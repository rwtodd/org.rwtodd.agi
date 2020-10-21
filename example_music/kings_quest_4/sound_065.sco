;; AGI Sound Resource 65 (Volume 2 Offset 277986)


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
i11	12	13	0	508
i11	34	4	0	339
i11	38	4	0	285
i11	42	39	0	254
i11	94	5	0	254
i11	99	4	0	285
i11	103	4	0	320
i11	107	5	0	339
i11	112	16	0	381
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	12	13	0	381
i12	42	43	0	508
i12	112	15	0	762
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	94	4	0	679
i13	98	4	0	571
i13	102	5	0	508
i13	108	15	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	188	0.9	1.0	1.0
