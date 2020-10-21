;; AGI Sound Resource 9 (Volume 0 Offset 42338)


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
i11	0	6	0	139
i11	24	66	0	139
i11	96	6	0	139
i11	108	6	0	139
i11	120	6	0	156
i11	132	6	0	156
i11	144	6	0	175
i11	156	6	0	175
i11	168	6	0	156
i11	180	6	0	156
i11	192	6	0	139
i11	216	66	0	139
i11	288	6	0	139
i11	300	6	0	139
i11	312	6	0	156
i11	324	6	0	156
i11	336	6	0	175
i11	348	6	0	175
i11	360	6	0	156
i11	372	6	0	156
i11	384	6	0	104
i11	408	66	0	104
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	6	0	278
i12	24	66	0	278
i12	96	6	0	278
i12	108	6	0	278
i12	120	6	0	312
i12	132	6	0	312
i12	144	6	0	350
i12	156	6	0	350
i12	168	6	0	312
i12	180	6	0	312
i12	192	6	0	278
i12	216	66	0	278
i12	288	6	0	278
i12	300	6	0	278
i12	312	6	0	312
i12	324	6	0	312
i12	336	6	0	350
i12	348	6	0	350
i12	360	6	0	312
i12	372	6	0	312
i12	384	6	0	278
i12	408	66	0	278
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	6	0	416
i13	24	66	0	416
i13	96	90	0	416
i13	192	6	0	416
i13	216	66	0	416
i13	288	90	0	416
i13	384	6	0	416
i13	408	66	0	416
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	534	0.9	1.0	1.0
