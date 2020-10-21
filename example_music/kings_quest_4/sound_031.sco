;; AGI Sound Resource 31 (Volume 2 Offset 551281)


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
i11	2	11	4	53
i11	21	11	4	80
i11	45	11	4	60
i11	66	11	4	80
i11	91	6	4	64
i11	102	7	4	71
i11	113	3	4	64
i11	123	6	4	60
i11	136	6	4	53
i11	146	5	4	48
i11	157	5	4	53
i11	169	4	4	60
i11	178	3	4	57
i11	181	35	4	53
i11	225	7	4	64
i11	247	36	4	80
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	1	12	4	107
i12	23	12	4	160
i12	46	11	4	120
i12	68	11	4	160
i12	90	11	4	127
i12	112	11	4	160
i12	134	12	4	120
i12	157	11	4	160
i12	179	11	4	107
i12	201	11	4	160
i12	223	11	4	120
i12	246	11	4	160
i12	268	11	4	127
i12	289	11	4	160
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	2	11	4	320
i13	24	11	4	190
i13	46	11	4	214
i13	68	11	4	190
i13	90	12	4	320
i13	113	11	4	190
i13	135	11	4	214
i13	157	11	4	190
i13	179	11	4	320
i13	202	11	4	190
i13	224	11	4	214
i13	246	11	4	190
i13	267	33	4	320
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	360	0.9	1.0	1.0
