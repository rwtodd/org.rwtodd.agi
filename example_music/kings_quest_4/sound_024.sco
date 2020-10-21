;; AGI Sound Resource 24 (Volume 2 Offset 550188)


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
i11	44	38	0	214
i11	83	9	0	226
i11	95	15	0	254
i11	124	26	0	254
i11	152	65	0	170
i11	273	31	0	180
i11	311	9	0	190
i11	323	21	0	214
i11	352	34	0	214
i11	390	83	0	269
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	40	176	2	127
i12	279	194	2	170
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	39	176	3	254
i13	273	199	3	269
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	533	0.9	1.0	1.0
