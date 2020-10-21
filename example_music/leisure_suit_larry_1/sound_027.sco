;; AGI Sound Resource 27 (Volume 1 Offset 96199)


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
i11	0	22	0	285
i11	22	20	0	214
i11	42	22	0	190
i11	65	86	0	170
i11	174	19	0	285
i11	195	21	0	214
i11	216	23	0	190
i11	240	80	0	170
i11	343	21	0	170
i11	365	21	0	160
i11	386	21	0	170
i11	407	86	0	190
i11	515	21	0	214
i11	537	21	0	170
i11	558	21	0	190
i11	579	107	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	63	108	2	428
i12	235	108	2	428
i12	407	108	2	285
i12	575	108	2	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	235	108	4	339
i13	407	108	4	226
i13	577	108	4	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	746	0.9	1.0	1.0
