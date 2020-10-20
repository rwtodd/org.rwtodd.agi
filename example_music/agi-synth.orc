;; this one sends notes to a midi synth (still generates its own noise)

sr = 48000
ksmps = 32

nchnls = 2
0dbfs = 1

;; should we round the midi notes to nearest int?
giRounding      init    0   

;; noise wave...16 units long: 1 0 0 0  0 0 0 0  0 0 0 0  0 0 0 0
ginzw	ftgen	0, 0, 16, -7, 1, 1, 1, 0, 0, 15, 0

gaLeft		init	0.0
gaRight 	init	0.0


;; load up the "FatBoy" soundfont into the fluidEngine
;; (it's at https://fatboy.site/)
giengine fluidEngine
gisfnum	 fluidLoad "H:\\OneDrive\\WATMM\\SoundFonts\\FatBoy-v0.786.sf2", giengine ;; , 1
;;gisfnum	 fluidLoad "H:\\OneDrive\\WATMM\\SoundFonts\\FluidR3_GM.sf2", giengine ;; , 1 

instr 1       ;; set program
;; i1  p2  p3 [1-3] [bank] [patch]
   ich = p4
   ibk = p5
   ipt = p6
   fluidProgramSelect giengine, ich, gisfnum, ibk, ipt
endin

instr   2     ;; set panning, rounding
; i2	p2    p3     [1-3] pan
iWhich  = p4
iPanVal = int(p5*128)
if (iWhich == 4) igoto roundingSet ;; the rounding code
   fluidCCi giengine, iWhich, 10, iPanVal
goto after
roundingSet:
   giRounding = p5
after:
endin

instr	11    ;; square wave 1
; i11	p2	p3	p4	p5
;	start	dur	ampl	pitch
iveloc  = 100 - (6*p4)
ifreq   = 111860.78125 / p5
imidi   ftom ifreq,giRounding
fluidNote giengine, 1, imidi, iveloc
endin

instr	12    ;; square wave 2
; i12	p2	p3	p4	p5
;	start	dur	ampl	pitch
iveloc  = 100 - (6*p4)
ifreq   = 111860.78125 / p5
imidi   ftom ifreq,giRounding
fluidNote giengine, 2, imidi, iveloc
endin

instr	13    ;; square wave 3
; i11	p2	p3	p4	p5
;	start	dur	ampl	pitch
iveloc  = 100 - (6*p4)
ifreq   = 111860.78125 / p5
imidi   ftom ifreq,giRounding
fluidNote giengine, 3, imidi, iveloc
endin

instr 21 ;; "white" noise
; i21	p2	p3	p4	p5
;	start	dur	amp	hz
iampl	=	ampdbfs(-20 - (3*p4))
ifreq   = 111860.78125 / p5
idens = (ifreq/2) ;; impulses per sec, 1/2 the shift cycles on average
iperiod = (ifreq/16)
adust	dust	1, idens
aphase, aunused syncphasor iperiod, adust
aout table aphase, ginzw, 1
aout *= iampl
gaLeft	+=	aout
gaRight	+=	aout
endin

instr 31 ;; linear noise
; i31	p2	p3	p4	p5
; start	dur	amp	hz
iampl	=	ampdbfs(-20 - (3*p4))
ifreq   = 111860.78125 / p5
iperiod = ifreq/16
idelay = 15/ifreq
aout oscil iampl, iperiod, ginzw, (1.001/16)
gaLeft += aout
gaRight += aout
endin

instr	99 ;; out-mixer
;; i99 p2=start p3=dur p4=reverb time p5=volstart p6=volend
afsLeft, afsRight fluidOut giengine
gaLeft += (3*afsLeft)
gaRight += (3*afsRight)
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
