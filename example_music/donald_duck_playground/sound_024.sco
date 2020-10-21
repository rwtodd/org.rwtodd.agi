;; AGI Sound Resource 24 (Volume 0 Offset 35362)


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
i11	0	18	0	165
i11	18	18	0	139
i11	36	18	0	208
i11	54	18	0	175
i11	72	18	0	165
i11	90	18	0	139
i11	108	18	0	208
i11	126	18	0	175
i11	144	18	0	165
i11	162	18	0	139
i11	180	18	0	208
i11	198	18	0	175
i11	216	12	0	165
i11	228	6	0	175
i11	234	12	0	185
i11	246	6	0	196
i11	252	21	0	208
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	36	2	278
i12	36	36	2	350
i12	72	36	2	278
i12	108	36	2	350
i12	144	36	2	278
i12	180	36	2	350
i12	216	12	2	278
i12	228	6	2	294
i12	234	18	2	312
i12	252	21	2	330
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	36	1	416
i13	36	36	1	525
i13	72	36	1	416
i13	108	36	1	525
i13	144	36	1	416
i13	180	36	1	525
i13	216	12	1	556
i13	228	6	1	525
i13	234	12	1	495
i13	246	6	1	441
i13	252	21	1	416
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	333	0.9	1.0	1.0
