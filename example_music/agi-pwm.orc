; This, to me, sounds nicer than the more authentic plain square waves.
; There is a little bit of pulse-width modulation to keep the sustained
; notes sounding slightly more interesting.
;
; I think it still maintains enough 'plain' to have authentic charm, as
; opposed to using MIDI/soundfonts.
;
sr = 48000
ksmps = 32

nchnls = 2
0dbfs = 1

#define DAMPEN #0.0#

;; AGI sound-alike envelope
gienv	ftgen	0, 0, 4096, 7, 0.95, 8,  1, 9,  0.95, 9,  0.9, 8,   0.85, 43,  0.8, 51,  0.75, 85,  0.6, 444,  0.2, 409, 0.15, 1195,  0

;; noise wave...16 units long: 1 0 0 0  0 0 0 0  0 0 0 0  0 0 0 0
ginzw	ftgen	0, 0, 16, -7, 1, 1, 1, 0, 0, 15, 0

gaLeft		init	0.0
gaRight 	init	0.0

giPan1		init	0.5
giPan2		init	0.7
giPan3		init	0.3

instr   1     ;; load program (no-op for this orchestra)
endin

instr   2     ;; set panning
; i1	p2    p3     [1-3] pan
iWhich  = p4
iPanVal = p5
if (iWhich == 1) igoto firstSq
if (iWhich == 2) igoto secondSq
if (iWhich == 3) igoto thirdSq
goto after

firstSq:
   giPan1 = iPanVal
   igoto after
secondSq:
   giPan2 = iPanVal
   igoto after
thirdSq:
   giPan3 = iPanVal
after:
endin

instr	11    ;; square wave 1
; i11	p2	p3	p4	p5
;	start	dur	ampl	pitch
iampl	=	ampdbfs(p4-$DAMPEN.)
;; kenv	adsr	0.01, 0.1, 0.25, 0.2 
kenv	oscili	1, 0.125, gienv
klfo	=	oscil:k(0.2,0.5,-1,random:i(0.75,1.0))+0.5
asq	vco2	iampl, p5, 2, klfo
aenv	=	asq*kenv
aoL, aoR pan2	aenv, giPan1, 0
gaLeft	+=	aoL
gaRight +=	aoR
endin

instr	12    ;; square wave 2
; i12	p2	p3	p4	p5
;	start	dur	ampl	pitch	
iampl	=	ampdbfs(p4-$DAMPEN.)
;; kenv	adsr	0.01, 0.1, 0.25, 0.2 
kenv	oscili	1, 0.125, gienv
klfo	=	oscil:k(0.2,0.5,-1,random:i(0.75,1.0))+0.5
asq	vco2	iampl, p5, 2, klfo
aenv	=	asq*kenv
aoL, aoR pan2	aenv, giPan2, 0
gaLeft	+=	aoL
gaRight +=	aoR
endin

instr	13    ;; square wave 3
; i13	p2	p3	p4	p5	
;	start	dur	ampl	pitch
iampl	=	ampdbfs(p4-$DAMPEN.)
;; kenv	adsr	0.01, 0.1, 0.25, 0.2 
kenv	oscili	1, 0.125, gienv
klfo	=	oscil:k(0.2,0.5,-1,random:i(0.75,1.0))+0.5
asq	vco2	iampl, p5, 2, klfo
aenv	=	asq*kenv
aoL, aoR pan2	aenv, giPan3, 0
gaLeft	+=	aoL
gaRight +=	aoR
endin

instr 21 ;; "white" noise
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

instr 31 ;; linear noise
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
