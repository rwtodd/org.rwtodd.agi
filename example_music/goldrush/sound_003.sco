;; AGI Sound Resource 3 (Volume 1 Offset 206454)


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
i11	22	6	0	226
i11	41	7	0	170
i11	48	99	0	180
i11	154	73	0	170
i11	234	6	0	226
i11	253	7	0	170
i11	260	99	0	180
i11	366	53	0	226
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	49	19	0	339
i12	75	7	0	269
i12	95	7	0	226
i12	102	26	0	240
i12	128	26	0	254
i12	155	19	0	339
i12	181	6	0	269
i12	201	6	0	226
i12	207	27	0	240
i12	234	26	0	254
i12	260	20	0	339
i12	287	6	0	269
i12	307	6	0	226
i12	313	27	0	240
i12	340	26	0	254
i12	366	53	0	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	479	0.9	1.0	1.0
