;; AGI Sound Resource 49 (Volume 1 Offset 48201)


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
i11	5	11	0	339
i11	20	11	0	107
i11	34	11	0	71
i11	49	11	0	42
i11	63	11	0	339
i11	78	11	0	113
i11	92	11	0	76
i11	107	11	0	48
i11	122	10	0	339
i11	136	11	0	127
i11	150	11	0	85
i11	165	11	0	53
i11	180	10	0	339
i11	194	11	0	143
i11	209	10	0	95
i11	223	11	0	57
i11	238	21	0	339
i11	267	29	0	320
i11	296	29	0	302
i11	325	23	0	285
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	49	10	0	107
i12	107	11	0	113
i12	165	11	0	127
i12	223	11	0	143
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	49	11	0	143
i13	107	11	0	151
i13	165	11	0	170
i13	223	11	0	190
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	408	0.9	1.0	1.0
