;; AGI Sound Resource 25 (Volume 1 Offset 152040)


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
i11	0	6	0	371
i11	6	6	0	393
i11	12	6	0	416
i11	18	6	0	441
i11	24	6	0	467
i11	60	12	0	58
i11	72	12	0	73
i11	84	12	0	92
i11	96	12	0	116
i11	144	6	0	92
i11	150	6	0	87
i11	156	6	0	82
i11	162	6	0	87
i11	168	6	0	92
i11	174	6	0	98
i11	180	12	0	104
i11	192	12	0	87
i11	204	12	0	147
i11	240	12	0	110
i11	252	12	0	185
i11	300	3	0	69
i11	303	15	0	65
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	60	12	0	110
i12	72	12	0	156
i12	84	12	0	175
i12	96	12	0	220
i12	300	3	0	495
i12	303	15	0	525
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	378	0.9	1.0	1.0
