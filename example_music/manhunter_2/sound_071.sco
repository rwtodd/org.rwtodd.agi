;; AGI Sound Resource 71 (Volume 2 Offset 172599)


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
i11	3	11	0	428
i11	23	10	0	428
i11	42	10	0	428
i11	61	33	0	508
i11	99	11	0	381
i11	118	11	0	381
i11	137	11	0	381
i11	157	33	0	508
i11	195	11	0	428
i11	214	11	0	428
i11	233	11	0	428
i11	252	11	0	508
i11	271	11	0	508
i11	291	10	0	508
i11	310	10	0	381
i11	329	11	0	381
i11	348	11	0	381
i11	367	38	0	508
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	4	11	0	339
i12	23	11	0	339
i12	42	11	0	339
i12	61	34	0	339
i12	157	33	0	339
i12	195	11	0	339
i12	214	11	0	339
i12	234	10	0	339
i12	253	10	0	339
i12	272	11	0	339
i12	291	11	0	339
i12	310	11	0	381
i12	329	11	0	381
i12	348	11	0	381
i12	367	38	0	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	3	11	0	285
i13	22	11	0	285
i13	41	11	0	285
i13	61	33	0	254
i13	99	11	0	254
i13	118	11	0	254
i13	137	11	0	254
i13	156	34	0	254
i13	195	10	0	285
i13	214	10	0	285
i13	233	11	0	285
i13	252	11	0	254
i13	271	11	0	254
i13	290	11	0	254
i13	309	11	0	254
i13	329	10	0	254
i13	348	10	0	254
i13	367	38	0	254
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	465	0.9	1.0	1.0
