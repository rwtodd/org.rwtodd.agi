;; AGI Sound Resource 20 (Volume 0 Offset 63829)


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
i11	106	3	0	64
i11	114	5	0	64
i11	122	5	0	64
i11	130	3	0	67
i11	138	5	0	71
i11	146	10	0	95
i11	163	96	0	64
i11	268	6	0	67
i11	276	6	0	71
i11	292	67	0	101
i11	430	3	0	64
i11	438	3	0	64
i11	446	4	0	64
i11	454	4	0	64
i11	463	3	0	64
i11	471	3	0	64
i11	479	3	0	64
i11	487	4	0	67
i11	495	4	0	71
i11	503	4	0	95
i11	511	77	0	50
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	25	8	3	339
i12	41	8	3	508
i12	57	8	3	453
i12	73	8	3	339
i12	89	8	3	508
i12	106	8	3	453
i12	122	8	3	339
i12	138	8	3	508
i12	154	8	3	453
i12	170	9	3	339
i12	187	8	3	508
i12	203	8	3	453
i12	219	8	3	339
i12	235	8	3	508
i12	252	8	3	453
i12	268	8	3	339
i12	284	8	3	508
i12	300	8	3	453
i12	316	8	3	339
i12	333	8	3	508
i12	349	8	3	453
i12	365	8	3	339
i12	381	8	3	508
i12	397	9	3	453
i12	414	8	3	339
i12	430	8	3	508
i12	446	8	3	453
i12	462	8	3	339
i12	478	9	3	508
i12	495	8	3	453
i12	511	8	3	339
i12	527	8	3	508
i12	543	8	3	453
i12	560	8	3	339
i12	576	8	3	508
i12	592	8	3	453
i12	608	8	3	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	203	98	3	381
i13	301	8	3	403
i13	309	20	3	428
i13	333	92	3	539
i13	446	17	3	381
i13	463	8	3	403
i13	471	10	3	428
i13	487	36	3	539
i13	552	16	3	381
i13	568	8	3	403
i13	576	8	3	428
i13	584	39	3	539
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	683	0.9	1.0	1.0
