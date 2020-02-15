package main

// routines for csound orchestra / score generation

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"path/filepath"

	"github.com/rwtodd/agi-tools/agi"
)

// calculates the sound length in sixths of a second. Note
// that AGI data is in 60ths of a second so we have to convert.
func soundLength(s *agi.Sound) float32 {
	var dur, max uint32
	for _, voice := range [...][]agi.Tone{s.Voice1, s.Voice2, s.Voice3} {
		dur = 0
		for _, t := range voice {
			dur += uint32(t.Duration)
		}
		if dur > max {
			max = dur
		}
	}
	dur = 0
	for _, n := range s.Voice4 {
		dur += uint32(n.Duration)
	}
	if dur > max {
		max = dur
	}

	return float32(max) / 10.0
}

func outputScore(game *agi.Game, odir string, i int) error {
	if i < 0 || i >= len(game.SoundDir) {
		return fmt.Errorf("Sound %d is out of range!", i)
	}

	entry := game.SoundDir[i]
	if entry.IsPresent() {
		sound, err := game.LoadSound(i)
		if err != nil {
			return fmt.Errorf("Sound %d ERROR: %v", i, err)
		}
		path := filepath.Join(odir, fmt.Sprintf("sound_%03d.sco", i))
		scoFile, err := os.Create(path)
		if err != nil {
			return err
		}
		scoBuf := bufio.NewWriter(scoFile)

		// calculate needed stats
		duration := soundLength(sound)

		// write the prelude
		fmt.Fprintf(scoBuf, "; This is '%s', sound resource #%d of length %f\n\n",
			filepath.Base(game.RootDir), i, duration)
		fmt.Fprintln(scoBuf, scorePrelude)

		// write the reverb..
		fmt.Fprintln(scoBuf, "\n; Set the reverb for 1 second longer than the song")
		fmt.Fprintln(scoBuf, ";   \t\t\t\treverb\tgain\tgain")
		fmt.Fprintln(scoBuf, "; 99\tstart\tdur\tdepth\tStart\tEnd")
		fmt.Fprintf(scoBuf, "i 99\t0\t%0.3f\t0.9\t1.0\t1.0\n", duration+6.0)

		// write each channel..
		err = outputChannel(scoBuf, "Voice 1", sound.Voice1, 0.5)
		if err == nil {
			err = outputChannel(scoBuf, "Voice 2", sound.Voice2, 0.7)
		}
		if err == nil {
			err = outputChannel(scoBuf, "Voice 3", sound.Voice3, 0.3)
		}
		if err == nil {
			err = outputNoise(scoBuf, "Voice 4 (Noise)", sound.Voice4)
		}
		if err != nil {
			// if any of the channels fails, just close up the file and report
			scoBuf.Flush()
			scoFile.Close()
			return err
		}

		// close up the file
		if err = scoBuf.Flush(); err != nil {
			return err
		}
		if err = scoFile.Close(); err != nil {
			return err
		}
	}
	return nil
}

// convert agi frequencies to real-world frequencies
func agiFreq(n uint16) float32 {
	if n == 0 {
		return 440.0 // just anything...
	}
	return 111860.78125 / float32(n)
}

// convert agi attenuation to csound db
func agiSoundLevel(lvl uint8) int {
	if lvl > 14 {
		return -200
	}
	return -20 - 3*int(lvl)
}

// convert agi durations at 1/60th second to csound at t 360 (1/6th second)
func agiDuration(dur uint16) float32 {
	return float32(dur) / 10.0
}

func outputChannel(buf io.Writer, name string, tones []agi.Tone, pan float32) error {
	fmt.Fprintln(buf, "\n\n; **** ", name)
	fmt.Fprintln(buf, "; 1\ttime\tdur\tdb\tfreq\tpan")
	var time float32 = 0
	var timeStr = ""
	var outputPrev = false // have we output the previous note?
	var panStr = fmt.Sprintf("%.2f", pan)
	var lastFreq float32 = -9999

	for _, t := range tones {
		if t.Attenuation < 15 {
			if outputPrev {
				timeStr = "+"
			} else {
				timeStr = fmt.Sprintf("%.3f", time)
			}
			var curFreq = agiFreq(t.Frequency)
			var freqStr = "."
			if curFreq != lastFreq {
				freqStr = fmt.Sprintf("%f", curFreq)
			}
			fmt.Fprintf(buf,
				"i 1\t%s\t%.2f\t%d\t%s\t%s\n",
				timeStr,
				agiDuration(t.Duration),
				agiSoundLevel(t.Attenuation),
				freqStr,
				panStr)
			outputPrev = true
			lastFreq = curFreq
			panStr = "."
		} else {
			outputPrev = false
		}
		time += agiDuration(t.Duration)
	}
	return nil
}

