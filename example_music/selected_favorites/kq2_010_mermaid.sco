;; AGI Sound Resource 10 (Volume 0 Offset 42974)


t 0 3600 ;; AGI runs in 1/60th second ticks

; set up the instruments
i 1  0  0  1   0 48   ;; 48 strings
i 1  0  0  2   0 48   ;; 48 strings
i 1  0  0  3   0 48   ;; 48 strings

; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	0	12	0	110
i11	12	12	0	104
i11	24	12	0	110
i11	36	36	0	139
i11	72	36	0	165
i11	108	12	0	123
i11	120	12	0	116
i11	132	12	0	123
i11	144	36	0	156
i11	180	36	0	185
i11	216	12	0	139
i11	228	12	0	131
i11	240	12	0	139
i11	252	36	0	175
i11	288	36	0	208
i11	324	18	0	185
i11	342	18	0	165
i11	360	36	0	156
i11	396	36	0	185
i11	432	111	0	165
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	36	0	165
i12	36	36	0	220
i12	72	36	0	278
i12	108	36	0	185
i12	144	36	0	247
i12	180	36	0	312
i12	216	36	0	208
i12	252	36	0	278
i12	288	36	0	350
i12	324	72	0	208
i12	396	36	0	220
i12	432	111	0	220
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	108	0	416
i13	108	108	0	467
i13	216	108	0	525
i13	324	36	0	312
i13	360	36	0	278
i13	396	36	0	556
i13	432	111	0	416
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	603	0.9	1.0	1.0
