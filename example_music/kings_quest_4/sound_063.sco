;; AGI Sound Resource 63 (Volume 0 Offset 65915)


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
i11	6	8	0	95
i11	14	6	0	107
i11	20	6	0	95
i11	26	6	0	85
i11	32	7	0	95
i11	39	7	0	85
i11	46	6	0	80
i11	52	7	0	85
i11	59	6	0	80
i11	66	6	0	71
i11	72	6	0	80
i11	78	7	0	71
i11	85	7	0	64
i11	92	5	0	71
i11	97	7	0	64
i11	104	6	0	57
i11	110	7	0	64
i11	117	7	0	57
i11	125	36	0	53
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
i99	0	221	0.9	1.0	1.0
