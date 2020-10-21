;; AGI Sound Resource 19 (Volume 1 Offset 377331)


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
i11	13	8	0	214
i11	26	9	0	214
i11	44	6	0	190
i11	55	7	0	190
i11	75	7	0	180
i11	98	11	0	170
i11	143	9	0	107
i11	197	5	0	180
i11	202	49	0	127
i11	254	14	0	143
i11	268	6	0	160
i11	274	3	0	180
i11	277	16	0	127
i11	293	8	0	143
i11	302	12	0	160
i11	326	15	0	80
i11	341	6	0	90
i11	352	15	0	95
i11	368	17	0	80
i11	404	15	0	95
i11	420	9	0	107
i11	429	8	0	127
i11	444	8	0	160
i11	454	15	0	135
i11	469	6	0	170
i11	478	12	0	143
i11	493	20	0	160
i11	562	38	0	80
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	6	3	2	428
i12	9	10	2	339
i12	26	6	2	339
i12	44	6	2	339
i12	55	7	2	339
i12	76	8	2	339
i12	98	11	2	339
i12	136	11	2	428
i12	192	21	2	320
i12	303	11	2	381
i12	326	12	2	381
i12	353	15	2	539
i12	377	15	2	539
i12	404	9	2	508
i12	429	19	2	403
i12	454	1	2	403
i12	455	15	2	428
i12	478	20	2	480
i12	504	15	2	508
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	13	8	2	240
i13	25	8	2	240
i13	44	6	2	240
i13	54	8	2	240
i13	75	9	2	240
i13	98	11	2	240
i13	141	9	2	240
i13	195	15	2	254
i13	228	15	2	254
i13	254	14	2	254
i13	278	8	2	254
i13	302	13	2	320
i13	327	12	2	320
i13	354	15	2	320
i13	379	13	2	320
i13	405	19	2	320
i13	429	14	2	254
i13	455	18	2	269
i13	473	5	2	302
i13	479	15	2	320
i13	504	11	2	320
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	660	0.9	1.0	1.0
