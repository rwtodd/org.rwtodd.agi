;; AGI Sound Resource 11 (Volume 1 Offset 1212)


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
i11	0	3	0	864
i11	3	3	0	928
i11	6	3	0	1008
i11	9	3	2	1008
i11	12	3	5	1008
i11	15	3	2	1008
i11	18	3	0	1008
i11	21	3	2	1008
i11	24	3	5	1008
i11	27	3	2	1008
i11	30	6	0	1008
i11	36	6	2	1008
i11	42	6	4	1008
i11	48	6	2	1008
i11	54	9	0	1008
i11	63	9	2	1008
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
i99	0	132	0.9	1.0	1.0
