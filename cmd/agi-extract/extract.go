package main

import (
	"fmt"
	"os"
	"sort"

	"github.com/rwtodd/agi-tools/agi"
	flag "github.com/rwtodd/cmdflag"
)

// print the inverse map, from token type to
// a list of words...
func formatWords(w map[string]uint16) {
	var invMap = make(map[uint16][]string)
	for k, v := range w {
		invMap[v] = append(invMap[v], k)
	}
	var keys = make([]int, 0, len(invMap))
	for k := range invMap {
		keys = append(keys, int(k))
	}
	sort.Ints(keys)
	for _, k := range keys {
		fmt.Printf("%04d:", k)
		for idx, w := range invMap[uint16(k)] {
			if idx%4 == 0 && idx > 0 {
				fmt.Print("\n     ")
			}
			fmt.Printf("  <%s>", w)
		}
		fmt.Println()
	}
}

func main() {
	ver := flag.Bool("ver", false, "Print AGI Version Number")
	words := flag.Bool("words", false, "Print Words.TOK contents")

	flag.Parse()
	rootDir := "."
	if nonFlags := flag.Args(); len(nonFlags) > 0 {
		rootDir = nonFlags[0]
	}

	var options agi.GameLoadOption
	if *words {
		options |= agi.Load_WordsTok
	}

	game, err := agi.NewGame(rootDir, options)
	if err != nil {
		fmt.Println("Error! ", err)
		os.Exit(1)
	}

	if *ver {
		fmt.Println("AGI Version", &game.Version)
	}
	if *words {
		formatWords(game.Words)
	}
}
