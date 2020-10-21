;; AGI Sound Resource 108 (Volume 0 Offset 114737)


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
i11	0	3	0	496
i11	3	3	0	432
i11	6	3	0	368
i11	9	3	0	304
i11	12	3	0	272
i11	15	3	0	304
i11	18	3	0	368
i11	21	6	0	432
i11	27	3	0	368
i11	30	3	0	304
i11	33	3	0	240
i11	36	3	0	208
i11	39	3	0	240
i11	42	3	0	304
i11	45	6	0	368
i11	51	3	0	304
i11	54	3	0	240
i11	57	3	0	176
i11	60	3	0	112
i11	63	3	0	176
i11	66	3	0	240
i11	69	6	0	304
i11	75	3	0	240
i11	78	3	0	176
i11	81	3	0	112
i11	84	3	0	48
i11	87	3	0	112
i11	90	3	0	176
i11	93	3	0	240
i11	96	3	0	304
i11	99	3	0	960
i11	102	3	0	672
i11	105	3	0	832
i11	108	3	0	560
i11	111	3	0	736
i11	114	3	0	448
i11	117	3	0	592
i11	120	3	0	832
i11	123	3	0	912
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
i99	0	186	0.9	1.0	1.0
