;; AGI Sound Resource 103 (Volume 1 Offset 90510)


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
i11	75	54	0	160
i11	129	15	0	170
i11	144	29	0	190
i11	177	29	0	190
i11	206	33	0	170
i11	240	30	0	160
i11	274	3	0	170
i11	277	5	0	160
i11	282	48	0	170
i11	330	16	0	190
i11	346	29	0	214
i11	385	132	0	214
i11	525	42	0	160
i11	572	11	0	170
i11	583	30	0	190
i11	613	17	0	190
i11	644	34	0	170
i11	678	36	0	160
i11	721	42	0	143
i11	764	43	0	170
i11	808	27	0	214
i11	871	105	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	67	127	0	254
i12	205	29	0	254
i12	239	38	0	320
i12	277	31	0	508
i12	311	35	0	339
i12	346	23	0	254
i12	385	78	0	285
i12	486	25	0	254
i12	521	55	0	381
i12	610	32	0	381
i12	643	35	0	320
i12	679	31	0	254
i12	717	110	0	508
i12	864	112	0	381
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	68	102	0	381
i13	174	105	0	320
i13	281	102	0	339
i13	385	138	0	428
i13	607	107	0	381
i13	714	82	0	339
i13	798	77	0	428
i13	875	100	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1036	0.9	1.0	1.0
