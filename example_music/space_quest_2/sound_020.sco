;; AGI Sound Resource 20 (Volume 2 Offset 3981)


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
i11	3	3	0	912
i11	6	3	0	944
i11	9	3	0	816
i11	12	3	0	848
i11	15	3	0	752
i11	18	3	0	800
i11	21	3	0	736
i11	24	3	0	784
i11	27	3	0	704
i11	30	3	0	752
i11	33	3	0	704
i11	36	3	0	784
i11	39	3	0	752
i11	42	3	0	848
i11	45	3	0	800
i11	48	3	0	928
i11	51	3	0	896
i11	54	3	0	976
i11	57	3	0	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	992
i12	3	3	0	880
i12	6	3	0	992
i12	9	3	0	864
i12	12	3	0	928
i12	15	3	0	832
i12	18	3	0	896
i12	21	3	0	784
i12	24	3	0	720
i12	27	3	0	800
i12	30	3	0	720
i12	33	3	0	800
i12	36	3	0	736
i12	39	3	0	784
i12	42	3	0	880
i12	45	3	0	816
i12	48	3	0	928
i12	51	3	0	880
i12	54	3	0	992
i12	57	3	0	944
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	120	0.9	1.0	1.0
