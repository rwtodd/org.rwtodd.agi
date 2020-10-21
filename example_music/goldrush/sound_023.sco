;; AGI Sound Resource 23 (Volume 2 Offset 555251)


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
i11	19	14	0	240
i11	37	3	0	240
i11	43	9	0	240
i11	55	9	0	240
i11	67	17	0	240
i11	90	18	0	320
i11	114	18	0	190
i11	138	159	0	214
i11	304	13	0	226
i11	321	3	0	226
i11	327	9	0	226
i11	339	9	0	226
i11	351	17	0	226
i11	374	18	0	302
i11	398	18	0	180
i11	422	136	0	202
i11	564	12	0	226
i11	576	12	0	202
i11	588	17	0	180
i11	611	18	0	151
i11	635	29	0	151
i11	670	12	0	135
i11	682	18	0	151
i11	706	18	0	180
i11	730	35	0	226
i11	765	12	0	202
i11	777	6	0	180
i11	789	29	0	180
i11	824	6	0	151
i11	836	30	0	151
i11	872	47	0	113
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	162	29	0	508
i12	197	9	0	508
i12	209	6	0	508
i12	221	6	0	508
i12	233	17	0	508
i12	256	18	0	641
i12	280	24	0	381
i12	304	136	0	360
i12	446	29	0	480
i12	481	9	0	480
i12	493	6	0	480
i12	505	6	0	480
i12	517	17	0	480
i12	540	18	0	605
i12	564	6	0	360
i12	576	12	0	403
i12	588	41	0	453
i12	635	41	0	480
i12	682	42	0	508
i12	730	41	0	539
i12	777	6	0	605
i12	789	29	0	605
i12	824	6	0	605
i12	836	30	0	605
i12	872	47	0	453
i12	919	6	0	906
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	588	41	0	226
i13	635	41	0	202
i13	682	42	0	226
i13	730	41	0	339
i13	777	6	0	226
i13	789	30	0	226
i13	824	6	0	240
i13	836	30	0	240
i13	872	47	0	180
i13	919	6	0	453
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	985	0.9	1.0	1.0
