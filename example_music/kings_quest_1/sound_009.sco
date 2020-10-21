;; AGI Sound Resource 9 (Volume 0 Offset 45754)


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
i21	0	6	2	0
i21	9	3	4	0
i21	12	6	0	0
i21	18	3	1	0
i21	21	3	3	0
i21	24	3	1	0
i21	27	9	0	0
i21	36	3	3	0
i21	45	6	2	0
i21	51	3	4	0
i21	54	3	7	0
i21	57	3	8	0
i21	60	3	9	0
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	123	0.9	1.0	1.0
