;; AGI Sound Resource 26 (Volume 0 Offset 6927)


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
i11	0	3	0	640
i11	3	3	3	576
i11	6	3	3	496
i11	9	3	3	624
i11	12	3	3	704
i11	15	3	3	608
i11	18	3	3	544
i11	21	3	3	640
i11	24	3	3	720
i11	27	3	3	784
i11	30	3	3	688
i11	33	3	3	624
i11	36	3	3	672
i11	39	3	3	768
i11	42	3	3	688
i11	45	3	3	752
i11	48	3	3	880
i11	51	3	3	912
i11	54	3	3	848
i11	57	3	3	800
i11	60	3	3	880
i11	63	3	3	928
i11	66	3	3	960
i11	69	3	3	896
i11	72	3	3	944
i11	75	3	3	992
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
i99	0	138	0.9	1.0	1.0
