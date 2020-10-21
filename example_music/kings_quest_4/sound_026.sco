;; AGI Sound Resource 26 (Volume 2 Offset 550506)


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
i11	3	3	0	127
i11	8	5	0	113
i11	17	5	0	107
i11	25	5	0	95
i11	34	5	0	85
i11	53	4	0	107
i11	69	6	0	95
i11	86	5	0	113
i11	105	5	0	143
i11	125	5	0	113
i11	223	5	0	127
i11	230	5	0	113
i11	238	6	0	107
i11	245	6	0	95
i11	253	5	0	85
i11	270	5	0	107
i11	288	4	0	95
i11	292	4	0	85
i11	297	3	0	95
i11	300	5	0	85
i11	307	3	0	95
i11	310	5	0	113
i11	331	5	0	143
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	103	2	127
i12	105	106	2	143
i12	221	59	2	127
i12	289	87	2	143
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	214	170	2	254
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	444	0.9	1.0	1.0
