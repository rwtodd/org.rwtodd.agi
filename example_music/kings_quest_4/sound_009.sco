;; AGI Sound Resource 9 (Volume 1 Offset 375260)


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
i11	70	9	0	127
i11	79	11	0	113
i11	92	22	0	107
i11	114	19	0	95
i11	137	2	0	113
i11	139	3	0	95
i11	142	63	0	113
i11	207	5	0	127
i11	212	7	0	113
i11	219	10	0	127
i11	230	45	0	143
i11	277	63	0	107
i11	340	22	0	127
i11	364	42	0	95
i11	406	31	0	85
i11	437	11	0	80
i11	450	5	0	85
i11	456	5	0	80
i11	461	121	0	85
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	18	9	2	170
i12	29	9	2	127
i12	38	13	2	107
i12	51	22	2	64
i12	73	15	2	107
i12	96	22	2	170
i12	118	32	2	127
i12	150	11	2	170
i12	161	9	2	143
i12	171	12	2	113
i12	185	15	2	57
i12	206	22	2	85
i12	228	15	2	113
i12	249	12	2	85
i12	281	10	2	214
i12	293	10	2	160
i12	303	13	2	127
i12	316	21	2	64
i12	337	22	2	80
i12	359	23	2	107
i12	382	22	2	127
i12	415	9	2	226
i12	426	12	2	170
i12	438	16	2	135
i12	454	24	2	85
i12	478	6	2	135
i12	508	34	2	57
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	6	118	2	254
i13	137	103	2	285
i13	269	111	2	320
i13	402	11	2	339
i13	413	9	2	226
i13	538	64	2	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	662	0.9	1.0	1.0
