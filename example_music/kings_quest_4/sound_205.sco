;; AGI Sound Resource 205 (Volume 0 Offset 91896)


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
i11	3	39	0	320
i11	42	33	0	339
i11	75	53	0	381
i11	130	103	0	190
i11	233	41	0	214
i11	274	85	0	240
i11	398	115	0	214
i11	548	42	0	320
i11	590	24	0	339
i11	615	30	0	381
i11	651	35	0	285
i11	686	27	0	320
i11	713	37	0	339
i11	751	68	0	381
i11	821	46	0	339
i11	869	69	0	403
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	3	99	0	762
i12	179	92	0	571
i12	320	78	0	428
i12	547	105	0	381
i12	747	181	0	508
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	2	100	0	762
i13	126	121	0	117
i13	272	90	0	855
i13	395	61	0	641
i13	963	1	0	240
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1024	0.9	1.0	1.0
