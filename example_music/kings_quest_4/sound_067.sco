;; AGI Sound Resource 67 (Volume 0 Offset 66012)


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
i11	8	53	0	214
i11	62	45	0	160
i11	108	53	0	120
i11	162	24	0	127
i11	187	19	0	143
i11	210	24	0	143
i11	234	30	0	127
i11	266	178	0	214
i11	446	49	0	240
i11	495	53	0	180
i11	549	59	0	135
i11	608	29	0	143
i11	638	33	0	180
i11	675	31	0	180
i11	706	31	0	160
i11	738	168	0	120
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	5	316	2	190
i12	376	16	2	160
i12	442	155	2	180
i12	675	158	2	269
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	22	182	2	214
i13	318	79	2	214
i13	668	172	2	539
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	966	0.9	1.0	1.0
