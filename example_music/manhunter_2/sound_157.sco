;; AGI Sound Resource 157 (Volume 3 Offset 292766)


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
i11	0	6	0	880
i11	6	6	0	864
i11	12	6	0	848
i11	18	6	0	832
i11	24	24	0	816
i11	48	6	0	832
i11	54	6	0	848
i11	60	24	0	864
i11	84	6	0	848
i11	90	6	0	832
i11	96	18	0	816
i11	114	6	0	832
i11	120	6	0	848
i11	126	6	0	864
i11	132	18	0	880
i11	150	6	0	864
i11	156	6	0	848
i11	162	12	0	832
i11	174	6	0	848
i11	180	6	0	864
i11	186	12	0	880
i11	198	6	0	864
i11	204	6	0	848
i11	210	6	0	832
i11	216	30	0	816
i11	246	6	0	832
i11	252	6	0	848
i11	258	6	0	864
i11	264	3	0	880
i11	267	3	0	896
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
i99	0	330	0.9	1.0	1.0
