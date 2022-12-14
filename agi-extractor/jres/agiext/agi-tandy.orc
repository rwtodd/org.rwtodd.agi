sr = 48000
ksmps = 32

nchnls = 1
0dbfs = 1

;; AGI sound-alike envelope
gienv	ftgen	0, 0, 4096, 7, 0.95, 8,  1, 9,  0.95, 9,  0.9, 8,   0.85, 43,  0.8, 51,  0.75, 85,  0.6, 444,  0.2, 409, 0.15, 1195,  0

;; noise wave...16 units long: 1 0 0 0  0 0 0 0  0 0 0 0  0 0 0 0
ginzw	ftgen	0, 0, 16, -7, 1, 1, 1, 0, 0, 15, 0

gaOut	init	0.0

instr   1     ;; load program (no-op for this orchestra)
inoop = p4
inoop2 = p5
inoop3 = p6
endin

instr   2     ;; set panning (no-op for this orchestra)
; i1	p2    p3     [1-3] pan
iWhich  = p4
iPanVal = p5
endin

instr	11,12,13    ;; square waves
; i11	p2	p3	p4	p5
;	start	dur	ampl	pitch
iampl   =       ampdbfs(-20 - 3 * p4)
ifreq   =       111860.78125 / p5
kenv	oscili	1, 0.125, gienv
asq	vco2	iampl, ifreq, 2, 0.5
aenv	=	asq*kenv
gaOut	+=	aenv
endin

instr 21 ;; "white" noise
; i2	p2	p3	p4	p5
;	start	dur	amp	hz
iampl	=	ampdbfs(-20 - (3*p4))
ifreq   =       111860.78125 / p5
idens = (ifreq/2) ;; impulses per sec, 1/2 the shift cycles on average
iperiod = (ifreq/16)
adust	dust	1, idens
aphase, aunused syncphasor iperiod, adust
aout table aphase, ginzw, 1
aout    *= iampl
gaOut	+= aout
endin

instr 31 ;; linear noise
; i2	p2	p3	p4	p5
; 	start	dur	amp	hz
iampl	=	ampdbfs(-20 - (3 * p4))
ifreq   =       111860.78125 / p5
iperiod = ifreq/16
idelay = 15/ifreq
;; apulse	mpulse	1, iperiod, idelay
aout oscil iampl, iperiod, ginzw, (1.001/16)
gaOut += aout
endin

instr	99 ;; out-mixer
;; i99 p2=start p3=dur p4=reverb time p5=volstart p6=volend
kslope init p5
if p5 != p6 then
	kslope	expon	p5, p3, p6
endif
	out gaOut*kslope
gaOut	=	0
endin
