;; AGI Sound Resource 89 (Volume 4 Offset 142168)


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
i11	5	10	0	170
i11	16	10	0	143
i11	28	10	0	170
i11	39	10	0	143
i11	51	10	0	190
i11	62	10	0	160
i11	74	10	0	190
i11	85	10	0	160
i11	97	10	0	214
i11	108	10	0	160
i11	120	10	0	214
i11	131	10	0	160
i11	143	43	0	190
i11	189	10	0	170
i11	201	10	0	143
i11	213	10	0	170
i11	224	10	0	143
i11	236	10	0	190
i11	247	10	0	143
i11	259	10	0	190
i11	270	12	0	143
i11	282	8	0	214
i11	293	9	0	160
i11	305	14	0	190
i11	322	3	0	214
i11	328	29	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	4	13	0	381
i12	28	12	0	381
i12	51	13	0	381
i12	74	13	0	381
i12	97	13	0	428
i12	120	13	0	428
i12	143	13	0	428
i12	166	13	0	428
i12	189	13	0	381
i12	212	13	0	381
i12	235	13	0	381
i12	258	13	0	381
i12	282	13	0	428
i12	305	13	0	428
i12	328	23	0	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	4	13	0	571
i13	27	13	0	571
i13	50	13	0	571
i13	73	13	0	571
i13	96	13	0	641
i13	120	12	0	641
i13	143	12	0	641
i13	166	12	0	641
i13	189	13	0	571
i13	212	13	0	571
i13	235	13	0	571
i13	258	13	0	571
i13	281	13	0	641
i13	304	13	0	641
i13	327	23	0	641
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	417	0.9	1.0	1.0
