;; AGI Sound Resource 13 (Volume 0 Offset 30143)


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
i11	12	7	0	428
i11	21	7	0	428
i11	31	7	0	302
i11	40	7	0	285
i11	49	7	0	428
i11	59	7	0	428
i11	68	7	0	302
i11	77	7	0	285
i11	87	7	0	428
i11	96	7	0	428
i11	106	7	0	302
i11	115	7	0	285
i11	124	7	0	428
i11	134	7	0	428
i11	143	7	0	302
i11	152	7	0	285
i11	162	16	0	160
i11	181	16	0	160
i11	199	7	0	127
i11	209	16	0	127
i11	228	6	0	127
i11	237	7	0	143
i11	246	7	0	143
i11	256	7	0	170
i11	265	7	0	214
i11	274	14	0	190
i11	293	7	0	180
i11	303	6	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	162	7	0	428
i12	172	7	0	428
i12	181	7	0	302
i12	190	7	0	285
i12	200	7	0	428
i12	209	7	0	428
i12	219	7	0	302
i12	228	7	0	285
i12	237	7	0	428
i12	247	7	0	428
i12	256	7	0	302
i12	265	7	0	285
i12	275	7	0	428
i12	284	7	0	428
i12	294	7	0	302
i12	303	7	0	285
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	370	0.9	1.0	1.0
