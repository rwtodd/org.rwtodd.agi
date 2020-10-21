;; AGI Sound Resource 11 (Volume 0 Offset 63617)


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
i11	0	24	0	42
i11	24	8	0	48
i11	32	21	0	53
i11	53	8	0	57
i11	61	23	0	64
i11	84	9	0	71
i11	93	23	0	80
i11	118	8	0	85
i11	127	127	0	90
i11	254	6	0	95
i11	260	6	0	90
i11	267	15	0	85
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	25	0	85
i12	25	7	0	95
i12	33	20	0	107
i12	53	9	0	113
i12	62	22	0	127
i12	84	10	0	143
i12	94	23	0	160
i12	118	9	0	170
i12	127	128	0	180
i12	255	5	0	190
i12	260	6	0	180
i12	267	15	0	170
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	23	0	339
i13	24	7	0	381
i13	31	22	0	428
i13	53	9	0	453
i13	62	21	0	508
i13	83	10	0	571
i13	94	22	0	641
i13	116	7	0	679
i13	126	129	0	719
i13	255	6	0	762
i13	261	5	0	719
i13	266	15	0	679
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	342	0.9	1.0	1.0
