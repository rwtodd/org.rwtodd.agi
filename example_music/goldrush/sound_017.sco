;; AGI Sound Resource 17 (Volume 0 Offset 66273)


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
i11	36	21	0	202
i11	68	8	0	180
i11	79	32	0	160
i11	122	32	0	135
i11	165	64	0	135
i11	240	10	0	120
i11	251	32	0	135
i11	293	32	0	160
i11	336	64	0	202
i11	411	11	0	180
i11	422	32	0	160
i11	465	32	0	160
i11	508	32	0	180
i11	550	33	0	180
i11	594	160	0	202
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	78	22	0	403
i12	110	8	0	269
i12	121	21	0	240
i12	153	8	0	269
i12	164	21	0	539
i12	196	8	0	269
i12	207	21	0	240
i12	239	8	0	269
i12	250	21	0	403
i12	282	8	0	269
i12	293	21	0	240
i12	325	8	0	269
i12	335	22	0	480
i12	368	8	0	302
i12	378	22	0	240
i12	410	11	0	302
i12	421	22	0	539
i12	453	8	0	320
i12	464	21	0	269
i12	496	8	0	320
i12	507	21	0	539
i12	539	8	0	360
i12	550	21	0	302
i12	582	8	0	360
i12	593	160	0	403
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	625	11	0	302
i13	636	21	0	269
i13	668	11	0	302
i13	679	75	0	320
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	814	0.9	1.0	1.0
