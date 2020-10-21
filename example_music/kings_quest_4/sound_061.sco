;; AGI Sound Resource 61 (Volume 2 Offset 554559)


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
i11	7	4	0	95
i11	28	4	0	107
i11	39	7	0	120
i11	63	7	0	127
i11	75	7	0	143
i11	97	6	0	160
i11	109	7	0	180
i11	133	11	0	190
i11	149	129	0	202
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	5	67	1	190
i12	73	64	1	285
i12	146	60	1	302
i12	226	51	1	202
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	338	0.9	1.0	1.0
