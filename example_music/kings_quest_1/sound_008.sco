;; AGI Sound Resource 8 (Volume 0 Offset 45583)


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
i11	0	6	0	144
i11	6	6	0	148
i11	12	6	0	144
i11	18	6	0	128
i11	24	12	0	144
i11	36	6	0	227
i11	42	6	0	216
i11	48	6	0	192
i11	54	6	0	176
i11	60	6	0	192
i11	66	6	0	224
i11	72	6	0	192
i11	84	6	0	141
i11	90	9	0	125
i11	99	30	0	112
i11	129	6	0	125
i11	135	6	0	141
i11	141	6	0	125
i11	147	6	0	112
i11	153	21	0	125
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	174	0	288
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	234	0.9	1.0	1.0
