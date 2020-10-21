;; AGI Sound Resource 35 (Volume 0 Offset 64800)


t 0 3600 ;; AGI runs in 1/60th second ticks

; set up the instruments
i 1  0  0  1   0 4   ;; 4 Rhodes piano
i 1  0  0  2   0 4   ;; 4 Rhodes piano
i 1  0  0  3   0 4   ;; 4 Rhodes piano

; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	8	42	0	254
i11	50	76	0	170
i11	138	94	0	170
i11	235	17	0	190
i11	252	24	0	214
i11	278	44	0	285
i11	322	82	0	190
i11	420	106	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	7	57	2	381
i12	143	44	2	381
i12	274	94	2	339
i12	406	81	2	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	7	13	2	127
i13	21	8	2	254
i13	29	10	2	214
i13	39	8	2	190
i13	48	48	2	85
i13	145	13	2	127
i13	159	8	2	254
i13	167	10	2	214
i13	177	8	2	190
i13	186	48	2	85
i13	275	13	2	143
i13	291	10	2	226
i13	301	8	2	190
i13	310	7	2	170
i13	319	39	2	95
i13	408	12	2	143
i13	421	9	2	226
i13	430	6	2	190
i13	436	6	2	170
i13	442	9	2	143
i13	451	47	2	95
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	586	0.9	1.0	1.0
