;; AGI Sound Resource 29 (Volume 1 Offset 96410)


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
i11	1	39	0	302
i11	40	15	0	269
i11	55	9	0	254
i11	81	9	0	302
i11	163	37	0	302
i11	203	13	0	269
i11	217	8	0	254
i11	245	7	0	302
i11	273	109	0	214
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	1	39	2	605
i12	41	14	2	539
i12	55	9	2	508
i12	81	9	2	605
i12	163	37	2	605
i12	203	13	2	539
i12	217	8	2	508
i12	245	7	2	605
i12	273	109	2	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	40	4	185
i13	40	15	4	53
i13	55	9	4	1017
i13	81	8	4	185
i13	163	36	4	185
i13	203	12	4	53
i13	217	8	4	1017
i13	245	7	4	185
i13	272	109	4	855
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	442	0.9	1.0	1.0
