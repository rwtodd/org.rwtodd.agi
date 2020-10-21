;; AGI Sound Resource 105 (Volume 1 Offset 91036)


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
i11	30	44	0	190
i11	77	46	0	143
i11	125	41	0	160
i11	166	44	0	190
i11	212	41	0	143
i11	255	44	0	160
i11	299	47	0	190
i11	347	43	0	143
i11	390	39	0	160
i11	429	43	0	190
i11	472	41	0	143
i11	513	43	0	160
i11	556	42	0	254
i11	598	43	0	170
i11	641	44	0	143
i11	686	44	0	214
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
i99	0	790	0.9	1.0	1.0
