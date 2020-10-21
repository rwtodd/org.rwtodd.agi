;; AGI Sound Resource 114 (Volume 3 Offset 15263)


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
i11	0	12	0	1008
i11	12	3	0	992
i11	15	12	0	976
i11	27	3	0	960
i11	30	12	0	944
i11	42	3	0	928
i11	45	12	0	912
i11	57	3	0	896
i11	60	12	0	880
i11	72	3	0	864
i11	75	12	0	848
i11	87	3	0	832
i11	90	12	0	816
i11	102	3	0	800
i11	105	12	0	784
i11	117	3	0	768
i11	120	12	0	752
i11	132	3	0	736
i11	135	12	0	720
i11	147	3	0	704
i11	150	12	0	688
i11	162	12	0	672
i11	174	3	0	656
i11	177	12	0	640
i11	189	12	0	624
i11	201	12	0	608
i11	213	12	0	592
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
i99	0	285	0.9	1.0	1.0
