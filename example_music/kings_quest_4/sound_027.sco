;; AGI Sound Resource 27 (Volume 2 Offset 550688)


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
i11	5	22	0	339
i11	27	19	0	320
i11	47	4	0	339
i11	51	5	0	320
i11	56	115	0	339
i11	171	12	0	360
i11	183	21	0	381
i11	204	78	0	480
i11	282	28	0	453
i11	310	59	0	480
i11	369	68	0	381
i11	437	28	0	339
i11	465	19	0	320
i11	486	4	0	339
i11	490	5	0	320
i11	495	132	0	339
i11	627	14	0	360
i11	641	31	0	381
i11	672	211	0	480
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	6	256	2	339
i12	301	128	2	480
i12	429	90	2	339
i12	519	144	2	679
i12	663	152	2	480
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	164	110	2	170
i13	433	234	2	170
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	943	0.9	1.0	1.0
