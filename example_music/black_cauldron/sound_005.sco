;; AGI Sound Resource 5 (Volume 1 Offset 149527)


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
i21	0	18	4	64
i21	18	15	3	64
i21	33	15	2	64
i21	48	15	1	64
i21	63	18	0	64
i21	81	12	1	64
i21	93	9	2	64
i21	102	9	3	64
i21	111	9	4	64
i21	120	6	5	64
i21	126	6	4	64
i21	132	6	3	64
i21	138	6	2	64
i21	144	3	1	64
i21	150	3	0	64
i21	156	3	0	64
i21	162	3	2	64
i21	168	6	3	64
i21	177	6	4	64
i21	189	3	5	64
i21	192	9	6	64
i21	207	12	8	64
i21	228	3	9	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	291	0.9	1.0	1.0
