;; AGI Sound Resource 23 (Volume 0 Offset 122457)


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
i11	0	21	0	116
i11	30	21	0	233
i11	60	21	0	116
i11	90	21	0	233
i11	120	6	0	104
i11	135	6	0	92
i11	150	6	0	139
i11	165	6	0	123
i11	180	21	0	116
i11	210	21	0	233
i11	240	21	0	116
i11	270	21	0	233
i11	300	6	0	104
i11	315	6	0	92
i11	330	6	0	139
i11	345	6	0	123
i11	360	21	0	116
i11	390	21	0	233
i11	420	21	0	116
i11	450	21	0	233
i11	480	6	0	104
i11	495	6	0	92
i11	510	6	0	139
i11	525	6	0	123
i11	540	21	0	116
i11	570	21	0	233
i11	600	21	0	116
i11	630	21	0	233
i11	660	6	0	104
i11	675	6	0	92
i11	690	6	0	139
i11	705	15	0	123
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	120	21	0	116
i12	150	21	0	233
i12	300	21	0	116
i12	330	21	0	233
i12	480	21	0	116
i12	510	21	0	233
i12	660	21	0	116
i12	690	30	0	233
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	780	0.9	1.0	1.0
