package agi

import (
	"fmt"
)

type Sound struct {
	Voice1, Voice2, Voice3 []Tone
	Voice4                 []Noise
}

type Tone struct {
	Frequency   uint16
	Duration    uint16
	Attenuation uint8
}

type NoiseType uint8

const (
	NoiseType_Linear NoiseType = iota
	NoiseType_White
)

type Noise struct {
	Duration    uint16
	Frequency   uint8
	Attenuation uint8
	Type        NoiseType
}

func checkChannelLength(src []byte) error {
	var slen = len(src)
	var remainder = slen % 5
	// either the length must be a multiple of 5, or
	// it must be longer by exactly two 0xff bytes
	var lenOK = (remainder == 0) ||
		(remainder == 2 && src[slen-2] == 0xff && src[slen-1] == 0xff)
	if !lenOK {
		return fmt.Errorf("Sound channel has a bad length %d!", slen)
	}
	return nil
}

func parseTones(src []byte) ([]Tone, error) {
	if lenErr := checkChannelLength(src); lenErr != nil {
		return nil, lenErr
	}

	var result = make([]Tone, 0, len(src)/5)
	for len(src) > 2 {
		curTone := Tone{
			Frequency:   (uint16(src[2]&0x3f) << 4) | uint16(src[3]&0x0f),
			Duration:    uint16(src[0]) | (uint16(src[1]) << 8),
			Attenuation: src[4] & 0x0f,
		}
		if curTone.Duration != 0xffff {
			result = append(result, curTone)
		}
		src = src[5:]
	}
	return result, nil
}

var noiseFreqs = [4]uint8{0x10, 0x20, 0x40, 0}

func parseNoises(src []byte) ([]Noise, error) {
	if lenErr := checkChannelLength(src); lenErr != nil {
		return nil, lenErr
	}

	var result = make([]Noise, 0, len(src)/5)
	for len(src) > 2 {
		var nt NoiseType
		if src[3]&0x04 == 0 {
			nt = NoiseType_Linear
		} else {
			nt = NoiseType_White
		}

		curNoise := Noise{
			Duration:    uint16(src[0]) | (uint16(src[1]) << 8),
			Frequency:   noiseFreqs[int(src[3]&0x03)],
			Attenuation: src[4] & 0x0f,
			Type:        nt,
		}
		if curNoise.Duration != 0xffff {
			result = append(result, curNoise)
		}
		src = src[5:]
	}
	return result, nil
}

// Loads sound resource number `num` for the given game `g`
func (g *Game) LoadSound(num int) (*Sound, error) {
	if num >= len(g.SoundDir) || !g.SoundDir[num].IsPresent() {
		return nil, fmt.Errorf("Sound Resource %d does not exist!", num)
	}

	var raw []byte
	var err error

	// Step 1: get the raw bytes
	if g.Version.IsV2() {
		raw, err = loadResource_V2(g, g.SoundDir[num])
	} else {
		raw, err = loadResource_V3(g, g.SoundDir[num])
	}
	if err != nil {
		return nil, err
	}

	// Step 2: determine the lengths of the channels
	ch1 := int(raw[0]) | (int(raw[1]) << 8)
	ch2 := int(raw[2]) | (int(raw[3]) << 8)
	ch3 := int(raw[4]) | (int(raw[5]) << 8)
	ch4 := int(raw[6]) | (int(raw[7]) << 8)

	// Step 3: parse the channels
	var result Sound
	if result.Voice1, err = parseTones(raw[ch1:ch2]); err != nil {
		return nil, err
	}
	if result.Voice2, err = parseTones(raw[ch2:ch3]); err != nil {
		return nil, err
	}
	if result.Voice3, err = parseTones(raw[ch3:ch4]); err != nil {
		return nil, err
	}
	if result.Voice4, err = parseNoises(raw[ch4:]); err != nil {
		return nil, err
	}
	return &result, nil
}
