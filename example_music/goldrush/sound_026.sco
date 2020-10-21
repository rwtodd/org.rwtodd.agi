;; AGI Sound Resource 26 (Volume 1 Offset 207619)


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
i11	2	6	0	381
i11	19	6	0	302
i11	37	6	0	403
i11	54	6	0	339
i11	71	6	0	428
i11	89	5	0	339
i11	106	6	0	453
i11	123	6	0	381
i11	141	5	0	508
i11	158	6	0	381
i11	175	6	0	453
i11	193	5	0	403
i11	210	6	0	381
i11	227	6	0	381
i11	245	5	0	339
i11	262	6	0	302
i11	279	26	0	285
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
i99	0	365	0.9	1.0	1.0
