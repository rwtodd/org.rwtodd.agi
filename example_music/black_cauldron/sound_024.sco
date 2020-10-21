;; AGI Sound Resource 24 (Volume 0 Offset 11072)


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
i11	0	72	0	92
i11	72	36	0	98
i11	108	105	0	139
i11	216	72	0	104
i11	288	36	0	110
i11	324	72	0	156
i11	396	36	0	156
i11	432	9	0	104
i11	441	9	0	92
i11	450	9	0	82
i11	459	9	0	78
i11	468	18	0	69
i11	486	18	0	52
i11	504	9	0	61
i11	513	9	0	69
i11	522	18	0	61
i11	540	36	0	46
i11	576	36	0	41
i11	612	9	0	104
i11	621	9	0	92
i11	630	9	0	82
i11	639	9	0	78
i11	648	18	0	69
i11	666	18	0	52
i11	702	15	0	55
i11	720	21	0	52
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	72	0	123
i12	72	36	0	131
i12	108	105	0	278
i12	216	72	0	208
i12	288	36	0	220
i12	324	72	0	312
i12	396	36	0	312
i12	432	18	3	104
i12	450	18	3	110
i12	468	18	3	123
i12	486	18	3	139
i12	504	18	3	156
i12	522	18	3	104
i12	540	9	3	55
i12	549	9	3	61
i12	558	9	3	69
i12	567	9	3	78
i12	576	36	3	139
i12	612	18	3	104
i12	630	18	3	110
i12	648	18	3	123
i12	666	18	3	139
i12	702	15	3	139
i12	720	21	3	104
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	108	0	556
i13	108	105	0	556
i13	216	72	0	624
i13	288	36	0	624
i13	324	72	0	624
i13	396	36	0	624
i13	432	18	3	139
i13	450	18	3	156
i13	468	18	3	165
i13	486	18	3	185
i13	504	18	3	208
i13	522	18	3	156
i13	540	36	3	139
i13	576	36	3	208
i13	612	18	3	139
i13	630	18	3	156
i13	648	18	3	165
i13	666	18	3	185
i13	702	15	3	185
i13	720	21	3	165
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	801	0.9	1.0	1.0
