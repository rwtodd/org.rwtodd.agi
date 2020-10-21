;; AGI Sound Resource 52 (Volume 0 Offset 65413)


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
i11	15	12	0	64
i11	27	11	0	67
i11	38	11	0	71
i11	49	10	0	76
i11	59	12	0	80
i11	71	10	0	85
i11	81	9	0	90
i11	90	13	0	95
i11	103	10	0	101
i11	113	11	0	107
i11	124	12	0	113
i11	136	11	0	120
i11	147	12	0	127
i11	159	11	0	135
i11	170	12	0	143
i11	182	11	0	151
i11	193	14	0	160
i11	207	11	0	170
i11	218	11	0	180
i11	229	14	0	190
i11	243	13	0	202
i11	256	14	0	214
i11	270	11	0	226
i11	281	18	0	240
i11	299	105	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	12	25	1	190
i12	37	22	1	202
i12	59	20	1	214
i12	81	21	1	226
i12	114	14	1	254
i12	128	19	1	269
i12	147	22	1	285
i12	169	23	1	269
i12	193	24	1	320
i12	218	27	1	339
i12	246	22	1	360
i12	268	23	1	381
i12	300	100	1	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	14	140	1	302
i13	195	203	1	403
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	464	0.9	1.0	1.0
