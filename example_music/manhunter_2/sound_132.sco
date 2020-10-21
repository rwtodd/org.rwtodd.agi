;; AGI Sound Resource 132 (Volume 3 Offset 175601)


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
i11	0	9	0	912
i11	9	9	0	816
i11	18	9	0	752
i11	33	9	0	912
i11	42	9	0	816
i11	51	9	0	752
i11	66	9	0	912
i11	75	9	0	816
i11	84	9	0	752
i11	99	9	0	912
i11	108	9	0	816
i11	117	9	0	752
i11	132	9	0	912
i11	141	9	0	816
i11	150	9	0	752
i11	165	9	0	912
i11	174	9	0	816
i11	183	9	0	752
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
i99	0	252	0.9	1.0	1.0
