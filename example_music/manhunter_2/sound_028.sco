;; AGI Sound Resource 28 (Volume 1 Offset 134621)


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
i11	0	6	3	576
i11	6	6	3	608
i11	12	6	3	576
i11	18	6	3	608
i11	24	6	3	576
i11	30	6	3	608
i11	36	6	3	576
i11	42	6	3	608
i11	48	6	3	576
i11	54	6	3	608
i11	60	6	3	576
i11	66	6	3	608
i11	72	6	3	576
i11	78	6	3	608
i11	84	6	3	576
i11	90	6	3	608
i11	96	6	3	576
i11	102	6	3	608
i11	108	6	3	576
i11	114	6	3	608
i11	120	6	3	576
i11	126	6	3	608
i11	132	6	3	576
i11	138	6	3	608
i11	144	6	3	576
i11	150	6	3	608
i11	156	6	3	576
i11	162	6	3	608
i11	168	6	3	576
i11	174	3	3	608
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	237	0.9	1.0	1.0
