;; AGI Sound Resource 9 (Volume 0 Offset 21824)


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
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	4	64
i21	3	3	2	64
i21	6	3	0	64
i21	9	3	1	64
i21	12	3	3	64
i21	15	3	5	64
i21	18	3	7	64
i21	21	3	10	64
i21	24	3	8	64
i21	27	3	5	64
i21	30	3	2	64
i21	33	3	0	64
i21	36	3	1	64
i21	39	3	2	64
i21	42	3	3	64
i21	45	3	4	64
i21	48	3	5	64
i21	51	3	6	64
i21	54	3	7	64
i21	57	3	8	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	120	0.9	1.0	1.0
