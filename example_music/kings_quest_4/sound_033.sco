;; AGI Sound Resource 33 (Volume 3 Offset 338176)


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
i11	13	63	0	80
i11	76	20	0	85
i11	96	42	0	95
i11	138	64	0	64
i11	202	20	0	71
i11	222	35	0	80
i11	258	4	0	85
i11	263	3	0	80
i11	266	65	0	85
i11	331	22	0	95
i11	353	43	0	107
i11	398	95	0	71
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	9	21	2	381
i12	30	23	2	254
i12	54	41	2	190
i12	138	20	2	381
i12	158	22	2	254
i12	180	40	2	190
i12	266	20	2	428
i12	286	23	2	339
i12	310	47	2	254
i12	397	22	2	428
i12	419	24	2	339
i12	444	50	2	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	11	23	2	127
i13	34	20	2	160
i13	55	44	2	127
i13	142	19	2	190
i13	161	20	2	127
i13	199	22	2	95
i13	221	27	2	80
i13	309	20	2	85
i13	330	21	2	107
i13	353	28	2	127
i13	441	19	2	127
i13	460	24	2	107
i13	487	27	2	85
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	574	0.9	1.0	1.0
