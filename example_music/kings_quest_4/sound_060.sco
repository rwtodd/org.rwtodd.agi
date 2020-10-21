;; AGI Sound Resource 60 (Volume 3 Offset 338851)


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
i11	6	11	0	80
i11	17	9	0	85
i11	26	10	0	80
i11	36	8	0	95
i11	46	11	0	71
i11	90	10	0	80
i11	100	7	0	85
i11	107	9	0	80
i11	116	9	0	95
i11	127	10	0	71
i11	167	8	0	80
i11	176	9	0	85
i11	185	9	0	80
i11	194	6	0	95
i11	204	9	0	80
i11	213	9	0	85
i11	222	9	0	80
i11	231	9	0	95
i11	240	9	0	80
i11	249	9	0	85
i11	258	9	0	80
i11	267	9	0	95
i11	277	12	0	71
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	6	10	3	160
i12	16	8	3	170
i12	24	9	3	160
i12	33	10	3	190
i12	44	7	3	143
i12	85	11	3	160
i12	96	7	3	170
i12	104	8	3	160
i12	113	9	3	190
i12	123	7	3	143
i12	163	10	3	160
i12	173	8	3	170
i12	181	9	3	160
i12	192	9	3	190
i12	201	9	3	160
i12	211	9	3	170
i12	220	8	3	160
i12	229	10	3	190
i12	240	8	3	160
i12	249	8	3	170
i12	257	9	3	160
i12	266	10	3	190
i12	276	8	3	143
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	7	16	2	302
i13	25	6	2	202
i13	44	6	2	180
i13	85	7	2	302
i13	105	3	2	202
i13	123	6	2	180
i13	164	7	2	302
i13	183	3	2	202
i13	201	6	2	180
i13	221	4	2	202
i13	241	8	2	302
i13	259	4	2	202
i13	277	5	2	180
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	349	0.9	1.0	1.0
