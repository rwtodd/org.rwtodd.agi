;; AGI Sound Resource 82 (Volume 4 Offset 141540)


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
i11	5	16	0	170
i11	33	15	0	190
i11	60	12	0	226
i11	73	41	0	254
i11	115	15	0	285
i11	142	10	0	285
i11	169	10	0	339
i11	182	16	0	285
i11	210	15	0	254
i11	251	15	0	170
i11	278	15	0	190
i11	305	12	0	226
i11	319	27	0	254
i11	360	15	0	285
i11	387	15	0	285
i11	414	14	0	339
i11	428	27	0	285
i11	455	15	0	339
i11	496	15	0	170
i11	523	16	0	190
i11	550	11	0	226
i11	564	15	0	254
i11	605	15	0	285
i11	632	16	0	285
i11	660	13	0	339
i11	673	28	0	285
i11	701	14	0	254
i11	741	16	0	170
i11	769	15	0	190
i11	796	13	0	226
i11	809	16	0	254
i11	850	16	0	226
i11	878	15	0	226
i11	905	13	0	254
i11	918	28	0	226
i11	946	27	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	5	16	0	113
i12	33	15	0	127
i12	60	13	0	151
i12	73	41	0	170
i12	115	15	0	190
i12	142	15	0	190
i12	169	10	0	226
i12	182	16	0	190
i12	210	15	0	170
i12	251	15	0	113
i12	278	15	0	127
i12	305	14	0	151
i12	319	20	0	170
i12	496	15	0	113
i12	523	16	0	127
i12	551	13	0	160
i12	564	15	0	170
i12	605	15	0	190
i12	632	16	0	190
i12	660	13	0	226
i12	673	28	0	190
i12	701	15	0	170
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	5	15	0	339
i13	32	15	0	381
i13	113	16	0	571
i13	141	15	0	571
i13	250	15	0	339
i13	277	15	0	381
i13	359	15	0	571
i13	386	15	0	571
i13	495	15	0	339
i13	522	16	0	381
i13	604	16	0	571
i13	631	16	0	571
i13	659	13	0	679
i13	672	28	0	453
i13	700	13	0	508
i13	850	13	0	453
i13	877	15	0	453
i13	945	27	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1033	0.9	1.0	1.0
