;; AGI Sound Resource 6 (Volume 0 Offset 33454)


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
i21	0	3	0	64
i21	6	3	0	32
i21	9	3	0	16
i21	12	3	0	32
i21	21	3	0	64
i21	42	12	0	16
i21	54	12	1	16
i21	66	9	2	16
i21	75	3	3	16
i21	78	9	4	16
i21	87	3	5	16
i21	90	3	6	16
i21	93	3	7	16
i21	96	3	8	16
i21	99	3	9	16
i21	102	3	10	16
i21	105	3	11	16
i21	108	3	12	16
i21	111	3	13	16
i21	114	3	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	177	0.9	1.0	1.0
