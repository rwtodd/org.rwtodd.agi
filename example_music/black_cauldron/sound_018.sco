;; AGI Sound Resource 18 (Volume 2 Offset 90880)


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
i11	0	3	2	992
i11	3	3	1	992
i11	6	3	0	896
i11	15	3	2	976
i11	18	3	1	960
i11	21	3	0	784
i11	42	3	2	1008
i11	45	3	1	976
i11	48	3	0	816
i11	93	3	2	816
i11	99	3	1	912
i11	102	3	0	960
i11	126	3	0	912
i11	129	3	1	832
i11	147	3	1	832
i11	150	3	0	928
i11	168	3	1	912
i11	171	3	0	944
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	9	3	0	16
i21	12	3	6	16
i21	21	3	2	32
i21	24	3	5	64
i21	27	3	4	32
i21	30	9	0	16
i21	39	3	1	32
i21	48	3	0	16
i21	51	3	1	32
i21	54	3	2	16
i21	57	3	1	16
i21	60	3	0	32
i21	63	3	2	32
i21	66	3	5	16
i21	69	6	0	16
i21	75	3	2	16
i21	78	3	0	16
i21	81	3	3	16
i21	84	6	0	16
i21	90	3	3	16
i21	96	3	1	16
i21	105	3	4	16
i21	108	3	0	32
i21	111	3	0	16
i21	114	3	0	64
i21	117	3	2	16
i21	120	3	4	16
i21	123	3	7	16
i21	132	3	5	16
i21	135	3	3	16
i21	138	3	1	16
i21	141	3	7	16
i21	144	3	6	16
i21	150	3	9	16
i21	153	3	8	16
i21	156	3	5	16
i21	159	6	7	16
i21	165	3	4	16
i21	174	3	0	16
i21	177	3	0	32
i21	180	3	0	64
i21	183	3	6	16
i21	186	3	0	64
i21	189	3	6	32
i21	192	3	0	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	255	0.9	1.0	1.0
