package agi

import (
	"bufio"
	"bytes"
	"compress/lzw"
	"errors"
	"fmt"
	"io"
	"io/ioutil"
	"os"
	"path/filepath"
	"strconv"
	"strings"
)

// Various processing depends on the AGI version of a game,
// and this struct tracks that version information.
type Version struct {
	Numeric float64
	Text    string
}

// Construct a Version object from a version string.
// Returns an error if the format was bad.
func newVersion(vstr string) (Version, error) {
	numericStr := vstr
	if len(vstr) > 5 {
		numericStr = vstr[:5] + vstr[6:]
	}
	number, err := strconv.ParseFloat(numericStr, 64)
	return Version{number, vstr}, err
}

// Dig the AGI version number out of AGIDATA.OVL. This is
// a simple search for text that looks like a version number.
func determineGameVersion(root string) (Version, error) {
	path := filepath.Join(root, "AGIDATA.OVL")
	agifile, err := os.Open(path)
	if err != nil {
		return Version{}, err
	}
	defer agifile.Close()
	agidata := bufio.NewReader(agifile)

	// search for a version number...
	partial := make([]byte, 0, 9)
	for {
		b, err := agidata.ReadByte()
		if err != nil {
			return Version{}, err
		}
		lp := len(partial)
		switch {
		case b == '.' && (lp == 1 || lp == 5):
			partial = append(partial, b)
		case b >= '0' && b <= '9' && lp != 1 && lp != 5:
			partial = append(partial, b)
		case lp == 5 || lp == 9:
			return newVersion(string(partial))
		default:
			partial = partial[:0] // start over with the next byte
		}
	}
}

// Versions are Stringers
func (v *Version) String() string {
	return v.Text
}

// utility version to detect a V1-or-V2 game
func (v *Version) IsV2() bool {
	return v.Numeric < 3.0
}

// Optional Metadata for `NewGame` to load.  Load_AllMetaData loads
// everything possible.  Some applications may only need certain
// resources.
type GameLoadOption uint16

const (
	Load_WordsTok GameLoadOption = 1 << iota
	Load_LogicDir
	Load_PicDir
	Load_ViewDir
	Load_SndDir
	Load_Objects
	Load_AllMetaData GameLoadOption = 0xffff
)

// DirEntry represents the location of a resource in an AGI game.
// AGI Games are divided into volumes, and within those volumes
// the resources are stored at specific offsets.
type DirEntry struct {
	volume uint8  // the volume file
	offset uint32 // the offset in the file
}

// utility method to tell whether a directory entry is actually
// present in the volume file.  F/FFFFFF == missing entry.
func (de DirEntry) IsPresent() bool {
	return (de.volume != 0x0f) || (de.offset != 0xfffff)
}

// dirEntries are Stringers
func (de DirEntry) String() string {
	if de.IsPresent() {
		return fmt.Sprintf("Volume %d Offset %d", de.volume, de.offset)
	}
	return "Not Present"
}

// The master struct for AGI game information.
type Game struct {
	RootDir  string            // the filesystem root of the game
	Version  Version           // the AGI version of this game
	Prefix   string            // Volume/DIR Prefix, only for V3
	Words    map[string]uint16 // the WORDS.TOK info
	Objects  ObjectList        // the OBJECTS list of objects
	LogicDir []DirEntry        // index to the LOGIC scripts
	PicDir   []DirEntry        // index to the PIC resources
	ViewDir  []DirEntry        // index to the VIEW resources
	SoundDir []DirEntry        // index to the SOUND resources
}

// Construct a Game struct, given the root directory of
// the game in the filesystem.
func NewGame(rootDir string, options GameLoadOption) (*Game, error) {
	ver, err := determineGameVersion(rootDir)
	if err != nil {
		return nil, err
	}
	game := &Game{
		RootDir: rootDir,
		Version: ver,
	}

	// if we need to load WORDS.TOK, do so.
	if options&Load_WordsTok > 0 {
		game.Words, err = loadWordsTok(rootDir)
	}
	if err != nil {
		return nil, err
	}

	// if we need to load OBJECT, do so
	if options&Load_Objects > 0 {
		game.Objects, err = loadObjects(game)
		if err != nil {
			return nil, err
		}
	}

	// if we need to load resource indices, do so based on the
	// AGI version
	if game.Version.IsV2() {
		if options&Load_LogicDir > 0 {
			game.LogicDir, err = loadResDir_V2(rootDir, "LOGDIR")
			if err != nil {
				return nil, err
			}
		}
		if options&Load_PicDir > 0 {
			game.PicDir, err = loadResDir_V2(rootDir, "PICDIR")
			if err != nil {
				return nil, err
			}
		}
		if options&Load_SndDir > 0 {
			game.SoundDir, err = loadResDir_V2(rootDir, "SNDDIR")
			if err != nil {
				return nil, err
			}
		}
		if options&Load_ViewDir > 0 {
			game.ViewDir, err = loadResDir_V2(rootDir, "VIEWDIR")
			if err != nil {
				return nil, err
			}
		}
	} else {
		if options&(Load_LogicDir|Load_PicDir|Load_SndDir|Load_ViewDir) > 0 {
			// for V3 games, we need to figure out the filename prefixes...
			game.Prefix, err = determinePrefix(rootDir)
			if err != nil {
				return nil, err
			}

			// ... and just load all the resource directories at once
			err = loadResDirs_V3(game)
			if err != nil {
				return nil, err
			}
		}
	}

	return game, nil
}

