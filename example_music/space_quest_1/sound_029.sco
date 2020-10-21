;; AGI Sound Resource 29 (Volume 2 Offset 257187)


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
i11	0	3	0	592
i11	3	3	0	544
i11	6	3	0	496
i11	9	3	0	416
i11	12	6	3	432
i11	18	6	0	416
i11	24	9	5	432
i11	33	6	1	416
i11	39	12	6	432
i11	51	6	2	416
i11	57	15	7	432
i11	72	6	3	416
i11	78	3	7	432
i11	81	3	8	432
i11	84	3	9	432
i11	87	3	10	432
i11	90	3	11	432
i11	93	3	12	432
i11	96	3	13	432
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
i99	0	159	0.9	1.0	1.0
