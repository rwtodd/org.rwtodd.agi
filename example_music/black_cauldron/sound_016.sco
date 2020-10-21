;; AGI Sound Resource 16 (Volume 0 Offset 9073)


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
i11	354	3	8	16
i11	357	3	8	48
i11	360	3	8	96
i11	363	3	8	128
i11	366	3	8	192
i11	369	3	8	272
i11	372	3	8	336
i11	375	3	8	400
i11	378	3	8	464
i11	381	3	8	528
i11	384	3	8	592
i11	387	3	8	656
i11	390	3	8	720
i11	393	3	8	784
i11	396	3	8	848
i11	399	3	8	912
i11	402	3	8	976
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	9	16
i21	3	42	11	64
i21	45	3	9	16
i21	48	39	11	64
i21	87	3	9	16
i21	90	36	11	64
i21	126	3	9	16
i21	129	33	11	64
i21	162	3	9	16
i21	165	30	11	64
i21	195	3	9	16
i21	198	27	11	64
i21	225	3	9	16
i21	228	24	11	64
i21	252	3	9	16
i21	255	21	11	64
i21	276	3	9	16
i21	279	18	11	64
i21	297	3	9	16
i21	300	15	11	64
i21	315	3	9	16
i21	318	12	11	64
i21	330	3	9	16
i21	333	9	11	64
i21	342	3	9	16
i21	345	6	11	64
i21	351	3	9	16
i21	354	18	12	16
i21	372	18	12	32
i21	390	15	12	64
i21	411	3	7	16
i21	414	3	3	16
i21	417	3	0	16
i21	420	3	1	16
i21	423	3	3	16
i21	426	3	4	16
i21	429	3	5	16
i21	432	3	6	16
i21	435	3	7	16
i21	438	3	8	16
i21	441	3	9	16
i21	444	3	10	16
i21	447	3	11	16
i21	450	3	12	16
i21	453	3	13	16
i21	456	3	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	519	0.9	1.0	1.0
