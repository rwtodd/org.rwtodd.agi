;; AGI Sound Resource 43 (Volume 3 Offset 162475)


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
i11	10	20	0	360
i11	30	25	0	240
i11	57	76	0	190
i11	133	26	0	214
i11	159	136	0	160
i11	307	20	0	360
i11	327	21	0	240
i11	356	68	0	120
i11	425	28	0	127
i11	453	57	0	160
i11	511	90	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	55	82	2	285
i12	202	67	2	214
i12	347	49	2	190
i12	396	25	2	214
i12	452	72	2	214
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	55	73	2	360
i13	156	19	2	762
i13	175	25	2	508
i13	200	63	2	381
i13	348	67	2	360
i13	451	25	2	381
i13	477	23	2	320
i13	506	44	2	240
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	661	0.9	1.0	1.0
