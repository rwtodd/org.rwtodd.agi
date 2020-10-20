sr = 48000
ksmps = 32

nchnls = 1
0dbfs = 1

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

instr	11    ;; square waves
; i11	p2	p3	p4	p5
;	start	dur	ampl	pitch
iampl   =       ampdbfs(-20)
ifreq   =       111860.78125 / p5
asq	vco2	iampl, ifreq, 2, 0.5
gaOut	+=	asq
endin

instr 12,13,21,31  ;; other voices are no-ops in this orchestra
iampl = p4
ifreq = p5
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
