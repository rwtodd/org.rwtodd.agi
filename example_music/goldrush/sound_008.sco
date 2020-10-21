;; AGI Sound Resource 8 (Volume 1 Offset 333374)


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
i11	9	13	0	127
i11	23	13	0	95
i11	36	13	0	127
i11	49	13	0	113
i11	62	13	0	127
i11	76	13	0	151
i11	89	11	0	127
i11	102	11	0	127
i11	115	12	0	127
i11	128	12	0	95
i11	142	5	0	127
i11	148	5	0	127
i11	155	11	0	113
i11	168	12	0	127
i11	181	12	0	151
i11	195	11	0	190
i11	208	11	0	190
i11	221	12	0	127
i11	234	12	0	95
i11	248	11	0	127
i11	261	11	0	113
i11	274	12	0	127
i11	287	12	0	151
i11	301	11	0	127
i11	314	59	0	127
i11	380	12	0	151
i11	393	14	0	143
i11	413	7	0	113
i11	420	11	0	127
i11	433	12	0	143
i11	446	12	0	151
i11	460	11	0	190
i11	473	11	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	22	5	0	381
i12	35	5	0	254
i12	48	5	0	226
i12	61	5	0	254
i12	75	5	0	381
i12	88	5	0	254
i12	101	5	0	226
i12	114	5	0	254
i12	128	5	0	381
i12	141	5	0	254
i12	154	5	0	226
i12	167	5	0	254
i12	181	5	0	381
i12	194	5	0	254
i12	207	5	0	226
i12	220	5	0	254
i12	234	5	0	381
i12	247	5	0	254
i12	260	5	0	226
i12	273	5	0	254
i12	287	5	0	381
i12	300	5	0	254
i12	313	60	0	254
i12	379	5	0	302
i12	393	5	0	285
i12	412	5	0	226
i12	419	5	0	254
i12	432	5	0	285
i12	446	5	0	302
i12	459	5	0	381
i12	472	5	0	381
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	544	0.9	1.0	1.0
