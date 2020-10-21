;; AGI Sound Resource 26 (Volume 0 Offset 45656)


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
i21	0	39	14	16
i21	39	15	13	16
i21	54	12	12	16
i21	66	12	11	16
i21	78	6	10	16
i21	84	6	9	16
i21	90	6	8	16
i21	96	6	7	16
i21	102	6	6	16
i21	108	3	5	16
i21	111	12	4	16
i21	123	3	5	16
i21	126	9	6	16
i21	135	9	7	16
i21	144	9	8	16
i21	153	6	9	16
i21	159	9	10	16
i21	168	6	11	16
i21	174	6	12	16
i21	180	6	13	16
i21	186	54	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	300	0.9	1.0	1.0
