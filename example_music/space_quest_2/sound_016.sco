;; AGI Sound Resource 16 (Volume 0 Offset 20536)


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
i11	0	3	0	1008
i11	3	3	0	848
i11	6	3	0	672
i11	9	3	0	480
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	15	12	0	16
i21	27	3	1	16
i21	30	3	2	16
i21	33	3	3	16
i21	36	3	4	16
i21	39	3	5	16
i21	42	3	6	16
i21	45	3	7	16
i21	48	3	8	16
i21	51	3	9	16
i21	54	3	10	16
i21	57	3	11	16
i21	60	3	12	16
i21	63	3	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	126	0.9	1.0	1.0
