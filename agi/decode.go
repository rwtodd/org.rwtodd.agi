package agi

// Some portions of AGI files are encoded via XOR with
// "Avis Durgan"
var Avis_Durgan = []byte("Avis Durgan")

// Decodes `src` by XOR-ing it with repeated copies
// of `code`.  It mutates `src` in place, and also
// returns it for convenience
func decode(code, src []byte) []byte {
	curCode, cl := 0, len(code)
	for i, v := range src {
		src[i] = v ^ code[curCode]
		curCode++
		if curCode == cl {
			curCode = 0
		}
	}
	return src
}
