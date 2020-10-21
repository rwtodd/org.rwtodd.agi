;; AGI Sound Resource 21 (Volume 0 Offset 64163)


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
i11	11	5	0	42
i11	16	4	0	40
i11	20	4	0	42
i11	24	3	0	40
i11	29	3	0	42
i11	32	3	0	40
i11	36	3	0	42
i11	39	4	0	40
i11	43	4	0	42
i11	47	3	0	40
i11	50	4	0	42
i11	54	4	0	40
i11	58	4	0	42
i11	62	3	0	40
i11	65	4	0	42
i11	69	4	0	40
i11	73	4	0	42
i11	77	3	0	40
i11	80	4	0	42
i11	84	4	0	40
i11	88	4	0	42
i11	92	3	0	40
i11	95	4	0	42
i11	99	4	0	40
i11	103	4	0	42
i11	107	3	0	339
i11	111	3	0	360
i11	114	4	0	381
i11	118	4	0	403
i11	122	3	0	428
i11	125	4	0	453
i11	129	4	0	480
i11	133	4	0	508
i11	137	3	0	539
i11	140	4	0	571
i11	144	4	0	605
i11	148	4	0	641
i11	152	35	0	679
i11	307	13	0	339
i11	320	16	0	320
i11	336	90	0	226
i11	426	11	0	339
i11	437	14	0	320
i11	453	108	0	214
i11	561	14	0	339
i11	575	18	0	320
i11	595	117	0	226
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	7	7	0	679
i12	23	7	4	1017
i12	39	8	4	906
i12	55	8	4	679
i12	72	7	4	1017
i12	88	7	4	906
i12	104	8	4	679
i12	120	8	4	1017
i12	137	7	4	906
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	107	4	3	85
i13	111	4	3	80
i13	115	3	3	76
i13	118	4	3	71
i13	122	4	3	67
i13	126	4	3	64
i13	130	3	3	60
i13	133	4	3	57
i13	137	4	3	53
i13	141	4	3	50
i13	145	3	3	48
i13	148	4	3	45
i13	152	36	3	42
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	772	0.9	1.0	1.0
