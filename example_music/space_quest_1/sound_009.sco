;; AGI Sound Resource 9 (Volume 0 Offset 33766)


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
i11	0	3	0	896
i11	3	3	8	896
i11	6	3	0	896
i11	9	3	9	896
i11	12	3	0	896
i11	15	3	9	896
i11	18	3	0	896
i11	21	3	9	896
i11	24	3	0	896
i11	27	3	8	896
i11	30	3	0	896
i11	33	3	8	896
i11	36	3	0	896
i11	39	3	8	896
i11	42	3	0	896
i11	45	3	8	896
i11	48	6	0	896
i11	54	6	1	896
i11	60	6	2	896
i11	66	9	3	896
i11	75	9	4	896
i11	84	9	5	896
i11	93	12	6	896
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	976
i12	3	3	5	976
i12	6	3	0	976
i12	9	3	5	976
i12	12	3	0	976
i12	15	3	5	976
i12	18	3	0	976
i12	21	3	5	976
i12	24	3	0	976
i12	27	3	5	976
i12	30	3	0	976
i12	33	3	5	976
i12	36	3	0	976
i12	39	3	5	976
i12	42	3	0	976
i12	45	3	4	976
i12	48	6	0	976
i12	54	6	1	976
i12	60	6	2	976
i12	66	9	3	976
i12	75	9	4	976
i12	84	12	5	976
i12	96	9	6	976
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	12	0	64
i21	12	36	0	16
i21	48	9	1	16
i21	57	9	2	16
i21	66	9	3	16
i21	75	9	4	16
i21	84	12	5	16
i21	96	9	6	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	165	0.9	1.0	1.0
