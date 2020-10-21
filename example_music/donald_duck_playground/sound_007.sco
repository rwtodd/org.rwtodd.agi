;; AGI Sound Resource 7 (Volume 0 Offset 122999)


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
i11	0	12	0	944
i11	12	3	3	1008
i11	51	9	0	944
i11	60	3	3	1008
i11	96	6	0	944
i11	102	3	3	1008
i11	135	3	0	944
i11	138	3	3	1008
i11	168	3	0	944
i11	171	3	3	1008
i11	198	3	0	944
i11	201	3	3	1008
i11	225	3	0	944
i11	228	3	3	1008
i11	249	3	0	944
i11	252	3	3	1008
i11	270	3	0	944
i11	273	3	3	1008
i11	288	3	0	944
i11	291	3	3	1008
i11	303	3	0	944
i11	306	3	3	1008
i11	315	3	0	944
i11	318	3	3	1008
i11	327	3	0	944
i11	330	3	3	1008
i11	339	3	0	944
i11	342	3	3	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	12	0	928
i13	12	3	3	992
i13	51	9	0	928
i13	60	3	3	992
i13	96	6	0	928
i13	102	3	3	992
i13	135	3	0	928
i13	138	3	3	992
i13	168	3	0	928
i13	171	3	3	992
i13	198	3	0	928
i13	201	3	3	992
i13	225	3	0	928
i13	228	3	3	992
i13	249	3	0	928
i13	252	3	3	992
i13	270	3	0	928
i13	273	3	3	992
i13	288	3	0	928
i13	291	3	3	992
i13	303	3	0	928
i13	306	3	3	992
i13	315	3	0	928
i13	318	3	3	992
i13	327	3	0	928
i13	330	3	3	992
i13	339	3	0	928
i13	342	3	3	992
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	12	0	32
i21	12	3	3	64
i21	51	9	0	32
i21	60	3	3	64
i21	96	6	0	32
i21	102	3	3	64
i21	135	3	0	32
i21	138	3	3	64
i21	168	3	0	32
i21	171	3	3	64
i21	198	3	0	32
i21	201	3	3	64
i21	225	3	0	32
i21	228	3	3	64
i21	249	3	0	32
i21	252	3	3	64
i21	270	3	0	32
i21	273	3	3	64
i21	288	3	0	32
i21	291	3	3	64
i21	303	3	0	32
i21	306	3	3	64
i21	315	3	0	32
i21	318	3	3	64
i21	327	3	0	32
i21	330	3	3	64
i21	339	3	0	32
i21	342	3	3	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	405	0.9	1.0	1.0
