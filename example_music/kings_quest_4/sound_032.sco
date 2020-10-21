;; AGI Sound Resource 32 (Volume 0 Offset 64477)


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
i11	9	106	0	127
i11	115	84	0	190
i11	199	5	0	214
i11	212	5	0	190
i11	229	106	0	127
i11	335	97	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	5	14	4	190
i12	19	14	4	170
i12	33	13	4	160
i12	46	10	4	143
i12	60	13	4	170
i12	73	9	4	160
i12	87	14	4	143
i12	101	5	4	127
i12	114	14	4	190
i12	128	14	4	170
i12	142	13	4	160
i12	155	10	4	143
i12	169	13	4	170
i12	183	8	4	160
i12	196	14	4	143
i12	210	5	4	127
i12	224	13	4	190
i12	237	14	4	170
i12	251	13	4	160
i12	265	9	4	143
i12	278	13	4	170
i12	292	8	4	160
i12	305	14	4	143
i12	319	5	4	127
i12	333	13	4	190
i12	346	14	4	170
i12	360	14	4	160
i12	374	9	4	143
i12	387	13	4	170
i12	401	9	4	160
i12	415	13	4	143
i12	428	6	4	127
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	6	13	4	480
i13	19	11	4	320
i13	33	13	4	254
i13	46	14	4	240
i13	60	13	4	214
i13	74	13	4	190
i13	87	12	4	170
i13	101	4	4	160
i13	115	13	4	480
i13	128	11	4	320
i13	142	14	4	254
i13	156	13	4	240
i13	169	13	4	214
i13	183	14	4	190
i13	197	11	4	170
i13	210	4	4	160
i13	224	13	4	480
i13	237	11	4	320
i13	251	14	4	254
i13	265	13	4	240
i13	278	14	4	214
i13	292	14	4	190
i13	306	11	4	170
i13	319	4	4	160
i13	333	14	4	480
i13	347	10	4	320
i13	360	14	4	254
i13	374	14	4	240
i13	388	13	4	214
i13	401	14	4	190
i13	415	11	4	170
i13	429	4	4	160
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	494	0.9	1.0	1.0
