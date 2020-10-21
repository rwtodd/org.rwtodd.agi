;; AGI Sound Resource 31 (Volume 0 Offset 23811)


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
i11	0	66	0	381
i11	69	23	0	320
i11	94	22	0	339
i11	116	19	0	381
i11	135	42	0	480
i11	180	42	0	508
i11	225	37	0	254
i11	264	5	0	226
i11	269	6	0	202
i11	275	4	0	190
i11	310	3	0	762
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	83	0	762
i12	90	45	0	762
i12	136	43	0	571
i12	180	42	0	679
i12	225	42	0	1017
i12	270	11	0	762
i12	310	5	0	762
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	22	0	480
i13	23	22	0	508
i13	48	31	0	480
i13	92	41	0	480
i13	136	42	0	762
i13	180	43	0	807
i13	225	41	0	571
i13	270	11	0	641
i13	311	6	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	377	0.9	1.0	1.0
