;; AGI Sound Resource 44 (Volume 3 Offset 162667)


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
i11	9	8	0	381
i11	17	9	0	339
i11	26	9	0	320
i11	35	13	0	339
i11	48	65	0	269
i11	114	7	0	285
i11	178	10	0	320
i11	188	10	0	339
i11	198	7	0	320
i11	207	12	0	381
i11	220	65	0	269
i11	285	6	0	285
i11	349	9	0	320
i11	358	12	0	339
i11	370	7	0	320
i11	379	13	0	381
i11	392	30	0	269
i11	422	6	0	285
i11	428	6	0	302
i11	434	85	0	320
i11	523	8	0	381
i11	531	9	0	339
i11	540	10	0	320
i11	550	12	0	339
i11	563	42	0	269
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	91	6	0	302
i12	101	11	0	190
i12	112	11	0	214
i12	123	9	0	190
i12	134	8	0	160
i12	219	8	0	302
i12	230	11	0	190
i12	241	10	0	214
i12	251	10	0	190
i12	262	9	0	160
i12	348	10	0	160
i12	358	11	0	190
i12	369	7	2	214
i12	434	10	2	160
i12	444	10	2	190
i12	455	6	2	214
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	4	9	0	641
i13	47	9	0	762
i13	90	6	0	641
i13	133	6	0	762
i13	176	6	0	641
i13	219	5	0	762
i13	261	6	0	641
i13	304	6	0	762
i13	347	6	0	641
i13	369	24	2	641
i13	433	19	2	641
i13	476	18	2	641
i13	518	10	2	641
i13	561	9	2	641
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	665	0.9	1.0	1.0
