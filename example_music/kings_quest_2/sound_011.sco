;; AGI Sound Resource 11 (Volume 0 Offset 43195)


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
i11	0	9	0	786
i11	12	72	0	786
i11	90	6	0	525
i11	96	12	0	393
i11	108	12	0	330
i11	120	12	0	294
i11	132	12	0	262
i11	144	12	0	393
i11	156	12	0	131
i11	168	12	0	110
i11	180	12	0	131
i11	192	12	0	98
i11	204	72	0	110
i11	288	12	0	110
i11	300	72	0	116
i11	378	6	0	165
i11	384	12	0	131
i11	396	9	0	147
i11	408	12	0	147
i11	420	3	0	165
i11	426	6	0	165
i11	444	12	0	196
i11	456	12	0	165
i11	468	12	0	147
i11	480	12	0	139
i11	492	12	0	147
i11	504	12	0	165
i11	516	9	0	196
i11	528	6	0	196
i11	540	12	0	131
i11	552	12	0	110
i11	564	12	0	131
i11	576	15	0	98
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	144	36	0	393
i12	186	6	0	525
i12	192	12	0	393
i12	204	12	0	330
i12	216	12	0	294
i12	228	12	0	262
i12	240	12	0	393
i12	282	6	0	525
i12	288	12	0	393
i12	300	12	0	330
i12	312	12	0	294
i12	324	12	0	262
i12	336	12	0	393
i12	378	6	0	525
i12	384	12	0	393
i12	396	12	0	330
i12	408	12	0	294
i12	420	12	0	262
i12	432	12	0	393
i12	474	6	0	525
i12	480	12	0	393
i12	492	12	0	330
i12	504	12	0	294
i12	516	12	0	262
i12	528	12	0	393
i12	540	12	0	262
i12	552	12	0	220
i12	564	12	0	262
i12	576	15	0	196
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	9	0	786
i13	12	72	0	786
i13	96	9	0	786
i13	108	72	0	786
i13	192	9	0	786
i13	204	72	0	786
i13	288	9	0	786
i13	300	72	0	786
i13	384	9	0	786
i13	396	72	0	786
i13	480	9	0	786
i13	492	48	0	786
i13	540	12	0	525
i13	552	12	0	441
i13	564	12	0	525
i13	576	15	0	393
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	651	0.9	1.0	1.0
