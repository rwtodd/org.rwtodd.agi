;; AGI Sound Resource 19 (Volume 1 Offset 552273)


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
i11	14	11	0	127
i11	35	11	0	127
i11	57	11	0	127
i11	78	11	0	151
i11	100	10	0	127
i11	121	11	0	120
i11	143	10	0	127
i11	164	32	0	151
i11	207	32	0	170
i11	250	10	0	160
i11	271	11	0	170
i11	293	42	0	190
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	14	11	0	160
i12	36	10	0	160
i12	57	11	0	160
i12	79	10	0	190
i12	100	11	0	160
i12	122	10	0	143
i12	143	11	0	160
i12	164	33	0	190
i12	207	32	0	202
i12	250	11	0	190
i12	272	10	0	202
i12	293	43	0	254
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	396	0.9	1.0	1.0
