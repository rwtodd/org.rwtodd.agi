;; AGI Sound Resource 15 (Volume 1 Offset 375820)


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
i11	52	14	0	190
i11	80	31	0	160
i11	114	22	0	143
i11	136	3	0	127
i11	139	3	0	120
i11	143	25	0	127
i11	168	8	0	113
i11	176	10	0	127
i11	192	4	0	143
i11	196	4	0	127
i11	200	23	0	143
i11	231	13	0	170
i11	248	2	0	214
i11	250	4	0	190
i11	255	30	0	214
i11	289	8	0	190
i11	297	9	0	170
i11	321	24	0	151
i11	449	12	0	190
i11	477	28	0	160
i11	509	16	0	143
i11	531	24	0	127
i11	561	7	0	113
i11	569	8	0	127
i11	591	30	0	143
i11	624	10	0	170
i11	637	2	0	214
i11	639	4	0	190
i11	645	34	0	214
i11	712	13	0	160
i11	725	7	0	170
i11	739	11	0	190
i11	755	5	0	202
i11	760	3	0	190
i11	763	6	0	202
i11	769	7	0	226
i11	781	6	0	202
i11	799	10	0	190
i11	851	7	0	95
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	69	120	0	381
i12	189	52	0	339
i12	243	56	0	428
i12	314	27	0	360
i12	468	58	0	381
i12	529	53	0	320
i12	585	36	0	339
i12	636	42	0	508
i12	706	5	0	381
i12	711	21	0	254
i12	739	3	0	320
i12	754	12	0	403
i12	767	7	0	508
i12	781	3	0	339
i12	798	6	0	381
i12	838	8	0	762
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	76	109	0	254
i13	194	50	0	285
i13	252	59	0	254
i13	321	21	0	226
i13	475	52	0	254
i13	529	53	0	320
i13	590	27	0	226
i13	637	4	0	214
i13	645	34	0	214
i13	767	7	0	226
i13	846	6	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	918	0.9	1.0	1.0
