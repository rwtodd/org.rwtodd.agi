;; AGI Sound Resource 159 (Volume 3 Offset 292138)


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
i11	9	3	0	864
i11	18	3	0	864
i11	27	3	0	864
i11	36	3	0	864
i11	45	3	0	864
i11	54	3	0	864
i11	63	3	0	864
i11	72	9	0	864
i11	87	6	0	848
i11	93	6	0	864
i11	99	6	0	880
i11	105	6	0	896
i11	111	6	0	912
i11	117	6	0	928
i11	123	6	0	944
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
