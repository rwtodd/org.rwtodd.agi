;; AGI Sound Resource 12 (Volume 2 Offset 549495)


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
i11	4	43	0	381
i11	47	42	0	269
i11	89	55	0	143
i11	145	74	0	90
i11	251	6	0	85
i11	260	6	0	85
i11	269	6	0	85
i11	278	6	0	85
i11	287	6	0	85
i11	299	7	0	90
i11	312	5	0	95
i11	329	31	0	120
i11	388	3	0	85
i11	394	4	0	85
i11	401	5	0	85
i11	408	6	0	85
i11	421	6	0	90
i11	434	5	0	95
i11	451	74	0	120
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	113	15	3	679
i12	128	14	3	641
i12	142	9	3	679
i12	151	10	3	641
i12	161	7	3	679
i12	168	8	3	641
i12	176	5	3	679
i12	181	5	3	641
i12	188	4	3	679
i12	192	5	3	641
i12	197	5	3	679
i12	202	4	3	641
i12	207	3	3	679
i12	210	5	3	641
i12	215	4	3	679
i12	219	4	3	641
i12	224	3	3	679
i12	227	4	3	641
i12	235	5	3	641
i12	240	128	3	679
i12	368	13	3	719
i12	381	21	3	762
i12	402	131	3	960
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	593	0.9	1.0	1.0
