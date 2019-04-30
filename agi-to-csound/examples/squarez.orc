;; vim: tabstop=8: shiftwidth=8: softtabstop=8: noexpandtab:
sr = 44100
ksmps = 20
nchnls = 2
; 0dbfs = 1

#define DAMPEN #0.0#

gienv	ftgen	0, 0, 4096, 7, 0.95, 8,  1, 9,  0.95, 9,  0.9, 8,   0.85, 43,  0.8, 51,  0.75, 85,  0.7, 444,  0.2, 409, 0.15, 1195,  0
gisqw	ftgen	0, 0, 2, 7, 1, 1, 1, 0, -1, 1, -1

gaLeft		init	0.0
gaRight init 0.0

instr	1    ;; square waves
; i1	p2	p3	p4	p5	p7
;	start	dur	ampl	pitch	pan
iampl	=	ampdbfs(p4-$DAMPEN)
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
aout	dust	iampl, p5
gaLeft	+=	aout
gaRight	+=	aout
endin

instr 3 ;; linear noise
iampl	=	ampdbfs(p4)
aout	mpulse	iampl, 1/p5
gaLeft	+=	aout
gaRight	+=	aout
endin


instr	99 ;; out-mixer
;; i99 p2=start p3=dur p4=reverb time p5=volstart p6=volend
aoL	reverb	gaLeft, p4
aoR	reverb	gaRight, p4
if p5 != p6 then
	kslope	expon	p5, p3, p6
else
	kslope	=	p5
endif
	outs	(gaLeft+(aoL*0.15))*kslope, (gaRight+(aoR*0.15))*kslope
gaLeft	=	0
gaRight	=	0
endin
