;; AGI Sound Resource 7 (Volume 1 Offset 374890)


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
i11	8	4	0	127
i11	12	3	0	113
i11	15	19	0	127
i11	34	5	0	113
i11	53	6	0	107
i11	73	6	0	127
i11	91	3	0	95
i11	94	3	0	85
i11	97	16	0	95
i11	114	9	0	107
i11	123	6	0	113
i11	134	8	0	127
i11	142	7	0	113
i11	151	13	0	143
i11	170	1	0	127
i11	171	4	0	113
i11	175	18	0	127
i11	193	7	0	113
i11	211	7	0	107
i11	232	7	0	127
i11	246	3	0	113
i11	249	4	0	107
i11	253	9	0	113
i11	262	9	0	127
i11	271	9	0	143
i11	281	13	0	113
i11	294	77	0	127
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	431	0.9	1.0	1.0
