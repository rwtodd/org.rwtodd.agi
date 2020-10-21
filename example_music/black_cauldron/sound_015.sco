;; AGI Sound Resource 15 (Volume 1 Offset 151277)


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
i11	0	6	0	992
i11	6	3	1	1008
i11	9	3	2	1008
i11	12	3	3	1008
i11	33	6	0	992
i11	39	6	1	1008
i11	45	6	2	1008
i11	57	6	0	976
i11	63	6	1	992
i11	69	6	2	992
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	4	32
i21	3	3	4	64
i21	33	3	4	32
i21	36	3	4	64
i21	57	3	4	32
i21	60	3	4	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	135	0.9	1.0	1.0
