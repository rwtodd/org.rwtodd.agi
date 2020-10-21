;; AGI Sound Resource 22 (Volume 2 Offset 549838)


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
i11	105	21	0	85
i11	128	13	0	71
i11	146	14	0	42
i11	162	4	0	48
i11	167	5	0	42
i11	172	40	0	48
i11	214	6	0	53
i11	220	7	0	48
i11	227	38	0	53
i11	283	4	0	85
i11	287	5	0	80
i11	292	4	0	85
i11	305	7	0	71
i11	320	6	0	53
i11	342	43	0	57
i11	388	6	0	53
i11	394	6	0	57
i11	400	6	0	64
i11	411	60	0	57
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	25	102	0	170
i12	339	202	0	160
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	17	111	0	285
i13	332	208	0	254
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	601	0.9	1.0	1.0
