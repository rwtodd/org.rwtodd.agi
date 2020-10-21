;; AGI Sound Resource 55 (Volume 2 Offset 552227)


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
i11	4	3	0	170
i11	7	6	0	160
i11	13	13	0	170
i11	26	14	0	160
i11	40	9	0	135
i11	49	16	0	127
i11	65	37	0	113
i11	102	15	0	107
i11	117	14	0	95
i11	131	6	0	107
i11	137	3	0	95
i11	140	8	0	107
i11	148	12	0	113
i11	160	14	0	127
i11	174	42	0	135
i11	216	6	0	160
i11	222	2	0	151
i11	224	16	0	170
i11	240	13	0	160
i11	253	11	0	135
i11	264	13	0	127
i11	277	14	0	113
i11	293	12	0	107
i11	305	15	0	95
i11	320	10	0	90
i11	332	4	0	95
i11	336	3	0	90
i11	339	13	0	95
i11	352	17	0	107
i11	369	17	0	113
i11	386	25	0	127
i11	412	111	0	113
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	583	0.9	1.0	1.0
