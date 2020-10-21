;; AGI Sound Resource 8 (Volume 0 Offset 109034)


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
i11	0	12	0	944
i11	12	3	0	976
i11	15	3	0	1008
i11	18	12	0	896
i11	30	3	0	944
i11	33	3	0	1008
i11	36	12	0	832
i11	48	3	0	912
i11	51	3	0	992
i11	54	12	0	752
i11	66	3	0	864
i11	69	3	0	992
i11	72	36	0	240
i11	108	6	0	432
i11	114	6	0	624
i11	120	6	0	848
i11	126	6	0	944
i11	132	24	0	1008
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
i99	0	216	0.9	1.0	1.0
