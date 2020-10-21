;; AGI Sound Resource 14 (Volume 2 Offset 90763)


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
i11	0	3	8	192
i11	3	3	6	144
i11	6	3	4	80
i11	9	3	2	48
i11	12	3	0	16
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	6	10	64
i21	6	6	9	64
i21	12	3	6	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	75	0.9	1.0	1.0
