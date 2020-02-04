package main

import (
	"fmt"
	"os"
)

// we'd like to perform a few operations in parallel. This
// file has the support functions to accomplish that.

// tokens limits concurrency to 10 operations
var tokens = make(chan struct{}, 10)
var taskErrors = make(chan error)

func concurrentTask(task func() error) {
	tokens <- struct{}{}
	taskErrors <- task()
	<-tokens
}

func reportNErrors(tasks int) int {
	var count int = 0
	var err error
	for ; tasks > 0; tasks-- {
		err = <-taskErrors
		if err != nil {
			count++
			fmt.Fprintln(os.Stderr, err)
		}
	}
	return count
}
