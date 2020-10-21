;; AGI Sound Resource 12 (Volume 0 Offset 147853)


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
i11	0	3	0	944
i11	3	3	3	1008
i11	9	3	1	944
i11	12	3	4	1008
i11	18	3	2	944
i11	21	3	5	1008
i11	27	3	3	944
i11	30	3	6	1008
i11	36	3	4	944
i11	39	3	7	1008
i11	45	3	5	944
i11	48	3	8	1008
i11	54	3	6	944
i11	57	3	9	1008
i11	63	3	7	944
i11	66	3	10	1008
i11	72	3	8	944
i11	75	3	11	1008
i11	81	3	9	944
i11	84	3	11	1008
i11	90	3	10	944
i11	93	3	13	1008
i11	99	3	11	944
i11	102	3	14	1008
i11	108	3	12	944
i11	111	3	14	1008
i11	117	3	13	944
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	928
i13	3	3	3	992
i13	9	3	1	928
i13	12	3	4	992
i13	18	3	2	928
i13	21	3	5	992
i13	27	3	3	928
i13	30	3	6	992
i13	36	3	4	928
i13	39	3	7	992
i13	45	3	5	928
i13	48	3	8	992
i13	54	3	6	928
i13	57	3	9	992
i13	63	3	7	928
i13	66	3	10	992
i13	72	3	8	928
i13	75	3	11	992
i13	81	3	9	928
i13	84	3	12	992
i13	90	3	10	928
i13	93	3	13	992
i13	99	3	11	928
i13	102	3	14	992
i13	108	3	12	928
i13	114	3	14	1008
i13	117	3	13	928
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	0	32
i21	3	3	3	64
i21	9	3	1	32
i21	12	3	4	64
i21	18	3	2	32
i21	21	3	5	64
i21	27	3	3	32
i21	30	3	6	64
i21	36	3	4	32
i21	39	3	7	64
i21	45	3	5	32
i21	48	3	8	64
i21	54	3	6	32
i21	57	3	9	64
i21	63	3	7	32
i21	66	3	10	64
i21	72	3	8	32
i21	75	3	11	64
i21	81	3	9	32
i21	84	3	12	64
i21	90	3	10	32
i21	93	3	13	64
i21	99	3	11	32
i21	102	3	14	64
i21	108	3	12	32
i21	117	3	13	32
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	180	0.9	1.0	1.0
