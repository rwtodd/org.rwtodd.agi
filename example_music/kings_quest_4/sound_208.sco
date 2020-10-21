;; AGI Sound Resource 208 (Volume 0 Offset 93752)


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
i11	15	54	0	90
i11	69	21	0	80
i11	90	27	0	76
i11	118	4	0	80
i11	122	6	0	76
i11	128	34	0	80
i11	162	38	0	90
i11	200	38	0	101
i11	242	57	0	101
i11	299	18	0	90
i11	317	24	0	80
i11	347	4	0	90
i11	351	6	0	80
i11	357	119	0	90
i11	476	54	0	113
i11	530	18	0	101
i11	549	38	0	90
i11	588	36	0	95
i11	624	35	0	90
i11	660	41	0	80
i11	703	92	0	71
i11	795	18	0	80
i11	813	17	0	71
i11	832	21	0	90
i11	854	83	0	60
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	15	52	0	302
i12	67	21	0	269
i12	88	74	0	240
i12	162	17	0	269
i12	179	17	0	302
i12	196	18	0	320
i12	214	22	0	360
i12	236	59	0	403
i12	295	22	0	360
i12	317	75	0	320
i12	392	19	0	302
i12	411	19	0	320
i12	430	18	0	360
i12	448	21	0	403
i12	469	60	0	453
i12	529	19	0	403
i12	548	45	0	360
i12	593	28	0	381
i12	621	39	0	360
i12	660	43	0	320
i12	703	153	0	360
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	20	49	0	180
i13	69	22	0	160
i13	91	75	0	151
i13	166	15	0	135
i13	181	17	0	151
i13	198	19	0	160
i13	217	25	0	180
i13	242	148	0	202
i13	392	18	0	180
i13	410	20	0	202
i13	430	20	0	226
i13	450	22	0	240
i13	472	49	0	226
i13	529	19	0	202
i13	548	39	0	180
i13	587	39	0	190
i13	626	34	0	180
i13	660	44	0	160
i13	704	91	0	143
i13	820	20	0	180
i13	840	20	0	190
i13	860	19	0	180
i13	879	20	0	160
i13	899	33	0	143
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	997	0.9	1.0	1.0
