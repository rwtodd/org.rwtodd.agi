;; AGI Sound Resource 17 (Volume 1 Offset 376546)


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
i11	54	4	0	160
i11	58	8	0	127
i11	69	4	0	135
i11	78	6	0	127
i11	89	8	0	95
i11	112	7	0	107
i11	158	10	0	160
i11	169	7	0	170
i11	179	6	0	160
i11	197	2	0	113
i11	199	18	0	107
i11	221	25	0	120
i11	301	5	0	160
i11	306	11	0	127
i11	320	10	0	143
i11	334	14	0	160
i11	349	7	0	214
i11	362	11	0	240
i11	378	33	0	143
i11	466	76	0	160
i11	543	4	0	320
i11	547	4	0	254
i11	551	4	0	214
i11	555	3	0	160
i11	558	4	0	214
i11	562	4	0	254
i11	566	15	0	320
i11	607	25	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	42	6	0	320
i12	48	7	0	254
i12	76	53	0	254
i12	149	22	0	381
i12	178	8	0	381
i12	193	53	0	403
i12	296	21	0	508
i12	328	25	0	539
i12	358	16	0	571
i12	393	22	0	339
i12	457	26	0	403
i12	483	19	0	381
i12	502	17	0	403
i12	520	23	0	428
i12	599	8	0	641
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	49	7	0	214
i13	99	27	0	180
i13	152	4	0	320
i13	156	14	0	240
i13	196	15	0	254
i13	218	28	0	285
i13	300	19	0	320
i13	330	24	0	320
i13	360	21	0	320
i13	381	4	0	302
i13	392	20	0	240
i13	459	4	0	214
i13	463	8	0	202
i13	483	2	0	240
i13	485	15	0	226
i13	500	22	0	240
i13	522	21	0	254
i13	603	18	0	180
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	692	0.9	1.0	1.0
