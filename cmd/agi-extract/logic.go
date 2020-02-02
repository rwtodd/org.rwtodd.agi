package main

import (
	"bufio"
	"fmt"
	"os"
	"path/filepath"

	"github.com/rwtodd/agi-tools/agi"
)

func outputLogic(game *agi.Game, odir string, i int) error {
	if i < 0 || i >= len(game.LogicDir) {
		return fmt.Errorf("Logic %d is out of range!", i)
	}

	entry := game.LogicDir[i]
	if entry.IsPresent() {
		logic, err := game.LoadLogic(i)
		if err != nil {
			return fmt.Errorf("Logic %d ERROR: %v", i, err)
		}
		path := filepath.Join(odir, fmt.Sprintf("logic_%03d.txt", i))
		logFile, err := os.Create(path)
		if err != nil {
			return err
		}
		logBuf := bufio.NewWriter(logFile)
		for mnum, msg := range logic.Messages {
			fmt.Fprintf(logBuf, "Logic %d Msg %d: <%s>\n", i, mnum+1, msg)
		}
		if err = logBuf.Flush(); err != nil {
			return err
		}
		if err = logFile.Close(); err != nil {
			return err
		}
	}
	return nil
}
