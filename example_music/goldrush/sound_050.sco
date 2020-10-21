;; AGI Sound Resource 50 (Volume 2 Offset 556120)


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
i11	0	2	0	1017
i11	3	2	0	1017
i11	6	3	0	1017
i11	9	3	0	1017
i11	13	2	0	960
i11	16	2	0	960
i11	19	2	0	960
i11	22	2	0	906
i11	25	2	0	906
i11	28	2	0	855
i11	31	3	0	855
i11	34	3	0	807
i11	37	2	0	807
i11	40	2	0	762
i11	43	2	0	719
i11	46	2	0	679
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	108	0.9	1.0	1.0
