;; AGI Sound Resource 25 (Volume 2 Offset 550333)


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
i11	45	70	0	254
i11	121	24	0	339
i11	146	25	0	285
i11	171	26	0	320
i11	197	23	0	339
i11	222	21	0	381
i11	244	4	0	320
i11	248	3	0	285
i11	251	68	0	320
i11	323	26	0	428
i11	349	85	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	44	9	0	508
i12	95	5	0	254
i12	146	7	0	508
i12	196	6	0	254
i12	247	9	0	508
i12	298	4	0	254
i12	348	7	0	508
i12	399	6	0	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	44	138	0	170
i13	247	55	0	160
i13	349	82	0	170
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	494	0.9	1.0	1.0
