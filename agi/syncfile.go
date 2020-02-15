package agi

import (
	"fmt"
	"os"
	"sync"
)

// SyncFiles are a way to ensure that the os.File for the Volume files
// are properly synchronized.

type syncFile struct {
	fname string     // the file name, maybe useful for error reporting
	file  *os.File   // the actual file to read
	mutex sync.Mutex // each syncFile has its own mutex
}

func (sf *syncFile) Close() error {
	sf.mutex.Lock()
	defer sf.mutex.Unlock()

	var err error
	if sf.file != nil {
		err = sf.file.Close()
	}
	sf.fname = ""
	sf.file = nil
	return err
}

type volumeFiles []syncFile

func newVolumeFiles(size int, nameFunc func(int) string) volumeFiles {
	volumes := make(volumeFiles, size)
	// don't need to lock here because we haven't returned the files to anyone yet
	for idx := range volumes {
		volumes[idx].fname = nameFunc(idx)
	}
	return volumes
}

func (vf volumeFiles) Close() error {
	var err error
	for idx := range vf {
		current := vf[idx].Close()
		if err == nil {
			err = current // only return the first error
		}
	}
	return err
}

func (vf volumeFiles) LockedOperation(vol int, op func(*os.File) ([]byte, error)) ([]byte, error) {
	if vol >= len(vf) {
		return nil, fmt.Errorf("No such AGI volume, number %d! (max was %d)", vol, len(vf))
	}
	sf := &vf[vol]
	sf.mutex.Lock()
	defer sf.mutex.Unlock()

	if sf.file == nil {
		var err error
		sf.file, err = os.Open(sf.fname)
		if err != nil {
			return nil, err
		}
	}

	return op(sf.file)
}
