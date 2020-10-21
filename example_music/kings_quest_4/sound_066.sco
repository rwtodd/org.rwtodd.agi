;; AGI Sound Resource 66 (Volume 2 Offset 555010)


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
i11	38	15	0	254
i11	70	4	0	285
i11	74	3	0	269
i11	77	2	0	254
i11	79	82	0	240
i11	166	11	0	214
i11	177	5	0	254
i11	187	8	0	254
i11	197	7	0	214
i11	207	104	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	38	9	2	190
i12	80	15	2	190
i12	122	10	2	190
i12	165	9	2	190
i12	207	10	2	190
i12	249	10	2	190
i12	292	9	2	190
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	38	128	2	95
i13	208	103	2	95
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	371	0.9	1.0	1.0
