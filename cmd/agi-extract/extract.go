package main

import (
	"fmt"
	"os"

	"github.com/rwtodd/agi-tools/agi"
	flag "github.com/rwtodd/cmdflag"
)

func main() {
	ver := flag.Bool("ver", false, "Print AGI Version Number")
	flag.Parse()
	rootDir := "."
	if nonFlags := flag.Args(); len(nonFlags) > 0 {
		rootDir = nonFlags[0]
	}

	game, err := agi.NewGame(rootDir)
	if err != nil {
		fmt.Println("Error! ", err)
		os.Exit(1)
	}

	if *ver {
		fmt.Println(&game.Version)
	}

}
