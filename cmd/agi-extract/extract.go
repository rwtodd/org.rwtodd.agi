package main

import (
	"fmt"
	"os"
	"strings"

	"github.com/rwtodd/agi-tools/agi"
	flag "github.com/rwtodd/cmdflag"
)

func main() {
	ver := flag.Bool("ver", false, "Print AGI Version Number")
	words := flag.String("words", "NONE", "Print Words.TOK contents (arg can be CSV|TABLE)")
	logic := flag.String("logic", "NONE", "Print Logic Scripts (arg is script numbers, or ALL)")
	objects := flag.String("objects", "NONE", "Print the Object list (arg can be CSV)")

	flag.Parse()
	*words = strings.ToUpper(*words)
	*logic = strings.ToUpper(*logic)
	*objects = strings.ToUpper(*objects)

	// set the root directory of the game
	rootDir := "."
	if nonFlags := flag.Args(); len(nonFlags) > 0 {
		rootDir = nonFlags[0]
	}

	// determine the index loading options based on the flags provided
	var options agi.GameLoadOption
	if *words != "NONE" || *logic != "NONE" {
		options |= agi.Load_WordsTok
	}
	if *logic != "NONE" {
		options |= agi.Load_LogicDir
	}
	if *objects != "NONE" {
		options |= agi.Load_Objects
	}

	game, err := agi.NewGame(rootDir, options)
	if err != nil {
		fmt.Println("Error! ", err)
		os.Exit(1)
	}

	// produce the requested output
	if *ver {
		fmt.Println("AGI Version", &game.Version)
	}
	switch *words {
	case "CSV":
		formatWordsCSV(game.Words)
	case "TABLE":
		formatWordsTable(game.Words)
	}

	switch *objects {
	case "CSV":
		fmt.Printf("\"ID\",\"Name\",\"Starting Room\",\"Max-Anim: %d\"\n", game.Objects.MaxAnimated)
		for idx, obj := range game.Objects.Objects {
			fmt.Printf("\"%d\",\"%s\",\"%d\"\n", idx, obj.Name, obj.StartingRoom)
		}
	}

	switch *logic {
	case "ALL":
		fmt.Printf("There are %d logic entries.\n", len(game.LogicDir))
		for i, entry := range game.LogicDir {
			if entry.IsPresent() {
				logic, err := game.LoadLogic(i)
				if err != nil {
					fmt.Printf("Logic %d ERROR: %v\n", i, err)
				} else {
					for mnum, msg := range logic.Messages {
						fmt.Printf("Logic %d Msg %d: <%s>\n", i, mnum, msg)
					}
				}
			} else {
				fmt.Printf("Logic %d is not present.\n", i)
			}
			fmt.Println("---------------------------------------------------------------")
		}
	}
}
