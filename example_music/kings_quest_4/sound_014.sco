;; AGI Sound Resource 14 (Volume 1 Offset 375554)


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
i11	23	19	0	320
i11	42	20	0	381
i11	62	21	0	320
i11	83	19	0	381
i11	104	20	0	320
i11	125	21	0	381
i11	146	19	0	320
i11	167	19	0	285
i11	188	20	0	339
i11	210	22	0	285
i11	252	68	0	285
i11	335	11	0	285
i11	346	11	0	254
i11	357	19	0	240
i11	377	22	0	254
i11	399	19	0	240
i11	420	20	0	254
i11	441	20	0	240
i11	463	19	0	254
i11	484	17	0	240
i11	505	19	0	214
i11	526	18	0	254
i11	546	22	0	214
i11	588	23	0	214
i11	613	27	0	170
i11	640	15	0	214
i11	672	65	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	24	18	0	240
i12	44	19	0	254
i12	63	19	0	240
i12	83	19	0	254
i12	103	19	0	240
i12	124	20	0	254
i12	145	21	0	240
i12	166	21	0	254
i12	187	20	0	214
i12	209	18	0	254
i12	228	20	0	214
i12	248	20	0	254
i12	269	21	0	214
i12	290	19	0	254
i12	310	24	0	214
i12	335	18	0	214
i12	353	22	0	190
i12	375	21	0	214
i12	396	22	0	190
i12	418	20	0	214
i12	440	22	0	190
i12	462	20	0	214
i12	484	21	0	190
i12	505	21	0	160
i12	528	21	0	170
i12	550	18	0	143
i12	593	144	0	143
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	797	0.9	1.0	1.0
