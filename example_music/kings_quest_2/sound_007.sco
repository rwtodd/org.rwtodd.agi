;; AGI Sound Resource 7 (Volume 0 Offset 40476)


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
i11	0	45	0	156
i11	48	33	0	156
i11	84	9	0	156
i11	96	45	0	156
i11	144	33	0	131
i11	180	9	0	139
i11	192	33	0	139
i11	228	9	0	156
i11	240	33	0	156
i11	276	9	0	165
i11	288	3	0	104
i11	294	3	0	104
i11	306	3	0	104
i11	318	3	0	131
i11	324	3	0	123
i11	336	6	0	156
i11	360	9	0	78
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	45	0	262
i12	48	33	0	262
i12	84	9	0	262
i12	96	45	0	262
i12	144	33	0	208
i12	180	9	0	208
i12	192	33	0	233
i12	228	9	0	233
i12	240	33	0	233
i12	276	9	0	233
i12	288	3	0	208
i12	294	3	0	208
i12	306	3	0	208
i12	318	3	0	262
i12	324	3	0	247
i12	336	6	0	312
i12	360	9	0	123
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	45	0	624
i13	48	33	0	624
i13	84	9	0	624
i13	96	45	0	624
i13	144	33	0	624
i13	180	9	0	416
i13	192	33	0	416
i13	228	9	0	416
i13	240	33	0	416
i13	276	9	0	416
i13	288	3	0	416
i13	294	3	0	416
i13	306	3	0	416
i13	318	3	0	525
i13	324	3	0	495
i13	336	6	0	624
i13	360	9	0	312
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	429	0.9	1.0	1.0
