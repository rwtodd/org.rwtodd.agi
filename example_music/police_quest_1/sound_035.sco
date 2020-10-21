;; AGI Sound Resource 35 (Volume 2 Offset 137785)


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
i11	0	9	0	339
i11	16	8	0	339
i11	29	29	0	302
i11	58	29	0	339
i11	87	30	0	254
i11	117	58	0	269
i11	175	12	0	339
i11	194	7	0	339
i11	204	29	0	302
i11	234	29	0	339
i11	263	29	0	226
i11	292	58	0	254
i11	352	12	0	339
i11	369	7	0	339
i11	379	28	0	170
i11	409	29	0	202
i11	438	29	0	254
i11	468	28	0	269
i11	497	57	0	302
i11	555	13	0	190
i11	576	7	0	190
i11	585	28	0	202
i11	614	28	0	254
i11	643	29	0	226
i11	672	58	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	29	20	0	508
i12	58	20	0	508
i12	87	30	0	508
i12	117	28	0	679
i12	146	58	0	453
i12	204	29	0	679
i12	234	28	0	453
i12	263	28	0	679
i12	292	29	0	508
i12	321	29	0	1017
i12	351	28	0	508
i12	380	28	0	508
i12	409	29	0	508
i12	438	29	0	403
i12	468	23	0	508
i12	497	53	0	508
i12	555	26	0	453
i12	585	18	0	508
i12	614	28	0	508
i12	643	26	0	679
i12	672	58	0	508
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	29	28	0	403
i13	58	29	0	403
i13	87	29	0	403
i13	117	57	0	381
i13	175	13	0	381
i13	195	4	0	381
i13	204	29	0	381
i13	234	28	0	381
i13	263	28	0	381
i13	292	56	0	403
i13	352	12	0	403
i13	369	7	0	403
i13	380	28	0	403
i13	409	29	0	339
i13	438	29	0	339
i13	468	28	0	381
i13	497	58	0	381
i13	555	29	0	381
i13	585	28	0	339
i13	614	29	0	403
i13	643	29	0	381
i13	672	58	0	403
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	790	0.9	1.0	1.0
