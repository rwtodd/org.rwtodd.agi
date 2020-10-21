;; AGI Sound Resource 5 (Volume 0 Offset 29805)


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
i11	9	6	0	320
i11	15	6	0	285
i11	21	6	0	254
i11	34	9	0	214
i11	46	6	0	214
i11	65	6	0	190
i11	71	6	0	214
i11	84	12	0	254
i11	96	6	0	320
i11	115	6	0	285
i11	121	6	0	254
i11	134	6	0	254
i11	146	6	0	214
i11	159	6	0	214
i11	171	6	0	160
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	237	0.9	1.0	1.0
