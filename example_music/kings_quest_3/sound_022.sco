;; AGI Sound Resource 22 (Volume 1 Offset 116417)


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
i11	0	90	0	208
i11	90	30	0	220
i11	120	30	0	262
i11	150	30	0	278
i11	180	181	0	294
i11	361	90	0	208
i11	451	30	0	220
i11	481	30	0	262
i11	511	30	0	278
i11	541	30	0	294
i11	571	30	0	312
i11	601	181	0	330
i11	782	30	0	208
i11	812	30	0	220
i11	842	90	0	262
i11	932	30	0	278
i11	962	30	0	330
i11	992	30	0	350
i11	1022	180	0	371
i11	1202	30	0	393
i11	1233	30	0	330
i11	1263	30	0	278
i11	1293	30	0	233
i11	1323	30	0	196
i11	1353	30	0	165
i11	1383	120	0	165
i11	1503	53	0	196
i11	1563	180	0	165
i11	1744	180	0	175
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	233	3	2	208
i12	243	5	2	208
i12	293	3	2	208
i12	303	3	2	208
i12	652	2	2	208
i12	654	3	2	208
i12	664	3	2	208
i12	714	3	2	208
i12	717	2	2	208
i12	724	3	2	208
i12	1075	2	2	208
i12	1078	2	2	208
i12	1085	3	2	208
i12	1088	2	2	208
i12	1135	3	2	208
i12	1138	2	2	208
i12	1145	3	2	208
i12	1203	32	2	393
i12	1235	88	2	330
i12	1323	60	2	233
i12	1561	353	2	220
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	233	2	1	220
i13	243	5	1	220
i13	293	2	1	220
i13	303	2	1	220
i13	651	5	1	220
i13	664	5	1	220
i13	714	5	1	220
i13	724	5	1	220
i13	1074	5	1	220
i13	1084	5	1	220
i13	1134	5	1	220
i13	1144	5	1	220
i13	1202	90	1	393
i13	1292	88	1	278
i13	1382	181	1	278
i13	1563	353	1	294
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1984	0.9	1.0	1.0
