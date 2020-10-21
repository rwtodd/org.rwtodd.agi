;; AGI Sound Resource 19 (Volume 2 Offset 3840)


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
i11	0	3	0	320
i11	3	3	0	432
i11	6	3	0	560
i11	9	3	0	688
i11	12	3	0	800
i11	15	3	0	912
i11	18	3	0	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	24	6	0	64
i21	30	6	1	64
i21	36	6	2	64
i21	42	6	3	64
i21	48	6	4	64
i21	54	6	5	64
i21	60	6	6	64
i21	66	6	7	64
i21	72	6	8	64
i21	78	6	9	64
i21	84	6	10	64
i21	90	6	11	64
i21	96	3	12	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	159	0.9	1.0	1.0
