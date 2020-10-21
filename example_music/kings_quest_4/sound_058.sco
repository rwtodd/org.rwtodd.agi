;; AGI Sound Resource 58 (Volume 0 Offset 65656)


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
i11	16	52	0	381
i11	68	13	0	254
i11	81	13	0	240
i11	94	13	0	127
i11	107	13	0	101
i11	120	55	0	85
i11	187	37	0	381
i11	224	30	0	360
i11	254	34	0	381
i11	288	34	0	403
i11	322	37	0	381
i11	359	36	0	360
i11	395	39	0	381
i11	434	42	0	403
i11	476	136	0	381
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	15	53	0	508
i12	187	36	0	190
i12	223	31	0	202
i12	254	33	0	190
i12	287	34	0	160
i12	321	37	0	190
i12	358	36	0	202
i12	394	39	0	190
i12	433	42	0	160
i12	475	137	0	190
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	16	53	0	254
i13	187	122	0	254
i13	321	138	0	254
i13	474	143	0	254
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	677	0.9	1.0	1.0
