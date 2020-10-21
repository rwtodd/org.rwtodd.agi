;; AGI Sound Resource 65 (Volume 1 Offset 2081)


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
i11	0	28	0	285
i11	37	32	0	214
i11	72	29	0	190
i11	110	13	0	170
i11	127	5	0	190
i11	136	5	0	214
i11	150	93	0	190
i11	260	10	0	170
i11	277	5	0	190
i11	287	6	0	214
i11	297	33	0	190
i11	334	6	0	170
i11	347	6	0	190
i11	360	4	0	170
i11	373	37	0	160
i11	412	10	0	135
i11	438	5	0	135
i11	449	123	0	143
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	147	38	0	571
i12	187	34	0	428
i12	227	29	0	381
i12	261	11	0	339
i12	278	5	0	381
i12	286	5	0	428
i12	296	42	0	381
i12	353	7	0	339
i12	371	23	0	320
i12	414	6	0	269
i12	443	5	0	339
i12	452	131	0	285
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	147	38	0	571
i13	187	35	0	428
i13	227	29	0	381
i13	261	11	0	339
i13	278	5	0	381
i13	286	5	0	428
i13	296	43	0	381
i13	353	7	0	339
i13	372	22	0	320
i13	414	6	0	269
i13	443	5	0	339
i13	452	131	0	285
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	643	0.9	1.0	1.0
