;; AGI Sound Resource 18 (Volume 1 Offset 376926)


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
i11	48	16	0	127
i11	66	6	0	143
i11	72	6	0	160
i11	78	6	0	170
i11	84	10	0	190
i11	96	5	0	214
i11	110	7	0	190
i11	123	5	0	170
i11	135	6	0	160
i11	148	5	0	170
i11	161	6	0	190
i11	173	6	0	214
i11	185	8	0	190
i11	209	4	0	254
i11	243	9	0	190
i11	261	9	0	190
i11	272	7	0	190
i11	284	9	0	190
i11	294	9	0	190
i11	306	7	0	190
i11	316	15	0	190
i11	334	8	0	190
i11	346	8	0	190
i11	358	10	0	190
i11	372	10	0	190
i11	385	10	0	190
i11	438	20	0	127
i11	475	8	0	143
i11	486	8	0	160
i11	496	6	0	170
i11	508	9	0	190
i11	528	10	0	214
i11	550	7	0	170
i11	564	4	0	190
i11	568	5	0	170
i11	573	11	0	190
i11	594	7	0	170
i11	616	14	0	160
i11	634	4	0	170
i11	638	4	0	160
i11	642	8	0	170
i11	664	8	0	214
i11	690	8	0	170
i11	794	6	0	254
i11	800	117	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	36	7	2	381
i12	43	4	2	285
i12	47	23	2	190
i12	75	3	2	226
i12	98	5	2	381
i12	110	5	2	339
i12	123	3	2	320
i12	135	4	2	254
i12	147	6	2	285
i12	159	5	2	320
i12	173	3	2	403
i12	440	10	2	381
i12	463	8	2	320
i12	485	5	2	254
i12	508	7	2	381
i12	528	4	2	320
i12	549	6	2	254
i12	573	7	2	381
i12	592	5	2	320
i12	616	7	2	254
i12	643	9	2	508
i12	663	5	2	339
i12	689	6	2	285
i12	776	7	2	762
i12	783	4	2	508
i12	791	121	2	381
i12	1018	1	2	0
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	37	5	2	320
i13	42	3	2	254
i13	790	122	2	320
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1079	0.9	1.0	1.0
