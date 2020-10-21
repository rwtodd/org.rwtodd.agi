;; AGI Sound Resource 19 (Volume 2 Offset 91321)


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
i21	0	18	0	64
i21	18	33	0	16
i21	51	3	4	16
i21	54	3	0	16
i21	57	3	4	16
i21	60	3	0	16
i21	63	3	4	16
i21	66	3	0	16
i21	69	3	4	16
i21	72	9	0	16
i21	84	3	0	16
i21	90	3	0	16
i21	96	9	0	16
i21	108	12	0	16
i21	126	6	0	16
i21	132	9	1	16
i21	141	3	2	16
i21	144	6	2	32
i21	150	6	3	32
i21	156	6	2	32
i21	162	6	2	16
i21	168	12	1	16
i21	180	6	0	16
i21	186	6	0	64
i21	192	12	1	32
i21	204	3	1	64
i21	207	3	2	64
i21	210	12	2	16
i21	222	6	3	16
i21	228	3	4	16
i21	231	3	5	16
i21	234	3	6	16
i21	237	3	8	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	300	0.9	1.0	1.0
