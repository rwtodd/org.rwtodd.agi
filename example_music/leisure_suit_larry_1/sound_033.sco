;; AGI Sound Resource 33 (Volume 0 Offset 25057)


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
i11	0	33	0	214
i11	60	21	0	214
i11	100	10	0	214
i11	120	43	0	214
i11	180	28	0	180
i11	220	12	0	190
i11	240	26	0	190
i11	280	10	0	214
i11	300	22	0	214
i11	340	13	0	226
i11	360	67	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	29	2	285
i12	60	18	2	285
i12	100	8	2	285
i12	120	32	2	285
i12	180	17	2	285
i12	220	8	2	285
i12	240	28	2	285
i12	280	9	2	285
i12	300	19	2	285
i12	340	8	2	285
i12	360	65	2	285
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	34	4	360
i13	60	20	4	360
i13	99	11	4	360
i13	119	43	4	360
i13	179	21	4	360
i13	219	14	4	360
i13	239	23	4	320
i13	279	8	4	320
i13	299	21	4	320
i13	339	14	4	320
i13	359	62	4	360
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	487	0.9	1.0	1.0
