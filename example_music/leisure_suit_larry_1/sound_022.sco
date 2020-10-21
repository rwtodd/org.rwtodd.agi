;; AGI Sound Resource 22 (Volume 0 Offset 23093)


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
i11	35	14	0	127
i11	53	24	0	107
i11	86	12	0	127
i11	103	13	0	160
i11	118	14	0	170
i11	133	16	0	160
i11	151	24	0	143
i11	183	16	0	170
i11	200	17	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	50	47	2	320
i12	102	46	2	254
i12	151	46	2	214
i12	201	26	2	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	287	0.9	1.0	1.0
