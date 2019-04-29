;; vim: tabstop=8: shiftwidth=8: softtabstop=8: noexpandtab:
sr = 44100
ksmps = 20
nchnls = 2

#define DAMPEN #0.4#

gienv	ftgen	0, 0, 4096, 7, 0.95, 8,  1, 9,  0.95, 9,  0.9, 8,   0.85, 43,  0.8, 51,  0.75, 85,  0.7, 444,  0.2, 409, 0.15, 1195,  0
gisqw	ftgen	0, 0, 2, 7, 1, 1, 1, 0, -1, 1, -1

ga1		init	0.0
gkvol		init	1.0

instr	1    ;; square waves
; i1	p2	p3	p4	p5	p7
;	start	dur	ampl	pitch	pan
iampl	=	ampdb(p4)
;; kenv	adsr	0.01, 0.1, 0.25, 0.2 
kenv	oscili	1, 0.125, gienv
asq	oscil	iampl, p5, gisqw
aenv	=	asq*kenv*$DAMPEN
ga1	+=	0.7*aenv
	outs	(p6*aenv),((1.0-p6)*aenv)
endin

instr 2 ;; "white" noise
; i2	p2	p3	p4	p5
;	start	dur	amp	hz
iampl	=	ampdb(p4)
aout	dust	iampl, p5 
	outs	aout, aout
endin

instr 3 ;; linear noise
iampl	=	ampdb(p4)
aout	mpulse	iampl, 1/p5
	outs	aout, aout
endin


instr	99
;; aout	reverb	ga1, 1.3
aout	nreverb	ga1, 0.8, 0.4
	outs	aout*0.15, aout*0.15
ga1	=	0
endin
