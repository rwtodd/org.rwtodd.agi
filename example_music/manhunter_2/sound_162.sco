;; AGI Sound Resource 162 (Volume 3 Offset 38241)


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
i11	78	3	0	560
i11	81	3	0	496
i11	84	3	0	432
i11	87	3	0	368
i11	90	3	0	80
i11	93	3	0	352
i11	96	3	0	48
i11	99	3	0	288
i11	102	3	0	80
i11	105	3	0	224
i11	108	3	0	128
i11	111	3	0	32
i11	114	3	0	272
i11	117	3	0	192
i11	120	3	0	16
i11	123	3	0	240
i11	126	3	0	112
i11	129	3	0	224
i11	132	3	0	64
i11	135	3	0	176
i11	138	3	0	32
i11	141	3	0	144
i11	144	3	0	80
i11	147	3	0	16
i11	150	3	0	192
i11	153	3	0	64
i11	156	3	0	144
i11	159	3	0	64
i11	162	3	0	320
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
i99	0	225	0.9	1.0	1.0
