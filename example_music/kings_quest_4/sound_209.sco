;; AGI Sound Resource 209 (Volume 3 Offset 339214)


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
i11	75	60	0	180
i11	141	16	0	190
i11	165	27	0	214
i11	197	36	0	214
i11	233	32	0	190
i11	267	25	0	180
i11	312	37	0	190
i11	371	18	0	240
i11	389	33	0	285
i11	431	43	0	285
i11	474	77	0	240
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	87	113	0	120
i12	201	112	0	180
i12	317	49	0	190
i12	370	23	0	214
i12	393	41	0	190
i12	435	119	0	240
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	78	121	0	120
i13	201	112	0	180
i13	316	49	0	190
i13	369	23	0	214
i13	392	41	0	190
i13	434	120	0	240
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	614	0.9	1.0	1.0
