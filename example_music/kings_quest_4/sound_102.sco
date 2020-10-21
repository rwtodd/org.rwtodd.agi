;; AGI Sound Resource 102 (Volume 1 Offset 90118)


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
i11	33	40	0	160
i11	73	10	0	170
i11	83	22	0	190
i11	109	29	0	190
i11	138	32	0	170
i11	170	33	0	160
i11	210	5	0	170
i11	216	3	0	160
i11	219	42	0	170
i11	263	12	0	190
i11	275	20	0	214
i11	309	56	0	214
i11	420	42	0	160
i11	469	9	0	170
i11	478	39	0	190
i11	518	32	0	190
i11	550	34	0	170
i11	584	23	0	160
i11	610	4	0	170
i11	614	5	0	160
i11	619	72	0	170
i11	695	19	0	214
i11	735	132	0	254
i11	889	48	0	80
i11	937	13	0	85
i11	950	26	0	95
i11	982	36	0	95
i11	1018	33	0	85
i11	1051	44	0	80
i11	1096	47	0	85
i11	1144	16	0	107
i11	1160	29	0	127
i11	1200	38	0	127
i11	1238	116	0	107
i11	1355	3	0	95
i11	1360	4	0	85
i11	1364	42	0	95
i11	1406	15	0	107
i11	1421	29	0	120
i11	1456	3	0	107
i11	1460	37	0	120
i11	1499	45	0	160
i11	1544	81	0	120
i11	1626	162	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	24	156	0	381
i12	203	161	0	508
i12	403	74	0	381
i12	619	79	0	508
i12	722	147	0	381
i12	889	28	0	381
i12	920	166	0	254
i12	1097	31	0	508
i12	1128	35	0	339
i12	1165	19	0	254
i12	1202	102	0	254
i12	1352	177	0	480
i12	1622	32	0	381
i12	1654	27	0	320
i12	1681	90	0	190
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	980	111	0	381
i13	1094	103	0	428
i13	1199	72	0	508
i13	1354	171	0	190
i13	1629	61	0	254
i13	1716	67	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1848	0.9	1.0	1.0
