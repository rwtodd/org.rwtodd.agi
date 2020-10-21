;; AGI Sound Resource 12 (Volume 0 Offset 8806)


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
i21	0	3	5	32
i21	3	9	4	32
i21	12	12	0	16
i21	24	3	1	16
i21	27	6	2	16
i21	33	6	4	16
i21	39	12	5	16
i21	51	9	6	16
i21	60	9	7	16
i21	69	12	8	16
i21	81	12	9	16
i21	93	15	10	16
i21	108	15	11	16
i21	123	12	12	16
i21	135	9	13	16
i21	144	27	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	231	0.9	1.0	1.0
