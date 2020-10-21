;; AGI Sound Resource 47 (Volume 2 Offset 196519)


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
i11	12	12	0	160
i11	24	12	0	151
i11	36	11	0	127
i11	47	12	0	113
i11	59	23	0	127
i11	82	23	0	508
i11	105	11	0	160
i11	116	12	0	151
i11	128	11	0	127
i11	139	12	0	113
i11	151	23	0	127
i11	174	14	0	762
i11	197	12	0	160
i11	209	11	0	151
i11	220	12	0	127
i11	232	11	0	113
i11	243	12	0	160
i11	255	11	0	151
i11	266	12	0	127
i11	278	12	0	113
i11	290	11	0	160
i11	301	12	0	151
i11	313	11	0	127
i11	324	12	0	113
i11	336	23	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	12	11	0	641
i12	23	12	0	605
i12	35	12	0	508
i12	47	11	0	453
i12	58	23	0	508
i12	104	13	0	762
i12	127	13	0	762
i12	151	12	0	762
i12	196	12	0	641
i12	208	12	0	605
i12	220	11	0	508
i12	231	12	0	453
i12	243	13	0	762
i12	266	13	0	762
i12	289	12	0	641
i12	301	11	0	605
i12	312	12	0	508
i12	324	11	0	453
i12	335	13	0	508
i12	358	15	0	508
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	11	13	0	762
i13	35	12	0	762
i13	58	12	0	762
i13	81	12	0	762
i13	196	13	0	762
i13	219	13	0	762
i13	289	12	0	762
i13	312	12	0	762
i13	335	13	0	762
i13	358	13	0	762
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	433	0.9	1.0	1.0
