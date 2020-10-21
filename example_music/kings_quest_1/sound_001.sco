;; AGI Sound Resource 1 (Volume 0 Offset 43628)


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
i11	0	12	0	185
i11	12	12	0	165
i11	24	12	0	156
i11	36	12	0	165
i11	48	12	0	185
i11	72	102	0	131
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	12	0	371
i12	12	12	0	330
i12	24	12	0	312
i12	36	12	0	330
i12	48	12	0	371
i12	72	102	0	220
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	12	0	742
i13	12	12	0	661
i13	24	12	0	624
i13	36	12	0	661
i13	48	12	0	742
i13	72	102	0	371
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	234	0.9	1.0	1.0
