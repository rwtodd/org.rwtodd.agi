package main

import (
	"fmt"
	"sort"
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
