;; AGI Sound Resource 9 (Volume 1 Offset 333648)


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
i11	22	12	0	214
i11	39	12	0	190
i11	55	29	0	180
i11	88	30	0	143
i11	122	46	0	143
i11	172	12	0	135
i11	188	30	0	143
i11	222	29	0	180
i11	255	46	0	214
i11	305	13	0	190
i11	322	29	0	180
i11	355	29	0	180
i11	388	29	0	190
i11	422	33	0	190
i11	455	117	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	56	50	0	285
i12	122	50	0	269
i12	189	50	0	285
i12	256	50	0	285
i12	322	50	0	285
i12	389	50	0	320
i12	455	117	0	360
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	56	50	0	428
i13	123	50	0	428
i13	189	50	0	428
i13	256	50	0	360
i13	323	49	0	428
i13	389	50	0	453
i13	456	116	0	428
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	632	0.9	1.0	1.0
