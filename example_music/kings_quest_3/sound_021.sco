;; AGI Sound Resource 21 (Volume 1 Offset 115786)


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
i11	45	11	0	156
i11	67	8	0	208
i11	79	7	0	156
i11	90	45	0	104
i11	158	7	0	116
i11	169	7	0	104
i11	180	11	0	92
i11	203	5	0	104
i11	214	7	0	116
i11	225	150	0	104
i11	406	7	0	156
i11	428	8	0	208
i11	439	6	0	156
i11	451	54	0	104
i11	518	8	0	156
i11	530	5	0	104
i11	541	9	0	116
i11	563	6	0	156
i11	575	5	0	116
i11	586	11	0	104
i11	601	11	0	116
i11	616	11	0	156
i11	631	11	0	116
i11	646	11	0	104
i11	661	11	0	116
i11	676	11	0	104
i11	691	11	0	116
i11	706	11	0	156
i11	721	11	0	116
i11	732	12	0	104
i11	755	11	0	116
i11	766	147	0	78
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	226	11	2	624
i12	248	6	2	833
i12	259	6	2	624
i12	271	45	2	416
i12	338	6	2	624
i12	350	5	2	416
i12	361	6	2	350
i12	383	12	2	371
i12	395	5	2	467
i12	406	141	2	416
i12	586	21	2	624
i12	631	21	2	833
i12	677	20	2	624
i12	722	22	2	833
i12	761	6	2	833
i12	767	142	2	585
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	225	11	1	312
i13	248	5	1	416
i13	259	5	1	312
i13	270	45	1	208
i13	338	5	1	312
i13	349	6	1	208
i13	360	6	1	175
i13	383	11	1	185
i13	394	6	1	233
i13	405	141	1	208
i13	586	20	1	312
i13	631	20	1	416
i13	676	21	1	312
i13	721	23	1	416
i13	760	6	1	416
i13	766	143	1	624
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	973	0.9	1.0	1.0
