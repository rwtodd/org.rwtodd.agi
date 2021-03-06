;; AGI Sound Resource 4 (Volume 0 Offset 108840)


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
i11	0	3	0	1008
i11	3	3	0	976
i11	6	3	0	1008
i11	12	3	0	976
i11	15	3	0	992
i11	18	3	0	944
i11	24	3	0	1008
i11	27	3	0	992
i11	30	3	0	912
i11	33	3	0	992
i11	39	3	0	848
i11	42	3	0	976
i11	45	3	0	896
i11	51	3	0	992
i11	54	3	0	1008
i11	57	3	0	976
i11	63	3	0	944
i11	66	3	0	880
i11	69	3	0	912
i11	72	3	0	992
i11	78	3	0	1008
i11	81	3	0	944
i11	84	3	0	864
i11	87	3	0	976
i11	90	3	0	896
i11	93	3	0	1008
i11	99	3	0	944
i11	102	3	0	976
i11	105	3	0	960
i11	108	3	0	992
i11	111	3	0	1008
i11	117	3	0	976
i11	120	3	0	928
i11	123	3	0	1008
i11	126	3	0	992
i11	129	3	0	1008
i11	132	3	0	992
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
i99	0	195	0.9	1.0	1.0
