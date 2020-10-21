;; AGI Sound Resource 36 (Volume 3 Offset 338572)


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
i11	9	11	0	381
i11	20	12	0	508
i11	32	11	0	339
i11	43	11	0	508
i11	55	12	0	381
i11	67	10	0	508
i11	78	12	0	339
i11	90	11	0	508
i11	101	12	0	381
i11	113	11	0	508
i11	124	12	0	339
i11	136	11	0	508
i11	147	12	0	381
i11	159	9	0	508
i11	170	10	0	339
i11	182	12	0	508
i11	194	11	0	381
i11	205	12	0	480
i11	217	11	0	339
i11	228	12	0	480
i11	240	11	0	381
i11	251	12	0	480
i11	263	12	0	339
i11	275	9	0	480
i11	286	12	0	381
i11	298	11	0	480
i11	309	12	0	339
i11	321	12	0	480
i11	335	9	0	339
i11	344	11	0	480
i11	355	12	0	339
i11	367	7	0	480
i11	378	12	0	381
i11	390	7	0	508
i11	401	12	0	339
i11	413	12	0	508
i11	425	11	0	381
i11	436	7	0	508
i11	448	11	0	339
i11	459	12	0	508
i11	471	11	0	381
i11	482	9	0	508
i11	494	11	0	339
i11	505	12	0	508
i11	517	11	0	381
i11	528	9	0	508
i11	540	12	0	339
i11	552	8	0	508
i11	563	12	0	381
i11	575	11	0	480
i11	586	12	0	339
i11	598	11	0	480
i11	609	12	0	381
i11	621	11	0	480
i11	632	12	0	339
i11	644	13	0	480
i11	659	8	0	339
i11	667	12	0	480
i11	679	11	0	339
i11	690	11	0	480
i11	702	11	0	320
i11	713	12	0	480
i11	725	11	0	339
i11	736	12	0	480
i11	748	122	0	381
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	9	148	2	190
i12	195	16	2	160
i12	211	108	2	240
i12	378	178	2	190
i12	570	174	2	214
i12	746	88	2	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	105	46	0	762
i13	284	38	0	762
i13	475	58	0	762
i13	657	174	0	762
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	930	0.9	1.0	1.0
