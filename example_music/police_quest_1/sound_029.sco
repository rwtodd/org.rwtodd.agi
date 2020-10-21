;; AGI Sound Resource 29 (Volume 2 Offset 135198)


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
i11	0	12	0	453
i11	26	10	0	360
i11	38	30	0	381
i11	76	11	0	381
i11	99	10	0	302
i11	109	25	0	320
i11	147	13	0	320
i11	171	10	0	254
i11	183	14	0	269
i11	207	9	0	214
i11	218	11	0	226
i11	243	11	0	180
i11	255	55	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	12	0	906
i12	26	11	0	719
i12	37	25	0	762
i12	77	10	0	762
i12	98	10	0	605
i12	110	18	0	641
i12	147	12	0	641
i12	173	11	0	508
i12	184	9	0	539
i12	208	12	0	428
i12	221	13	0	453
i12	245	10	0	360
i12	256	55	0	381
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	77	13	0	453
i13	100	11	0	360
i13	111	26	0	381
i13	149	10	0	381
i13	173	10	0	302
i13	184	12	0	320
i13	209	11	0	254
i13	220	13	0	269
i13	244	10	0	214
i13	256	54	0	226
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	371	0.9	1.0	1.0
