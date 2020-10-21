;; AGI Sound Resource 13 (Volume 2 Offset 549723)


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
i11	31	3	0	254
i11	34	4	0	240
i11	39	5	0	214
i11	44	5	0	190
i11	49	17	0	170
i11	90	30	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	30	4	0	508
i12	34	4	0	480
i12	39	4	0	428
i12	43	6	0	381
i12	49	17	0	339
i12	90	30	0	679
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	91	30	0	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	181	0.9	1.0	1.0
