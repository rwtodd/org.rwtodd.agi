;; AGI Sound Resource 106 (Volume 1 Offset 91138)


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
i11	24	28	0	190
i11	59	39	0	160
i11	98	35	0	127
i11	133	38	0	190
i11	171	36	0	160
i11	211	39	0	127
i11	252	33	0	254
i11	285	38	0	170
i11	323	29	0	143
i11	359	34	0	254
i11	393	34	0	170
i11	432	36	0	143
i11	471	77	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	30	222	0	190
i12	252	100	0	214
i12	361	34	0	254
i12	395	37	0	170
i12	432	45	0	143
i12	478	78	0	160
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	616	0.9	1.0	1.0
