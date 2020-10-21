;; AGI Sound Resource 9 (Volume 0 Offset 20244)


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
i11	0	6	0	896
i11	6	6	0	928
i11	12	6	0	896
i11	18	6	0	928
i11	24	6	0	896
i11	30	6	0	928
i11	36	6	0	896
i11	42	6	0	928
i11	48	6	0	896
i11	54	6	0	928
i11	60	6	0	896
i11	66	6	0	928
i11	72	6	0	896
i11	78	6	0	928
i11	84	6	0	896
i11	90	6	0	928
i11	96	6	0	896
i11	102	6	0	928
i11	108	6	0	896
i11	114	6	0	928
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	6	0	912
i12	6	6	0	944
i12	12	6	0	912
i12	18	6	0	944
i12	24	6	0	912
i12	30	6	0	944
i12	36	6	0	912
i12	42	6	0	944
i12	48	6	0	912
i12	54	6	0	944
i12	60	6	0	912
i12	66	6	0	944
i12	72	6	0	912
i12	78	6	0	944
i12	84	6	0	912
i12	90	6	0	944
i12	96	6	0	912
i12	102	6	0	944
i12	108	6	0	912
i12	114	6	0	944
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	120	4	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	180	0.9	1.0	1.0
