;; AGI Sound Resource 3 (Volume 0 Offset 44415)


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
i11	0	3	1	32
i11	3	3	0	48
i11	6	3	2	32
i11	9	3	0	48
i11	12	3	0	32
i11	15	3	1	48
i11	18	3	1	32
i11	21	3	0	48
i11	24	3	0	32
i11	27	3	0	48
i11	30	3	1	32
i11	33	3	1	16
i11	63	6	0	16
i11	69	6	2	80
i11	75	3	6	64
i11	87	3	6	64
i11	90	3	3	64
i11	93	3	0	64
i11	96	3	2	64
i11	105	3	2	240
i11	108	3	2	192
i11	111	3	3	128
i11	114	3	1	64
i11	117	3	0	16
i11	126	3	4	240
i11	129	3	1	192
i11	132	3	1	128
i11	135	3	1	64
i11	138	3	1	32
i11	141	3	0	16
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	112
i12	3	3	3	96
i12	6	3	3	112
i12	9	3	2	96
i12	12	3	3	112
i12	15	3	0	96
i12	18	3	3	112
i12	21	3	0	96
i12	24	3	0	112
i12	27	3	0	96
i12	30	3	4	112
i12	33	3	1	96
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	204	0.9	1.0	1.0
