;; AGI Sound Resource 195 (Volume 3 Offset 232075)


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
i11	0	3	0	560
i11	12	6	0	800
i11	18	6	0	608
i11	24	6	0	464
i11	30	6	0	416
i11	36	6	0	480
i11	42	6	0	608
i11	48	6	0	784
i11	63	9	0	800
i11	84	6	0	736
i11	93	9	0	752
i11	102	3	0	832
i11	105	9	0	912
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
i99	0	174	0.9	1.0	1.0
