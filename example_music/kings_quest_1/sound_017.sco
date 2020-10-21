;; AGI Sound Resource 17 (Volume 0 Offset 47597)


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
i11	0	12	0	288
i11	15	12	0	304
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	12	0	224
i12	15	12	0	224
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	12	4	288
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	33	3	4	0
i21	36	3	5	0
i21	39	3	2	0
i21	42	21	0	0
i21	63	3	5	0
i21	66	27	6	0
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	153	0.9	1.0	1.0
