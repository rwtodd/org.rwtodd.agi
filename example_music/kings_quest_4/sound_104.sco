;; AGI Sound Resource 104 (Volume 1 Offset 90786)


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
i11	35	57	0	160
i11	92	9	0	170
i11	101	22	0	190
i11	126	28	0	190
i11	155	23	0	170
i11	187	35	0	160
i11	229	59	0	143
i11	288	9	0	160
i11	297	33	0	170
i11	337	58	0	170
i11	497	54	0	160
i11	551	8	0	170
i11	559	29	0	190
i11	591	38	0	190
i11	629	36	0	170
i11	665	33	0	190
i11	707	4	0	170
i11	711	37	0	190
i11	748	43	0	202
i11	791	53	0	226
i11	853	182	0	202
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	128	33	0	381
i12	161	27	0	339
i12	188	33	0	320
i12	229	57	0	285
i12	286	11	0	339
i12	297	36	0	428
i12	333	49	0	508
i12	459	20	0	95
i12	479	17	0	107
i12	496	131	0	120
i12	627	40	0	160
i12	667	41	0	120
i12	710	4	0	127
i12	714	6	0	120
i12	720	71	0	127
i12	791	44	0	143
i12	841	196	0	127
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	379	208	0	254
i13	589	125	0	240
i13	720	118	0	254
i13	852	184	0	170
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1097	0.9	1.0	1.0
