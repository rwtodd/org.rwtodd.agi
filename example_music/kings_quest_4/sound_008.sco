;; AGI Sound Resource 8 (Volume 1 Offset 375030)


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
i11	10	4	0	127
i11	14	4	0	113
i11	18	11	0	127
i11	29	9	0	143
i11	38	7	0	127
i11	48	7	0	113
i11	60	19	0	107
i11	81	4	0	127
i11	101	5	0	113
i11	106	8	0	107
i11	114	8	0	113
i11	126	8	0	127
i11	134	10	0	113
i11	146	8	0	143
i11	157	9	0	127
i11	168	4	0	113
i11	194	9	0	127
i11	203	11	0	143
i11	214	9	0	127
i11	224	11	0	113
i11	236	8	0	107
i11	258	6	0	127
i11	284	20	0	113
i11	304	10	0	127
i11	314	8	0	113
i11	326	71	0	143
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	9	3	1	508
i12	12	69	1	339
i12	97	66	1	339
i12	182	61	1	508
i12	258	5	1	508
i12	275	120	1	571
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	17	80	1	254
i13	103	58	1	226
i13	185	6	1	339
i13	191	77	1	254
i13	283	103	1	285
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	457	0.9	1.0	1.0
