;; AGI Sound Resource 3 (Volume 0 Offset 38492)


t 0 3600 ;; AGI runs in 1/60th second ticks

; set up the instruments
i 1  0  0  1   8 19 ;; pipe organ (fatboy .sf2)
i 1  0  0  2   8 19 ;; pipe organ (fatboy .sf2)
i 1  0  0  3   8 19 ;; pipe organ (fatboy .sf2)

; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	0	9	0	123
i11	9	9	0	139
i11	18	108	0	123
i11	126	9	0	139
i11	135	9	0	156
i11	144	9	0	165
i11	153	9	0	185
i11	162	36	0	196
i11	198	144	0	185
i11	360	9	0	247
i11	369	9	0	278
i11	378	108	0	247
i11	486	36	0	330
i11	522	36	0	312
i11	558	36	0	393
i11	594	108	0	371
i11	720	9	0	247
i11	729	9	0	278
i11	738	108	0	247
i11	846	9	0	278
i11	855	9	0	312
i11	864	9	0	330
i11	873	9	0	371
i11	882	36	0	393
i11	918	108	0	371
i11	1026	9	0	786
i11	1035	9	0	661
i11	1044	9	0	556
i11	1053	9	0	467
i11	1062	9	0	393
i11	1071	9	0	330
i11	1080	9	0	278
i11	1089	9	0	233
i11	1098	9	0	196
i11	1107	9	0	165
i11	1116	9	0	139
i11	1125	9	0	116
i11	1134	72	0	139
i11	1206	72	0	165
i11	1278	147	0	156
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	720	9	0	495
i12	729	9	0	556
i12	738	108	0	495
i12	846	9	0	556
i12	855	9	0	624
i12	864	9	0	661
i12	873	9	0	742
i12	882	36	0	786
i12	918	108	0	742
i12	1026	9	0	786
i12	1035	9	0	786
i12	1044	9	0	661
i12	1053	9	0	556
i12	1062	9	0	467
i12	1071	9	0	393
i12	1080	9	0	330
i12	1089	9	0	278
i12	1098	36	0	233
i12	1134	144	0	247
i12	1278	147	0	247
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	1026	9	0	786
i13	1035	9	0	786
i13	1044	9	0	786
i13	1053	9	0	661
i13	1062	9	0	556
i13	1071	9	0	467
i13	1080	54	0	393
i13	1134	144	0	742
i13	1278	147	0	742
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1485	0.9	1.0	1.0
