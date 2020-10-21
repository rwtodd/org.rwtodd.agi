;; AGI Sound Resource 37 (Volume 2 Offset 551539)


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
i11	10	114	0	95
i11	135	6	0	101
i11	146	6	0	127
i11	157	6	0	127
i11	168	6	0	107
i11	180	121	0	95
i11	306	7	0	101
i11	318	4	0	127
i11	328	6	0	127
i11	338	6	0	107
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	10	11	2	508
i12	53	13	2	679
i12	95	12	2	508
i12	137	6	1	679
i12	148	6	1	571
i12	159	6	1	571
i12	169	6	1	679
i12	180	12	1	508
i12	222	15	1	679
i12	264	13	1	508
i12	307	6	1	679
i12	317	7	1	571
i12	328	7	1	571
i12	339	6	1	679
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	405	0.9	1.0	1.0
