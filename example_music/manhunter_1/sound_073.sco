;; AGI Sound Resource 73 (Volume 4 Offset 141292)


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
i11	2	16	0	214
i11	18	32	0	190
i11	50	16	0	170
i11	66	32	0	107
i11	98	16	0	214
i11	114	32	0	226
i11	147	16	0	170
i11	163	32	0	127
i11	195	16	0	170
i11	211	32	0	160
i11	243	16	0	127
i11	259	32	0	107
i11	291	48	0	226
i11	339	14	0	285
i11	355	32	0	285
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	14	0	428
i12	19	13	0	428
i12	34	15	0	428
i12	51	14	0	428
i12	67	14	0	339
i12	83	14	0	339
i12	99	14	0	339
i12	115	14	0	453
i12	131	14	0	453
i12	147	14	0	453
i12	163	14	0	508
i12	179	14	0	508
i12	195	14	0	339
i12	211	14	0	320
i12	227	14	0	320
i12	243	15	0	320
i12	260	13	0	320
i12	275	14	0	320
i12	292	14	0	381
i12	308	14	0	571
i12	324	14	0	571
i12	340	14	0	571
i12	356	14	0	571
i12	372	14	0	571
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	50	14	0	339
i13	66	30	0	214
i13	146	15	0	339
i13	163	32	0	254
i13	243	14	0	254
i13	259	32	0	214
i13	339	14	0	143
i13	355	32	0	143
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	447	0.9	1.0	1.0
