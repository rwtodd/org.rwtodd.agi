;; AGI Sound Resource 21 (Volume 0 Offset 48261)


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
i11	0	3	0	99
i11	3	3	0	106
i11	6	3	0	118
i11	9	6	0	115
i11	15	3	0	116
i11	18	3	0	133
i11	21	3	0	99
i11	24	3	0	120
i11	27	3	0	106
i11	30	3	0	122
i11	33	3	0	106
i11	36	3	0	123
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	100
i12	3	3	0	107
i12	6	3	0	120
i12	9	3	0	118
i12	12	3	0	116
i12	15	3	0	118
i12	18	3	0	134
i12	21	3	0	100
i12	24	3	0	104
i12	27	3	0	122
i12	30	6	0	106
i12	36	3	0	122
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	134
i13	3	3	0	140
i13	6	3	0	118
i13	9	3	0	120
i13	12	3	3	133
i13	15	3	0	102
i13	18	3	0	119
i13	21	3	3	134
i13	24	3	0	118
i13	27	3	0	140
i13	30	3	3	108
i13	33	3	0	108
i13	36	3	0	124
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	99	0.9	1.0	1.0
