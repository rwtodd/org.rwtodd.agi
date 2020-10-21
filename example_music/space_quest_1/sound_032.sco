;; AGI Sound Resource 32 (Volume 2 Offset 257439)


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
i21	3	27	0	16
i21	30	18	1	16
i21	48	18	2	16
i21	66	21	3	16
i21	87	18	4	16
i21	105	18	5	16
i21	123	18	6	16
i21	141	15	7	16
i21	156	15	8	16
i21	171	9	9	16
i21	180	9	10	16
i21	189	9	11	16
i21	198	9	12	16
i21	207	9	13	16
i21	216	9	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	285	0.9	1.0	1.0
