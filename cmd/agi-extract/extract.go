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
	// restrict := flag.Int("n", -1, "Only output this item (-1 for all)")
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
		options |= agi.Load_WordsTok
	}
	if *logic {
		options |= agi.Load_LogicDir | agi.Load_WordsTok
	}
	if *objects {
		options |= agi.Load_Objects
	}

	game, err := agi.NewGame(rootDir, options)
	if err != nil {
		fmt.Println("Error! ", err)
		os.Exit(1)
	}

	// produce the requested output
	// start with the output directory
	err = os.MkdirAll(*od, 0777)
	if err != nil {
		fmt.Println("Can't make output dir! ", err)
		os.Exit(1)
	}

	if *ver {
		if err = outputVersion(game, *od); err != nil {
			fmt.Println("Can't output version: ", err)
			os.Exit(1)
		}
	}

	if *words {
		if err = formatWordsCSV(game, *od); err != nil {
			fmt.Println("Can't output words csv: ", err)
			os.Exit(1)
		}
		if err = formatWordsTable(game, *od); err != nil {
			fmt.Println("Can't output words table: ", err)
			os.Exit(1)
		}
	}

	if *objects {
		if err = outputObjects(game, *od); err != nil {
			fmt.Println("Can't output objects: ", err)
			os.Exit(1)
		}
	}

	if *logic {
		if err = outputLogic(game, *od); err != nil {
			fmt.Println("Cant' output logic: ", err)
			os.Exit(1)
		}
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

func outputLogic(game *agi.Game, odir string) error {
	for i, entry := range game.LogicDir {
		if entry.IsPresent() {
			logic, err := game.LoadLogic(i)
			if err != nil {
				fmt.Printf("Logic %d ERROR: %v\n", i, err)
			} else {
				path := filepath.Join(odir, fmt.Sprintf("logic_%03d.txt", i))
				logFile, err := os.Create(path)
				if err != nil {
					return err
				}
				logBuf := bufio.NewWriter(logFile)
				for mnum, msg := range logic.Messages {
					fmt.Fprintf(logBuf, "Logic %d Msg %d: <%s>\n", i, mnum, msg)
				}
				if err = logBuf.Flush(); err != nil {
					return err
				}
				if err = logFile.Close(); err != nil {
					return err
				}
			}
		}
	}
	return nil
}
