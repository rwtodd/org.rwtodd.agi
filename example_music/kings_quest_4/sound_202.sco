;; AGI Sound Resource 202 (Volume 0 Offset 91579)


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
i11	7	39	0	80
i11	46	11	0	85
i11	57	22	0	95
i11	83	30	0	95
i11	113	27	0	85
i11	140	37	0	80
i11	177	39	0	85
i11	216	14	0	107
i11	230	24	0	127
i11	263	31	0	127
i11	294	46	0	107
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	4	77	0	381
i12	83	51	0	190
i12	177	26	0	508
i12	203	28	0	339
i12	231	12	0	254
i12	253	84	0	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	34	100	0	254
i13	174	85	0	428
i13	261	59	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	400	0.9	1.0	1.0
