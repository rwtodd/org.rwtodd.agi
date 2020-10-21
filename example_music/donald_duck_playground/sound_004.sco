;; AGI Sound Resource 4 (Volume 0 Offset 134101)


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
i11	3	6	0	976
i11	9	6	4	976
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	9	3	9	896
i12	12	3	11	896
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	3	9	0	976
i13	12	3	0	1008
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	0	32
i21	15	3	8	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	78	0.9	1.0	1.0
