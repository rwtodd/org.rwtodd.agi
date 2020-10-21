;; AGI Sound Resource 7 (Volume 0 Offset 33615)


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
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	48
i12	3	3	0	32
i12	6	3	0	48
i12	12	3	0	48
i12	15	3	0	32
i12	18	3	0	48
i12	24	3	0	48
i12	27	3	0	32
i12	30	3	0	48
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	64
i13	3	3	0	80
i13	6	3	0	64
i13	12	3	0	64
i13	15	3	0	80
i13	18	3	0	64
i13	24	3	0	64
i13	27	3	0	80
i13	30	3	0	64
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	93	0.9	1.0	1.0
