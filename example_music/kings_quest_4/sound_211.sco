;; AGI Sound Resource 211 (Volume 0 Offset 94190)


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
i11	39	39	0	143
i11	86	8	0	151
i11	94	26	0	170
i11	122	34	0	170
i11	156	32	0	151
i11	188	29	0	170
i11	221	2	0	151
i11	224	37	0	170
i11	261	37	0	180
i11	298	47	0	202
i11	346	71	0	180
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	40	174	0	285
i12	214	68	0	302
i12	348	71	0	302
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	24	159	0	428
i13	183	18	0	285
i13	213	111	0	453
i13	346	71	0	453
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	479	0.9	1.0	1.0
