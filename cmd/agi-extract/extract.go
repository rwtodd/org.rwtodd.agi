package main

import (
	"fmt"
	"os"
	"sort"
	"strings"

	"github.com/rwtodd/agi-tools/agi"
	flag "github.com/rwtodd/cmdflag"
)

// format the WORDS.TOK data as CSV, sorted by word
func formatWordsCSV(w map[string]uint16) {
	fmt.Println(`"Word","Category"`)
	var keys = make([]string, 0, len(w))
	for k := range w {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	for _, k := range keys {
		fmt.Printf("\"%s\",\"%d\"\n", k, w[k])
	}
}

// format the WORDS.TOK data as a text table, ordered by the
// category number.
func formatWordsTable(w map[string]uint16) {
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
	words := flag.String("words", "NONE", "Print Words.TOK contents (arg can be CSV|TABLE)")

	flag.Parse()
	*words = strings.ToUpper(*words)

	// set the root directory of the game
	rootDir := "."
	if nonFlags := flag.Args(); len(nonFlags) > 0 {
		rootDir = nonFlags[0]
	}

	// determine the index loading options based on the flags provided
	var options agi.GameLoadOption
	if *words != "NONE" {
		options |= agi.Load_WordsTok
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
}
