;; AGI Sound Resource 10 (Volume 1 Offset 826)


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
i11	15	3	0	896
i11	18	6	0	928
i11	24	6	0	896
i11	33	3	0	928
i11	36	6	0	896
i11	42	6	0	928
i11	48	6	0	896
i11	57	3	0	928
i11	66	3	0	896
i11	72	3	0	928
i11	75	3	0	896
i11	81	6	0	928
i11	93	6	0	928
i11	99	6	0	896
i11	105	3	0	928
i11	114	3	0	896
i11	117	6	0	928
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	6	0	912
i12	6	6	0	944
i12	15	3	0	912
i12	18	6	0	944
i12	24	6	0	912
i12	33	3	0	944
i12	36	6	0	912
i12	42	6	0	944
i12	48	6	0	912
i12	57	3	0	944
i12	66	3	0	912
i12	72	3	0	944
i12	75	6	0	912
i12	81	6	0	944
i12	93	6	0	944
i12	99	6	0	912
i12	105	3	0	944
i12	114	3	0	912
i12	117	6	0	944
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	12	4	64
i21	15	15	4	64
i21	33	21	4	64
i21	57	3	4	64
i21	66	3	4	64
i21	72	15	4	64
i21	93	15	4	64
i21	114	9	4	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	183	0.9	1.0	1.0
