;; AGI Sound Resource 40 (Volume 2 Offset 551938)


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
i11	19	6	0	170
i11	27	6	0	160
i11	36	6	0	120
i11	44	5	0	113
i11	52	6	0	90
i11	59	6	0	85
i11	69	4	0	60
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	19	5	3	571
i12	26	6	3	508
i12	34	5	3	571
i12	42	4	3	508
i12	47	4	3	571
i12	54	2	3	508
i12	57	2	3	571
i12	60	2	3	508
i12	64	2	3	571
i12	69	4	3	508
i12	74	2	3	571
i12	77	2	3	508
i12	82	2	3	571
i12	86	12	3	508
i12	98	17	3	679
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	19	9	2	90
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	175	0.9	1.0	1.0
