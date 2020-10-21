;; AGI Sound Resource 18 (Volume 0 Offset 66523)


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
i11	10	7	0	127
i11	25	7	0	127
i11	40	7	0	127
i11	55	7	0	151
i11	70	7	0	127
i11	85	7	0	113
i11	100	7	0	127
i11	115	22	0	151
i11	145	7	0	151
i11	160	37	0	170
i11	205	7	0	151
i11	220	22	0	170
i11	250	7	0	127
i11	265	7	0	127
i11	280	7	0	127
i11	295	7	0	151
i11	310	7	0	127
i11	325	7	0	113
i11	340	7	0	127
i11	355	22	0	151
i11	385	7	0	151
i11	400	7	0	170
i11	415	7	0	151
i11	430	7	0	170
i11	445	30	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	10	8	0	151
i12	25	8	0	151
i12	40	8	0	151
i12	55	8	0	190
i12	70	8	0	151
i12	85	8	0	143
i12	100	8	0	151
i12	115	23	0	190
i12	145	8	0	190
i12	160	38	0	202
i12	205	8	0	190
i12	220	23	0	202
i12	250	8	0	151
i12	265	8	0	151
i12	280	8	0	151
i12	295	8	0	190
i12	310	8	0	151
i12	325	8	0	143
i12	340	8	0	151
i12	355	23	0	190
i12	385	8	0	190
i12	400	8	0	202
i12	415	8	0	190
i12	430	8	0	202
i12	445	30	0	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	10	7	0	254
i13	25	7	0	254
i13	40	7	0	254
i13	55	7	0	302
i13	70	7	0	254
i13	85	7	0	226
i13	100	7	0	254
i13	115	22	0	302
i13	145	7	0	302
i13	160	37	0	339
i13	205	7	0	302
i13	220	22	0	339
i13	250	7	0	254
i13	265	7	0	254
i13	280	7	0	254
i13	295	7	0	302
i13	310	7	0	254
i13	325	7	0	226
i13	340	7	0	254
i13	354	23	0	302
i13	384	8	0	302
i13	399	8	0	339
i13	414	8	0	302
i13	429	8	0	339
i13	444	30	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	535	0.9	1.0	1.0