// Figure out the prefix on the DIR and VOL files, for V3 games
func determinePrefix(rootDir string) (string, error) {
	matches, err := filepath.Glob(filepath.Join(rootDir, "*VOL.0"))
	if err != nil || len(matches) == 0 {
		return "", errors.New("Couldn't find prefix on VOL.0!")
	}
	prefix := strings.TrimSuffix(filepath.Base(matches[0]), "VOL.0")
	return prefix, nil
}

// Parse a set of directory entries (e.g., from LOGDIR)
func parseResDir(src []byte) ([]DirEntry, error) {
	var srcLen = len(src)
	if srcLen%3 != 0 {
		return nil, errors.New("resource directory size must be a multiple of 3!")
	}

	var entries = make([]DirEntry, 0, srcLen/3)
	var entry DirEntry
	for idx := 0; idx < srcLen; idx += 3 {
		entry.volume = src[idx] >> 4
		entry.offset = (uint32(src[idx]&0x0f) << 16) | (uint32(src[idx+1]) << 8) | uint32(src[idx+2])
		entries = append(entries, entry)
	}
	return entries, nil
}

// Load an AGI V2 resource directory from disk, and parse it
func loadResDir_V2(rootDir string, fname string) ([]DirEntry, error) {
	path := filepath.Join(rootDir, fname)
	src, err := ioutil.ReadFile(path)
	if err != nil {
		return nil, err
	}
	return parseResDir(src)
}

// Load the AGI V3 resource directories from disk, and parse them. Since
// we have to open the file, we might as well parse them all, I think.
func loadResDirs_V3(game *Game) error {
	path := filepath.Join(game.RootDir, game.Prefix+"DIR")
	src, err := ioutil.ReadFile(path)
	if err != nil {
		return err
	}

	offset1 := int(src[0]) | (int(src[1]) << 8)
	offset2 := int(src[2]) | (int(src[3]) << 8)
	game.LogicDir, err = parseResDir(src[offset1:offset2])
	if err != nil {
		return err
	}

	offset1, offset2 = offset2, int(src[4])|(int(src[5])<<8)
	game.PicDir, err = parseResDir(src[offset1:offset2])
	if err != nil {
		return err
	}

	offset1, offset2 = offset2, int(src[6])|(int(src[7])<<8)
	game.ViewDir, err = parseResDir(src[offset1:offset2])
	if err != nil {
		return err
	}

	game.SoundDir, err = parseResDir(src[offset2:])
	return err
}

// Loads the OBJECT file, decoding it if necessary.
func loadObjects(game *Game) (ObjectList, error) {
	path := filepath.Join(game.RootDir, "OBJECT")
	objectBytes, err := ioutil.ReadFile(path)
	if err != nil {
		return ObjectList{}, err
	}
	if game.Version.Numeric >= 2.411 {
		decode(Avis_Durgan, objectBytes)
	}
	return parseObjects(objectBytes)
}

// Loads the WORDS.TOK file for both v2 and v3 games.
func loadWordsTok(rootDir string) (map[string]uint16, error) {
	path := filepath.Join(rootDir, "WORDS.TOK")
	wordsBytes, err := ioutil.ReadFile(path)
	if err != nil {
		return nil, err
	}
	return parseWordsTok(wordsBytes), nil
}

// load a version-2 resource as a byte array, raw and unprocessed
func loadResource_V2(rootDir string, de DirEntry) ([]byte, error) {
	if !de.IsPresent() {
		return nil, errors.New("Resource isn't present!")
	}

	// first, open the file and read the resource header
	path := filepath.Join(rootDir, fmt.Sprintf("VOL.%d", de.volume))
	volFile, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	var header = make([]byte, 5)
	var offset = int64(de.offset)
	_, err = volFile.ReadAt(header, offset)
	if err != nil {
		return nil, err
	}
	if header[0] != 0x12 || header[1] != 0x34 || header[2] != de.volume {
		return nil, errors.New("Invalid resource header!")
	}

	// second, pull the resource bytes out of the file
	var reslen = uint32(header[3]) | (uint32(header[4]) << 8)
	var resource = make([]byte, reslen)
	_, err = volFile.ReadAt(resource, offset+5)
	return resource, err
}

// load a version-3 resource as a byte array, raw and unprocessed
func loadResource_V3(game *Game, de DirEntry) ([]byte, error) {
	if !de.IsPresent() {
		return nil, errors.New("Resource isn't present!")
	}

	// first, open the file and read the resource header
	path := filepath.Join(game.RootDir, fmt.Sprintf("%sVOL.%d", game.Prefix, de.volume))
	volFile, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	var header = make([]byte, 7)
	var offset = int64(de.offset)
	_, err = volFile.ReadAt(header, offset)
	if err != nil {
		return nil, err
	}
	if header[0] != 0x12 || header[1] != 0x34 || (header[2]&0x7f) != de.volume {
		return nil, errors.New("Invalid resource header!")
	}

	// second, pull the resource bytes out of the file
	var reslen = uint32(header[3]) | (uint32(header[4]) << 8)
	var lzwlen = uint32(header[5]) | (uint32(header[6]) << 8)

	var diskBytes = make([]byte, lzwlen)
	if _, err = volFile.ReadAt(diskBytes, offset+7); err != nil {
		return nil, err
	}

	// we have the resource now... decompress it if necessary...
	switch {
	case (header[2] & 0x80) > 0:
		// TODO... PicCompression
		return nil, errors.New("TODO: Pic Compression")
	case lzwlen != reslen:
		var resource = make([]byte, reslen)
		var expander = lzw.NewReader(bytes.NewReader(diskBytes), lzw.LSB, 8)
		_, err = io.ReadFull(expander, resource)
		expander.Close()
		return resource, err
	default:
		return diskBytes, nil
	}
}
