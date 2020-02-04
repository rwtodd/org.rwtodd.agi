package main

import (
	"bufio"
	"fmt"
	"os"
	"path/filepath"

	"github.com/rwtodd/agi-tools/agi"
	flag "github.com/rwtodd/cmdflag"
)

func main() {
	od := flag.String("od", ".", "The directory to use for output files.")
	ver := flag.Bool("ver", false, "Output the AGI Version Number")
	words := flag.Bool("words", false, "Output Words.TOK contents")
	logic := flag.Bool("logic", false, "Output Logic Scripts")
	objects := flag.Bool("objects", false, "Output the Object list")
	restrict := flag.Int("n", -1, "Only output this item (-1 for all)")
	all := flag.Bool("all", false, "Equivalent to /ver/words/logic/objects/n:-1")

	flag.Parse()

	// set the root directory of the game
	rootDir := "."
	if nonFlags := flag.Args(); len(nonFlags) > 0 {
		rootDir = nonFlags[0]
	}

	// determine the index loading options based on the flags provided
	if *all {
		*ver, *words, *logic, *objects = true, true, true, true
		//*n = -1
	}

	var options agi.GameLoadOption
	if *words {
		options |= agi.Load_WordsTok | agi.Load_WordSynonyms
	}
	if *logic {
		// logic script decompilation needs several resource types
		options |= agi.Load_LogicDir | agi.Load_WordsTok | agi.Load_WordSynonyms | agi.Load_Objects
	}
	if *objects {
		options |= agi.Load_Objects
	}

	game, err := agi.NewGame(rootDir, options)
	if err != nil {
		fmt.Println("Error! ", err)
		os.Exit(1)
	}
	defer game.Close()

	// produce the requested output
	// start with the output directory
	err = os.MkdirAll(*od, 0777)
	if err != nil {
		fmt.Println("Can't make output dir! ", err)
		os.Exit(1)
	}

	var errCount int = 0
	if *ver {
		if err = outputVersion(game, *od); err != nil {
			fmt.Fprintln(os.Stderr, "Can't output version: ", err)
			errCount++
		}
	}

	if *words {
		if err = formatWordsCSV(game, *od); err != nil {
			fmt.Fprintln(os.Stderr, "Can't output words csv: ", err)
			errCount++
		}
		if err = formatWordsTable(game, *od); err != nil {
			fmt.Fprintln(os.Stderr, "Can't output words table: ", err)
			errCount++
		}
	}

	if *objects {
		if err = outputObjects(game, *od); err != nil {
			fmt.Fprintln(os.Stderr, "Can't output objects: ", err)
			errCount++
		}
	}

	if *logic {
		min, max := 0, len(game.LogicDir)
		if *restrict >= 0 {
			min, max = *restrict, *restrict+1
		}
		for i := min; i < max; i++ {
			logIndex := i
			go concurrentTask(func() error {
				err := outputLogic(game, *od, logIndex)
				if err != nil {
					err = fmt.Errorf("Can't output logic %d (%v): %v\n", logIndex, game.LogicDir[logIndex], err)
				}
				return err
			})
		}
		errCount += reportNErrors(max - min)
	}

	if errCount > 0 {
		os.Exit(1)
	}
}

func outputVersion(game *agi.Game, odir string) error {
	path := filepath.Join(odir, "version.txt")
	verFile, err := os.Create(path)
	if err != nil {
		return err
	}
	verBuf := bufio.NewWriter(verFile)
	fmt.Fprintln(verBuf, "AGI Version", &game.Version)
	err = verBuf.Flush()
	if err != nil {
		return err
	}
	return verFile.Close()
}

func outputObjects(game *agi.Game, odir string) error {
	path := filepath.Join(odir, "objects.csv")
	obFile, err := os.Create(path)
	if err != nil {
		return err
	}
	obBuf := bufio.NewWriter(obFile)
	fmt.Fprintf(obBuf, "\"ID\",\"Name\",\"Starting Room\",\"Max-Anim: %d\"\n", game.Objects.MaxAnimated)
	for idx, obj := range game.Objects.Objects {
		fmt.Fprintf(obBuf, "\"%d\",\"%s\",\"%d\"\n", idx, obj.Name, obj.StartingRoom)
	}
	err = obBuf.Flush()
	if err != nil {
		return err
	}
	return obFile.Close()
}
