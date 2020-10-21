;; AGI Sound Resource 26 (Volume 0 Offset 20869)


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
i11	0	3	0	240
i11	3	3	0	256
i11	6	3	1	272
i11	9	3	2	288
i11	12	3	3	304
i11	15	3	4	320
i11	18	3	5	336
i11	27	3	12	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	208
i12	3	3	0	224
i12	6	3	1	240
i12	9	3	2	256
i12	12	3	3	272
i12	15	3	4	288
i12	18	3	5	304
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	192
i13	3	3	0	208
i13	6	3	1	224
i13	9	3	2	240
i13	12	3	3	256
i13	15	3	4	272
i13	18	3	5	288
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	0	64
i21	6	3	0	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	90	0.9	1.0	1.0
