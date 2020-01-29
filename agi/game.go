package agi

import (
	"bufio"
	"io/ioutil"
	"os"
	"path/filepath"
	"strconv"
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
	Load_AllMetaData GameLoadOption = 0xffff
)

// The master struct for AGI game information.
type Game struct {
	RootDir string            // the filesystem root of the game
	Version Version           // the AGI version of this game
	Words   map[string]uint16 // the WORDS.TOK info
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
		Words:   nil,
	}
	if options&Load_WordsTok > 0 {
		game.Words, err = loadWordsTok(rootDir)
	}
	if err != nil {
		return nil, err
	}

	return game, nil
}

func loadWordsTok(rootDir string) (map[string]uint16, error) {
	path := filepath.Join(rootDir, "WORDS.TOK")
	wordsBytes, err := ioutil.ReadFile(path)
	if err != nil {
		return nil, err
	}
	return parseWordsTok(wordsBytes), nil
}
