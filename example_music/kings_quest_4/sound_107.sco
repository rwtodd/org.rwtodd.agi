;; AGI Sound Resource 107 (Volume 1 Offset 91278)


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
i11	27	113	0	160
i11	145	57	0	190
i11	202	21	0	170
i11	223	39	0	160
i11	263	112	0	143
i11	379	84	0	214
i11	499	116	0	160
i11	616	62	0	190
i11	678	19	0	170
i11	697	36	0	160
i11	737	1	0	170
i11	739	5	0	160
i11	744	153	0	170
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	32	18	0	320
i12	50	18	0	339
i12	68	18	0	381
i12	87	18	0	428
i12	107	18	0	381
i12	126	22	0	339
i12	148	104	0	320
i12	254	5	0	339
i12	260	4	0	320
i12	264	18	0	339
i12	282	17	0	381
i12	300	19	0	428
i12	320	20	0	508
i12	340	20	0	428
i12	360	19	0	381
i12	382	102	0	339
i12	494	3	0	320
i12	497	6	0	285
i12	503	18	0	320
i12	521	18	0	339
i12	541	17	0	381
i12	561	19	0	508
i12	580	19	0	381
i12	600	21	0	339
i12	621	38	0	320
i12	660	31	0	285
i12	696	3	0	320
i12	700	2	0	285
i12	702	37	0	320
i12	739	59	0	339
i12	798	20	0	381
i12	818	33	0	428
i12	855	78	0	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	30	32	0	190
i13	68	38	0	160
i13	106	36	0	127
i13	147	19	0	381
i13	166	17	0	320
i13	185	29	0	254
i13	260	21	0	508
i13	281	20	0	339
i13	302	26	0	254
i13	341	37	0	285
i13	378	20	0	508
i13	399	18	0	339
i13	418	18	0	254
i13	436	23	0	214
i13	459	15	0	170
i13	501	19	0	381
i13	522	19	0	320
i13	541	38	0	254
i13	579	29	0	190
i13	620	20	0	762
i13	641	10	0	381
i13	660	18	0	381
i13	678	17	0	320
i13	698	16	0	254
i13	739	19	0	428
i13	758	19	0	339
i13	778	18	0	285
i13	798	18	0	214
i13	818	33	0	190
i13	856	71	0	143
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	993	0.9	1.0	1.0
