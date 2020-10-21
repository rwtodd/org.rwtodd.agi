;; AGI Sound Resource 12 (Volume 0 Offset 43756)


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
i11	0	48	0	139
i11	48	12	0	139
i11	60	6	0	69
i11	66	6	0	61
i11	72	6	0	69
i11	78	6	0	61
i11	84	24	0	69
i11	108	48	0	139
i11	156	12	0	139
i11	168	6	0	69
i11	174	6	0	61
i11	180	6	0	69
i11	186	6	0	61
i11	192	6	0	69
i11	198	6	0	61
i11	204	6	0	69
i11	210	6	0	61
i11	216	24	0	69
i11	240	48	0	139
i11	288	12	0	139
i11	300	6	0	69
i11	306	6	0	61
i11	312	24	0	69
i11	336	6	0	69
i11	342	6	0	61
i11	348	24	0	69
i11	372	6	0	69
i11	378	6	0	61
i11	384	24	0	69
i11	408	6	0	69
i11	414	6	0	61
i11	420	6	0	69
i11	426	6	0	61
i11	432	6	0	69
i11	438	6	0	61
i11	444	6	0	69
i11	450	6	0	61
i11	456	6	0	69
i11	462	6	0	61
i11	468	6	0	69
i11	474	6	0	61
i11	480	6	0	69
i11	486	6	0	61
i11	492	54	0	69
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
i99	0	606	0.9	1.0	1.0
