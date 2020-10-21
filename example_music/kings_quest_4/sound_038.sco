;; AGI Sound Resource 38 (Volume 2 Offset 551712)


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
i11	76	6	0	202
i11	87	6	0	254
i11	98	6	0	254
i11	109	6	0	214
i11	121	106	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	36	12	2	508
i12	79	5	2	679
i12	89	6	2	571
i12	100	6	2	571
i12	110	6	2	679
i12	121	12	2	508
i12	163	15	2	679
i12	206	12	2	508
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
