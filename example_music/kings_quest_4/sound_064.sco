;; AGI Sound Resource 64 (Volume 2 Offset 277750)


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
i11	106	9	0	254
i11	117	10	0	285
i11	127	11	0	320
i11	138	10	0	339
i11	148	11	0	381
i11	159	10	0	339
i11	169	7	0	320
i11	180	11	0	285
i11	191	72	0	254
i11	275	96	0	381
i11	402	10	0	254
i11	413	10	0	285
i11	423	11	0	320
i11	434	11	0	339
i11	445	10	0	381
i11	455	11	0	339
i11	466	10	0	320
i11	476	11	0	285
i11	487	77	0	254
i11	572	72	0	381
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	21	11	2	500
i12	106	10	2	762
i12	190	11	2	500
i12	275	11	2	762
i12	360	10	2	500
i12	444	11	2	762
i12	529	11	2	500
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	21	117	3	381
i13	148	11	3	190
i13	170	4	3	127
i13	191	71	3	127
i13	360	11	3	190
i13	381	6	3	127
i13	403	42	3	127
i13	445	10	3	381
i13	466	5	3	254
i13	487	37	3	254
i13	529	5	3	254
i13	551	2	3	190
i13	572	71	3	190
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	704	0.9	1.0	1.0
