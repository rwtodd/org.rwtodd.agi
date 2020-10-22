;; AGI Sound Resource 32 (Volume 2 Offset 143698)


t 0 3600 ;; AGI runs in 1/60th second ticks

; set up the instruments
i 1  0  0  1   0 10; music box
i 1  0  0  2   0 10; music box
i 1  0  0  3   0 11; vibraphone

; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	45	26	0	208
i11	75	13	0	139
i11	90	28	0	185
i11	120	13	0	139
i11	135	30	0	165
i11	165	15	0	185
i11	180	41	0	208
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	46	15	2	208
i12	61	15	2	220
i12	76	15	2	247
i12	91	15	2	278
i12	106	15	2	247
i12	121	15	2	220
i12	136	20	2	208
i12	166	9	2	278
i12	181	41	2	330
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	45	26	1	208
i13	75	13	1	139
i13	90	28	1	185
i13	120	13	1	139
i13	135	30	1	165
i13	165	15	1	185
i13	180	41	1	208
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	282	0.9	1.0	1.0
