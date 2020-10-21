;; AGI Sound Resource 46 (Volume 2 Offset 555980)


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
i11	0	3	11	1008
i11	3	3	12	992
i11	6	3	12	976
i11	9	3	10	960
i11	12	3	8	912
i11	15	3	7	784
i11	18	3	5	592
i11	21	3	5	288
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	13	64
i21	3	3	12	64
i21	6	6	10	64
i21	12	3	9	32
i21	15	3	8	32
i21	18	3	7	16
i21	21	3	6	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	84	0.9	1.0	1.0
