;; AGI Sound Resource 70 (Volume 0 Offset 114589)


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
i11	5	22	0	113
i11	34	22	0	107
i11	63	11	0	101
i11	78	11	0	95
i11	92	11	0	101
i11	107	11	0	95
i11	121	11	0	101
i11	136	11	0	107
i11	150	22	0	113
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	5	22	0	127
i12	34	22	0	127
i12	63	22	0	127
i12	92	22	0	127
i12	121	22	0	127
i12	150	22	0	127
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	5	22	0	226
i13	34	22	0	214
i13	63	51	0	202
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	232	0.9	1.0	1.0
