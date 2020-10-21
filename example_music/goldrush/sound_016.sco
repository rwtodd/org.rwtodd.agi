;; AGI Sound Resource 16 (Volume 2 Offset 328761)


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
i11	7	14	0	127
i11	25	14	0	127
i11	44	14	0	127
i11	63	14	0	127
i11	82	7	0	113
i11	91	14	0	127
i11	110	33	0	151
i11	147	7	0	135
i11	157	14	0	127
i11	176	6	0	127
i11	185	7	0	151
i11	194	7	0	127
i11	204	23	0	113
i11	232	56	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	25	14	0	135
i12	44	14	0	151
i12	63	14	0	170
i12	81	28	0	151
i12	109	29	0	190
i12	156	14	0	190
i12	175	14	0	190
i12	194	7	0	190
i12	203	24	0	190
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	7	14	0	254
i13	25	15	0	254
i13	44	14	0	254
i13	63	14	0	254
i13	82	23	0	381
i13	110	28	0	381
i13	157	14	0	339
i13	176	14	0	339
i13	194	7	0	339
i13	204	23	0	339
i13	232	7	0	254
i13	251	7	0	339
i13	270	7	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	348	0.9	1.0	1.0
