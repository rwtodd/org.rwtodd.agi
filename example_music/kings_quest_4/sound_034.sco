;; AGI Sound Resource 34 (Volume 3 Offset 338412)


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
i11	4	80	0	190
i11	87	17	0	214
i11	104	28	0	240
i11	140	30	0	240
i11	180	46	0	214
i11	226	48	0	190
i11	274	72	0	214
i11	346	25	0	240
i11	373	12	0	254
i11	387	16	0	285
i11	403	156	0	320
i11	560	66	0	240
i11	626	12	0	254
i11	640	30	0	285
i11	674	38	0	285
i11	712	36	0	254
i11	748	42	0	240
i11	791	140	0	254
i11	931	120	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	272	0	285
i12	274	227	0	381
i12	546	232	0	360
i12	781	263	0	381
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1111	0.9	1.0	1.0
