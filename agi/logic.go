package agi

import (
	"fmt"
)

// Logic resources are a combination of bytecode and string
// messages.
type Logic struct {
	ByteCode []byte
	Messages []string
}

// Loads logic resource number `num` for the given game `g`
func (g *Game) LoadLogic(num int) (*Logic, error) {
	if num >= len(g.LogicDir) || !g.LogicDir[num].IsPresent() {
		return nil, fmt.Errorf("Logic Resource %d does not exist!", num)
	}

	if g.Version.IsV2() {
		// step 1: get the raw bytes
		raw, err := loadResource_V2(g.RootDir, g.LogicDir[num])
		if err != nil {
			return nil, err
		}

		// step 2: decrypt the messages
		var textArea = (int(raw[0]) | (int(raw[1]) << 8)) + 2
		var msgIndexLen = 2 * int(raw[textArea])
		_ = decode(Avis_Durgan, raw[textArea+3+msgIndexLen:])

		// step 3: parse the script
		return parseLogic(raw)
	} else {
		return nil, fmt.Errorf("Cannot load V3 logic resources!")
	}
}

// parses the decoded logic script bytes
func parseLogic(src []byte) (*Logic, error) {
	// step 1, get the bytecode out
	var logicLen = int(src[0]) | (int(src[1]) << 8)
	var byteCode = src[2 : 2+logicLen]

	// step 2, get the messages out
	var msgCount = int(src[2+logicLen])
	var msgBaseIdx = 3 + logicLen
	var msgEndIdx = msgBaseIdx + 2 + (msgCount * 2)
	var messages = make([]string, 0, msgCount)
	for i := msgBaseIdx + 2; i < msgEndIdx; i += 2 {
		strOffs := int(src[i]) | (int(src[i+1]) << 8)
		messages = append(messages, asciizString(src, msgBaseIdx+strOffs))
	}
	return &Logic{byteCode, messages}, nil
}
