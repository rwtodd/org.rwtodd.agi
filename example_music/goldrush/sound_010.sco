;; AGI Sound Resource 10 (Volume 0 Offset 29957)


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
i11	6	54	0	214
i11	78	45	0	214
i11	132	18	0	214
i11	151	49	0	214
i11	204	14	0	170
i11	222	50	0	254
i11	276	14	0	226
i11	294	27	0	214
i11	330	27	0	214
i11	366	27	0	226
i11	402	27	0	226
i11	438	126	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	6	54	0	339
i12	78	54	0	320
i12	150	54	0	339
i12	222	54	0	320
i12	294	54	0	339
i12	366	54	0	320
i12	438	126	0	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	6	54	0	508
i13	78	54	0	508
i13	150	54	0	508
i13	222	54	0	508
i13	294	54	0	508
i13	366	54	0	508
i13	438	126	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	624	0.9	1.0	1.0
