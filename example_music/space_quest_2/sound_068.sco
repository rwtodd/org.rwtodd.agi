;; AGI Sound Resource 68 (Volume 0 Offset 26725)


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
i11	0	37	0	254
i11	50	14	0	269
i11	71	4	0	254
i11	80	4	0	269
i11	91	5	0	285
i11	114	64	0	339
i11	190	7	0	285
i11	215	7	0	302
i11	232	7	0	381
i11	249	5	0	320
i11	265	7	0	339
i11	281	5	0	428
i11	301	9	0	360
i11	327	9	0	381
i11	357	139	0	539
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	36	0	302
i12	50	14	0	320
i12	71	4	0	302
i12	80	4	0	320
i12	91	5	0	339
i12	114	64	0	403
i12	190	7	0	339
i12	215	7	0	360
i12	232	7	0	453
i12	249	5	0	381
i12	265	7	0	403
i12	281	5	0	508
i12	301	9	0	428
i12	326	10	0	453
i12	357	139	0	641
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	37	0	302
i13	50	14	0	320
i13	71	4	0	302
i13	80	4	0	320
i13	91	5	0	339
i13	114	64	0	403
i13	190	7	0	339
i13	215	7	0	360
i13	233	6	0	453
i13	249	5	0	381
i13	265	7	0	403
i13	281	5	0	508
i13	301	9	0	428
i13	327	9	0	453
i13	357	139	0	641
i13	601	1	0	0
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	662	0.9	1.0	1.0
