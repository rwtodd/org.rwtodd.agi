package main

import (
	"bufio"
	"fmt"
	"os"
	"path/filepath"
	"sort"

	"github.com/rwtodd/agi-tools/agi"
)

// format the WORDS.TOK data as CSV, sorted by word
func formatWordsCSV(game *agi.Game, odir string) error {
	path := filepath.Join(odir, "words.csv")
	wordsFile, err := os.Create(path)
	if err != nil {
		return err
	}
	wordsBuf := bufio.NewWriter(wordsFile)

	fmt.Fprintln(wordsBuf, `"Word","Category"`)
	w := game.Words
	var keys = make([]string, 0, len(w))
	for k := range w {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	for _, k := range keys {
		fmt.Fprintf(wordsBuf, "\"%s\",\"%d\"\n", k, w[k])
	}
	if err = wordsBuf.Flush(); err != nil {
		return err
	}
	return wordsFile.Close()
}

// format the WORDS.TOK data as a text table, ordered by the
// category number.
func formatWordsTable(game *agi.Game, odir string) error {
	var invMap = game.Synonyms
	var keys = make([]int, 0, len(invMap))
	for k := range invMap {
		keys = append(keys, int(k))
	}
	sort.Ints(keys)

	path := filepath.Join(odir, "words_table.txt")
	wordsFile, err := os.Create(path)
	if err != nil {
		return err
	}
	wordsBuf := bufio.NewWriter(wordsFile)

	for _, k := range keys {
		fmt.Fprintf(wordsBuf, "%04d:", k)
		for idx, w := range invMap[uint16(k)] {
			if idx%4 == 0 && idx > 0 {
				fmt.Fprint(wordsBuf, "\n     ")
			}
			fmt.Fprintf(wordsBuf, "  <%s>", w)
		}
		fmt.Fprintln(wordsBuf)
	}
	if err = wordsBuf.Flush(); err != nil {
		return err
	}
	return wordsFile.Close()
}
