;; AGI Sound Resource 78 (Volume 2 Offset 121825)


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
i11	0	6	0	848
i11	6	3	0	896
i11	15	6	0	848
i11	21	3	0	896
i11	30	6	0	848
i11	36	3	0	896
i11	45	6	0	848
i11	51	3	0	896
i11	60	6	0	848
i11	66	3	0	896
i11	75	6	0	848
i11	81	3	0	896
i11	90	6	0	848
i11	96	3	0	896
i11	105	6	0	848
i11	111	3	0	896
i11	120	6	0	848
i11	126	3	0	896
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
i99	0	189	0.9	1.0	1.0
