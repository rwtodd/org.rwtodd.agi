;; AGI Sound Resource 1 (Volume 0 Offset 7897)


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
i21	0	174	3	16
i21	174	48	2	16
i21	222	405	3	16
i21	627	15	4	16
i21	642	264	3	16
i21	906	15	4	16
i21	921	30	3	16
i21	951	15	4	16
i21	966	456	3	16
i21	1422	18	2	16
i21	1440	504	3	16
i21	1944	12	4	16
i21	1956	597	3	16
i21	2553	9	4	16
i21	2562	249	3	16
i21	2811	15	4	16
i21	2826	429	3	16
i21	3255	12	2	16
i21	3267	255	3	16
i21	3522	15	7	16
i21	3537	300	3	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	3897	0.9	1.0	1.0