func outputNoise(buf io.Writer, name string, noises []agi.Noise) error {
	fmt.Fprintln(buf, "\n\n; **** ", name)
	fmt.Fprintln(buf, ";  \ttime\tdur\tdb\tfreq")
	var time float32 = 0
	var timeStr = ""
	var outputPrev = false // have we output the previous note?
	var typePrev = agi.NoiseType_White
	var lastFreq float32 = -9999

	for _, n := range noises {
		if n.Attenuation < 15 {
			if outputPrev && (n.Type == typePrev) {
				timeStr = "+"
			} else {
				timeStr = fmt.Sprintf("%.3f", time)
			}
			var inum = 2
			if n.Type == agi.NoiseType_Linear {
				inum = 3
			}
			var curFreq = agiFreq(uint16(n.Frequency))
			var freqStr = "."
			if curFreq != lastFreq {
				freqStr = fmt.Sprintf("%f", curFreq)
			}
			fmt.Fprintf(buf,
				"i %d\t%s\t%.2f\t%d\t%s\n",
				inum,
				timeStr,
				agiDuration(n.Duration),
				agiSoundLevel(n.Attenuation),
				freqStr)
			outputPrev = true
			typePrev = n.Type
			lastFreq = curFreq
		} else {
			outputPrev = false
		}
		time += agiDuration(n.Duration)
	}
	return nil
}

// write the canned example orchestra to disk.  This way we can always
// have an easy starting point.
func outputOrchestra(odir string) error {
	path := filepath.Join(odir, "agi.orc")
	ofile, err := os.Create(path)
	if err != nil {
		return err
	}
	defer ofile.Close()
	if _, err = ofile.WriteString(exampleOrch); err != nil {
		return err
	}
	return nil
}

const scorePrelude = `; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 1 for the main voice (square wave)
; Instrument 2 for white noise
; Instrument 3 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second
`

const exampleOrch = `;; vim: tabstop=8: shiftwidth=8: softtabstop=8: noexpandtab:
;; example use:  csound --wave --output=out.wav agi.orc whatever.sco

sr = 48000
ksmps = 32

;sr = 44100
;ksmps = 20

nchnls = 2
; 0dbfs = 1

#define DAMPEN #0.0#

;; AGI sound-alike envelope
gienv	ftgen	0, 0, 4096, 7, 0.95, 8,  1, 9,  0.95, 9,  0.9, 8,   0.85, 43,  0.8, 51,  0.75, 85,  0.7, 444,  0.2, 409, 0.15, 1195,  0

;; square wave
gisqw	ftgen	0, 0, 2, 7, 1, 1, 1, 0, -1, 1, -1

;; noise wave...16 units long: 1 0 0 0  0 0 0 0  0 0 0 0  0 0 0 0
ginzw	ftgen	0, 0, 16, -7, 1, 1, 1, 0, 0, 15, 0

gaLeft		init	0.0
gaRight init 0.0

instr	1    ;; square waves
; i1	p2	p3	p4	p5	p7
;	start	dur	ampl	pitch	pan
iampl	=	ampdbfs(p4-$DAMPEN.)
;; kenv	adsr	0.01, 0.1, 0.25, 0.2 
kenv	oscili	1, 0.125, gienv
asq	oscil	iampl, p5, gisqw
aenv	=	asq*kenv
aoL, aoR pan2	aenv, p6, 0
gaLeft	+=	aoL
gaRight +=	aoR
endin

instr 2 ;; "white" noise
; i2	p2	p3	p4	p5
;	start	dur	amp	hz
iampl	=	ampdbfs(p4)
idens = (p5/2) ;; impulses per sec, 1/2 the shift cycles on average
iperiod = (p5/16)
adust	dust	1, idens
aphase, aunused syncphasor iperiod, adust
aout table aphase, ginzw, 1
aout *= iampl
gaLeft	+=	aout
gaRight	+=	aout
endin

instr 3 ;; linear noise
; i2	p2	p3	p4	p5
; start	dur	amp	hz
iampl	=	ampdbfs(p4)
iperiod = p5/16
idelay = 15/p5
;; apulse	mpulse	1, iperiod, idelay
aout oscil iampl, iperiod, ginzw, (1.001/16)
gaLeft += aout
gaRight += aout
endin

instr	99 ;; out-mixer
;; i99 p2=start p3=dur p4=reverb time p5=volstart p6=volend
aoL	reverb	gaLeft, p4
aoR	reverb	gaRight, p4
kslope init p5
if p5 != p6 then
	kslope	expon	p5, p3, p6
endif
	outs	(gaLeft+(aoL*0.15))*kslope, (gaRight+(aoR*0.15))*kslope
gaLeft	=	0
gaRight	=	0
endin
`
