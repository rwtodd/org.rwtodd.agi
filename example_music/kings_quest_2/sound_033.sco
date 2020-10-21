;; AGI Sound Resource 33 (Volume 0 Offset 47653)


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
i11	0	3	9	16
i11	3	3	0	16
i11	6	3	1	16
i11	9	3	4	16
i11	12	3	14	16
i11	15	3	11	48
i11	18	3	0	48
i11	21	3	1	48
i11	24	3	9	48
i11	27	3	13	48
i11	30	3	14	48
i11	33	3	11	64
i11	36	3	0	64
i11	39	3	1	64
i11	42	3	14	64
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	105	0.9	1.0	1.0
