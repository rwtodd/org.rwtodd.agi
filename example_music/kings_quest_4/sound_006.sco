;; AGI Sound Resource 6 (Volume 0 Offset 62989)


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
i11	1	25	0	339
i11	26	7	0	381
i11	34	20	0	428
i11	54	9	0	453
i11	63	22	0	508
i11	85	10	0	571
i11	95	23	0	641
i11	119	9	0	679
i11	128	128	0	719
i11	256	5	0	762
i11	261	6	0	719
i11	268	15	0	679
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	23	1	339
i12	26	7	1	381
i12	33	22	1	428
i12	55	9	1	453
i12	64	21	1	508
i12	85	10	1	571
i12	96	22	1	641
i12	118	7	1	679
i12	129	128	1	719
i12	257	6	1	762
i12	263	6	1	719
i12	269	14	1	679
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	1	25	2	42
i13	26	7	2	48
i13	34	20	2	53
i13	54	9	2	57
i13	63	22	2	64
i13	85	10	2	71
i13	95	23	2	80
i13	119	9	2	85
i13	128	128	2	90
i13	256	5	2	95
i13	261	6	2	90
i13	268	15	2	85
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	343	0.9	1.0	1.0
