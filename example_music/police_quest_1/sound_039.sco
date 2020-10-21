;; AGI Sound Resource 39 (Volume 1 Offset 137519)


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
i11	1	18	0	226
i11	19	12	0	214
i11	31	5	0	269
i11	40	6	0	360
i11	47	7	0	428
i11	54	10	0	360
i11	65	6	0	428
i11	71	16	0	320
i11	89	11	0	360
i11	101	7	0	428
i11	108	6	0	320
i11	120	6	0	428
i11	127	11	0	360
i11	144	21	0	226
i11	165	10	0	214
i11	177	6	0	269
i11	183	10	0	360
i11	194	5	0	428
i11	200	10	0	360
i11	210	6	0	428
i11	217	16	0	320
i11	235	12	0	360
i11	247	3	0	269
i11	254	12	0	269
i11	267	6	0	285
i11	274	13	0	269
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	20	13	0	539
i12	38	8	0	719
i12	56	11	0	539
i12	73	9	0	719
i12	91	8	0	539
i12	109	11	0	719
i12	127	8	0	539
i12	146	6	0	719
i12	152	7	0	641
i12	159	6	0	571
i12	166	13	0	539
i12	184	11	0	719
i12	201	8	0	539
i12	218	11	0	719
i12	237	8	0	539
i12	255	14	0	719
i12	275	10	0	539
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	18	10	0	360
i13	37	8	0	428
i13	55	9	0	360
i13	72	19	0	453
i13	91	15	0	428
i13	109	16	0	453
i13	127	12	0	428
i13	146	18	0	285
i13	164	9	0	360
i13	182	10	0	428
i13	201	8	0	360
i13	218	17	0	453
i13	237	17	0	428
i13	255	17	0	403
i13	274	11	0	428
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	347	0.9	1.0	1.0
