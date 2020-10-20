;; AGI Sound Resource 5 (Volume 0 Offset 21441)


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
i11	0	9	0	928
i11	9	9	1	928
i11	18	12	2	928
i11	30	12	3	928
i11	42	12	4	928
i11	54	12	5	928
i11	66	12	6	928
i11	78	6	7	928
i11	84	6	8	928
i11	90	6	9	928
i11	96	6	10	928
i11	102	6	11	928
i11	108	3	12	928
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	9	0	960
i12	9	9	1	960
i12	18	12	2	960
i12	30	12	3	960
i12	42	12	4	960
i12	54	12	5	960
i12	66	12	6	960
i12	78	6	7	960
i12	84	6	8	960
i12	90	6	9	960
i12	96	6	10	960
i12	102	6	12	960
i12	108	3	13	960
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	171	0.9	1.0	1.0
