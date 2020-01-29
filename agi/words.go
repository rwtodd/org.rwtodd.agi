package agi

// The following are special token numbers, as used by
// the AGI interpreters
const (
	Word_Ignored    = 0
	Word_Any        = 1
	Word_IgnoreLine = 9999
)

func parseWord(src []byte, lastWord string) (string, uint16, []byte) {
	idx, prefixLen := 1, int(src[0])
	end := len(src)
	suffix := make([]byte, 0, 32)
	for idx < end {
		current := src[idx]
		idx += 1
		suffix = append(suffix, (current^0x7f)&0x7f)
		if current&0x80 != 0 {
			break
		}
	}
	var code uint16
	// make sure there's room to read the code... it's Big-Endian 16-bit
	if idx < (end - 1) {
		code = (uint16(src[idx]) << 8) | uint16(src[idx+1])
		idx += 2
	}
	return lastWord[:prefixLen] + string(suffix), code, src[idx:]
}

// parses the WORDS.TOK data into a map from input words to
// token values.
func parseWordsTok(src []byte) map[string]uint16 {
	src = src[52:] // skip the first 52 bytes
	word, words := "", make(map[string]uint16)
	var code uint16
	for len(src) > 0 {
		word, code, src = parseWord(src, word)
		words[word] = code
	}
	return words
}
