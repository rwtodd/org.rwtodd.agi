;; AGI Sound Resource 24 (Volume 2 Offset 555620)


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
i11	3	7	0	226
i11	33	7	0	190
i11	63	7	0	151
i11	93	7	0	113
i11	123	52	0	143
i11	183	7	0	151
i11	198	7	0	170
i11	213	7	0	190
i11	228	7	0	202
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	8	0	453
i12	32	8	0	381
i12	62	8	0	302
i12	92	8	0	226
i12	122	53	0	285
i12	182	8	0	302
i12	197	8	0	339
i12	212	7	0	381
i12	227	8	0	403
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	295	0.9	1.0	1.0
