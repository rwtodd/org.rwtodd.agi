;; AGI Sound Resource 8 (Volume 0 Offset 19963)


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
i11	0	3	0	576
i11	3	3	1	576
i11	6	3	2	576
i11	9	3	1	576
i11	12	6	0	592
i11	18	3	1	624
i11	21	6	2	672
i11	27	3	1	720
i11	30	3	0	768
i11	33	6	1	816
i11	39	3	2	848
i11	42	6	1	880
i11	48	3	0	912
i11	51	6	1	944
i11	57	6	2	976
i11	63	3	1	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	560
i12	3	3	1	560
i12	6	3	2	560
i12	9	3	1	560
i12	12	6	0	576
i12	18	3	1	608
i12	21	6	2	656
i12	27	3	1	704
i12	30	3	0	736
i12	33	6	1	784
i12	39	3	2	816
i12	42	6	1	864
i12	48	3	0	896
i12	51	6	1	928
i12	57	6	2	960
i12	63	3	1	992
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	102	3	0	32
i21	105	27	0	16
i21	132	6	1	16
i21	138	6	2	16
i21	144	6	3	16
i21	150	6	4	16
i21	156	6	5	16
i21	162	6	6	16
i21	168	6	7	16
i21	174	6	8	16
i21	180	6	9	16
i21	186	6	10	16
i21	192	6	11	16
i21	198	9	12	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	267	0.9	1.0	1.0
