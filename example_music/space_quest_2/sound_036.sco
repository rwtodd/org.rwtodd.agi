;; AGI Sound Resource 36 (Volume 2 Offset 216621)


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
i11	0	3	0	1008
i11	12	6	0	1008
i11	120	3	0	992
i11	132	6	0	992
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	48
i12	12	3	2	48
i12	15	3	4	48
i12	18	3	7	48
i12	120	3	0	48
i12	132	3	2	48
i12	135	3	4	48
i12	138	3	7	48
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	24	3	4	64
i21	27	3	3	64
i21	30	21	1	64
i21	51	3	2	64
i21	54	3	3	64
i21	57	3	4	64
i21	60	3	5	64
i21	63	3	6	64
i21	66	3	7	64
i21	69	3	8	64
i21	72	3	9	64
i21	75	3	10	64
i21	78	3	11	64
i21	81	3	12	64
i21	84	3	13	64
i21	87	3	14	64
i21	117	6	14	32
i21	123	12	3	32
i21	135	6	2	32
i21	141	39	1	32
i21	180	6	2	32
i21	186	3	3	32
i21	189	3	4	32
i21	192	3	5	32
i21	195	3	6	32
i21	198	3	7	32
i21	201	3	8	32
i21	204	3	9	32
i21	207	3	10	32
i21	210	3	11	32
i21	213	3	12	32
i21	216	3	13	32
i21	219	3	14	32
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	282	0.9	1.0	1.0
