;; AGI Sound Resource 198 (Volume 3 Offset 246492)


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
i11	0	3	0	976
i11	3	3	0	928
i11	6	3	0	256
i11	9	3	0	176
i11	12	3	0	928
i11	15	3	0	32
i11	18	3	0	912
i11	21	3	0	16
i11	24	3	0	896
i11	27	3	0	48
i11	30	3	0	944
i11	33	3	0	16
i11	36	3	0	928
i11	39	3	0	80
i11	42	3	0	176
i11	45	3	0	928
i11	48	3	0	240
i11	51	3	0	384
i11	54	3	0	928
i11	57	3	0	896
i11	60	9	0	960
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
i99	0	129	0.9	1.0	1.0
