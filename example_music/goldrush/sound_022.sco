;; AGI Sound Resource 22 (Volume 2 Offset 555080)


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
i11	24	23	0	339
i11	55	37	0	254
i11	99	8	0	269
i11	114	23	0	254
i11	144	23	0	202
i11	175	37	0	226
i11	219	8	0	240
i11	234	23	0	226
i11	265	7	0	202
i11	279	8	0	226
i11	295	37	0	254
i11	339	8	0	302
i11	354	23	0	302
i11	384	23	0	339
i11	414	113	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	55	113	0	403
i12	175	112	0	339
i12	295	112	0	381
i12	415	112	0	403
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	55	113	0	339
i13	175	113	0	269
i13	295	113	0	302
i13	415	113	0	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	588	0.9	1.0	1.0
