;; AGI Sound Resource 2 (Volume 1 Offset 206271)


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
i11	12	6	0	339
i11	20	7	0	302
i11	29	26	0	269
i11	64	17	0	254
i11	81	18	0	202
i11	99	26	0	226
i11	133	17	0	269
i11	151	17	0	226
i11	168	26	0	254
i11	194	8	0	269
i11	203	17	0	254
i11	220	17	0	302
i11	237	69	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	29	26	0	453
i12	64	17	0	403
i12	81	17	0	360
i12	98	26	0	339
i12	133	26	0	453
i12	167	26	0	453
i12	202	26	0	360
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	29	26	0	679
i13	64	26	0	679
i13	99	25	0	539
i13	133	26	0	679
i13	168	26	0	719
i13	202	26	0	906
i13	237	69	0	679
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	366	0.9	1.0	1.0
