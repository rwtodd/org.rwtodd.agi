;; AGI Sound Resource 66 (Volume 0 Offset 26209)


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
i11	0	43	0	381
i11	49	41	0	320
i11	98	46	0	254
i11	148	15	0	240
i11	172	6	0	254
i11	183	6	0	285
i11	195	142	0	254
i11	351	11	0	240
i11	375	6	0	254
i11	387	6	0	285
i11	401	46	0	254
i11	450	33	0	381
i11	485	8	0	285
i11	496	47	0	320
i11	544	54	0	339
i11	599	142	0	381
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	42	0	285
i12	49	40	0	240
i12	98	45	0	190
i12	147	15	0	180
i12	171	7	0	190
i12	182	6	0	214
i12	195	142	0	190
i12	350	11	0	180
i12	375	6	0	190
i12	386	7	0	214
i12	400	47	0	190
i12	449	33	0	285
i12	485	7	0	214
i12	495	47	0	240
i12	544	53	0	254
i12	598	143	0	285
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	1	42	0	285
i13	50	40	0	240
i13	98	46	0	190
i13	148	15	0	180
i13	172	6	0	190
i13	183	6	0	214
i13	195	142	0	190
i13	351	11	0	180
i13	376	6	0	190
i13	387	7	0	214
i13	401	47	0	190
i13	450	33	0	285
i13	486	7	0	214
i13	496	47	0	240
i13	545	53	0	254
i13	599	142	0	285
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	801	0.9	1.0	1.0
