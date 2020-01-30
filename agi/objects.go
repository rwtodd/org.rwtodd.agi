package agi

import (
	"errors"
)

type Object struct {
	StartingRoom uint8
	Name         string
}

type ObjectList struct {
	MaxAnimated uint8    // the maximum index of animated objects
	Objects     []Object // the list of objects in the game
}

// Any objects in room 255 are part of the Player's inventory
const PlayerInventoryRoom = 255

// Parse the OBJECT file format. The `src` is expected to be
// pre-decoded if necessary
func parseObjects(src []byte) (ObjectList, error) {
	var wordsStart = int(src[0]) | (int(src[1]) << 8)
	var result ObjectList
	result.MaxAnimated = src[2]
	objlst, objnames := src[3:wordsStart+3], src[wordsStart+3:]
	if wordsStart%3 != 0 {
		return result, errors.New("Bad OBJECTS format!")
	}
	for i := 0; i < wordsStart; i += 3 {
		var nameOffset = (int(objlst[i]) | (int(objlst[i+1]) << 8)) - wordsStart
		var name = asciizString(objnames, nameOffset)
		result.Objects = append(result.Objects, Object{objlst[i+2], name})
	}
	return result, nil
}
