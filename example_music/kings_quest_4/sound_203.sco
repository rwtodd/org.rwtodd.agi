;; AGI Sound Resource 203 (Volume 0 Offset 91742)


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
i11	8	17	0	95
i11	30	37	0	107
i11	69	52	0	95
i11	122	16	0	107
i11	138	30	0	120
i11	172	4	0	107
i11	178	39	0	120
i11	219	47	0	160
i11	266	84	0	120
i11	351	171	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	87	74	0	190
i12	174	114	0	240
i12	369	78	0	254
i12	457	72	0	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	104	102	0	480
i13	206	121	0	381
i13	385	155	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	600	0.9	1.0	1.0
