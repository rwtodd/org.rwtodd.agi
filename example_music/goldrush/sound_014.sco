;; AGI Sound Resource 14 (Volume 0 Offset 30371)


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
i11	16	35	0	226
i11	63	36	0	226
i11	111	35	0	226
i11	158	29	0	339
i11	193	9	0	302
i11	205	33	0	285
i11	241	9	0	339
i11	252	36	0	302
i11	300	35	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	16	36	0	453
i12	64	35	0	453
i12	111	35	0	453
i12	158	30	0	679
i12	194	9	0	605
i12	206	32	0	571
i12	241	9	0	679
i12	253	35	0	605
i12	300	36	0	679
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	63	35	0	254
i13	110	36	0	285
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	396	0.9	1.0	1.0
