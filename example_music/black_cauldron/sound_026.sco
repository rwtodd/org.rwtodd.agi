;; AGI Sound Resource 26 (Volume 2 Offset 91552)


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
i11	0	108	0	92
i11	108	36	0	98
i11	144	144	0	131
i11	288	108	0	110
i11	396	36	0	116
i11	432	147	0	165
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	108	0	116
i12	108	36	0	123
i12	144	144	0	165
i12	288	108	0	139
i12	396	36	0	147
i12	432	147	0	208
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	108	0	139
i13	108	36	0	147
i13	144	144	0	196
i13	288	108	0	165
i13	396	36	0	175
i13	432	147	0	247
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	639	0.9	1.0	1.0
