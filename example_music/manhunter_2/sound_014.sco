;; AGI Sound Resource 14 (Volume 1 Offset 104843)


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
i11	0	3	0	768
i11	3	3	0	256
i11	6	3	0	224
i11	9	3	0	192
i11	12	3	0	128
i11	15	3	0	80
i11	18	3	0	32
i11	21	3	0	16
i11	42	3	3	688
i11	45	3	3	608
i11	60	3	3	656
i11	63	3	3	720
i11	66	3	3	608
i11	81	3	3	576
i11	84	3	3	672
i11	87	3	3	624
i11	102	3	3	640
i11	105	3	3	704
i11	108	3	3	656
i11	123	3	3	640
i11	126	3	3	704
i11	129	3	3	608
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
i99	0	192	0.9	1.0	1.0
