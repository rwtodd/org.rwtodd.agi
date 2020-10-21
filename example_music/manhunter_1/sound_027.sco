;; AGI Sound Resource 27 (Volume 0 Offset 110067)


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
i11	3	9	0	190
i11	19	9	0	190
i11	35	10	0	214
i11	51	9	0	190
i11	68	9	0	160
i11	83	29	0	190
i11	132	9	0	190
i11	148	8	0	190
i11	164	9	0	285
i11	180	9	0	254
i11	196	36	0	214
i11	260	9	0	190
i11	276	9	0	190
i11	292	9	0	214
i11	308	9	0	190
i11	324	20	0	160
i11	348	28	0	190
i11	389	9	0	143
i11	405	9	0	160
i11	421	9	0	190
i11	437	9	0	214
i11	453	64	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	3	9	0	143
i12	35	9	0	143
i12	67	9	0	143
i12	131	9	0	143
i12	260	9	0	143
i12	292	9	0	143
i12	324	9	0	143
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	3	9	0	113
i13	35	9	0	113
i13	67	9	0	113
i13	131	9	0	113
i13	260	9	0	113
i13	292	9	0	113
i13	324	9	0	113
i13	388	9	0	113
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	577	0.9	1.0	1.0
