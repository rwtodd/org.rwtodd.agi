;; AGI Sound Resource 15 (Volume 2 Offset 254504)


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
i21	0	3	3	64
i21	3	3	2	64
i21	6	6	1	32
i21	12	15	0	16
i21	27	6	1	16
i21	33	3	2	16
i21	36	3	2	32
i21	39	6	3	32
i21	45	3	4	32
i21	48	12	5	64
i21	60	12	6	64
i21	72	12	7	64
i21	84	12	8	64
i21	96	12	9	64
i21	108	12	10	64
i21	120	12	11	64
i21	132	12	12	64
i21	144	3	13	64
i21	147	3	14	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	210	0.9	1.0	1.0
