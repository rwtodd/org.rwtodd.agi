;; AGI Sound Resource 23 (Volume 1 Offset 151879)


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
i11	0	6	0	800
i11	6	6	0	784
i11	12	6	0	768
i11	18	6	0	752
i11	24	6	0	736
i11	30	3	6	736
i11	33	6	0	720
i11	39	6	0	704
i11	45	6	0	688
i11	51	6	0	672
i11	57	6	0	656
i11	63	3	6	640
i11	66	6	0	640
i11	72	6	0	624
i11	78	6	0	608
i11	84	3	0	592
i11	87	6	0	576
i11	93	6	0	560
i11	99	6	0	544
i11	105	12	0	528
i11	117	15	0	512
i11	132	87	0	496
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	30	3	13	1008
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	279	0.9	1.0	1.0
