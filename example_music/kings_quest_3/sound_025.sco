;; AGI Sound Resource 25 (Volume 0 Offset 22465)


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
i11	39	59	0	208
i11	98	20	0	156
i11	118	19	0	165
i11	137	15	0	139
i11	157	39	0	196
i11	196	33	0	208
i11	235	18	0	104
i11	255	8	0	92
i11	265	8	0	82
i11	275	21	0	78
i11	314	10	0	156
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	39	39	2	312
i12	78	77	2	416
i12	157	73	2	330
i12	235	20	2	416
i12	256	8	2	467
i12	266	7	2	556
i12	274	21	2	624
i12	313	9	2	624
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	59	26	1	262
i13	98	25	1	262
i13	235	20	1	330
i13	255	10	1	278
i13	265	10	1	220
i13	275	19	1	247
i13	314	10	1	312
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	384	0.9	1.0	1.0
