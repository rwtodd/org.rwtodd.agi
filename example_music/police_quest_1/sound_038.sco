;; AGI Sound Resource 38 (Volume 0 Offset 24243)


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
i11	0	48	0	320
i11	50	14	0	285
i11	66	16	0	254
i11	83	16	0	320
i11	100	66	0	285
i11	166	31	0	269
i11	200	15	0	254
i11	215	17	0	190
i11	233	33	0	214
i11	267	16	0	254
i11	283	16	0	240
i11	300	65	0	285
i11	365	15	0	254
i11	380	18	0	285
i11	400	50	0	320
i11	451	15	0	339
i11	466	32	0	381
i11	500	32	0	381
i11	533	32	0	285
i11	567	32	0	381
i11	600	66	0	339
i11	667	11	0	320
i11	678	9	0	339
i11	689	10	0	320
i11	700	97	0	285
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	14	0	641
i12	18	16	0	679
i12	35	29	0	641
i12	66	27	0	855
i12	99	34	0	480
i12	134	32	0	571
i12	167	31	0	428
i12	200	33	0	641
i12	233	33	0	508
i12	267	32	0	428
i12	300	33	0	480
i12	333	32	0	679
i12	367	32	0	855
i12	400	33	0	762
i12	433	32	0	508
i12	467	32	0	508
i12	500	47	0	453
i12	549	15	0	508
i12	565	33	0	453
i12	600	33	0	428
i12	633	34	0	855
i12	667	32	0	762
i12	700	98	0	855
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	100	66	0	339
i13	167	32	0	339
i13	200	16	0	320
i13	217	15	0	339
i13	236	29	0	320
i13	267	32	0	320
i13	301	30	0	403
i13	333	33	0	428
i13	367	32	0	339
i13	400	77	0	381
i13	506	52	0	453
i13	566	31	0	453
i13	598	61	0	480
i13	667	33	0	480
i13	701	32	0	381
i13	733	65	0	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	858	0.9	1.0	1.0
