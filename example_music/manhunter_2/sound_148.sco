;; AGI Sound Resource 148 (Volume 3 Offset 27060)


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
i11	0	15	0	944
i11	15	3	0	1008
i11	18	15	0	960
i11	33	3	0	1008
i11	36	15	0	976
i11	51	3	0	1008
i11	54	15	0	976
i11	69	3	0	1008
i11	72	15	0	976
i11	87	3	0	1008
i11	90	15	0	976
i11	105	3	0	1008
i11	108	15	0	976
i11	123	3	0	1008
i11	126	15	0	976
i11	141	3	0	1008
i11	144	15	0	976
i11	159	3	0	1008
i11	162	15	0	976
i11	177	3	0	1008
i11	180	15	0	976
i11	195	3	0	1008
i11	198	15	0	976
i11	213	3	0	1008
i11	216	15	0	992
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
i99	0	291	0.9	1.0	1.0
