;; AGI Sound Resource 36 (Volume 1 Offset 136927)


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
i11	0	13	0	453
i11	26	6	0	360
i11	39	89	0	381
i11	148	10	0	381
i11	174	6	0	302
i11	186	124	0	320
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	16	0	906
i12	25	5	0	719
i12	39	89	0	762
i12	147	11	0	762
i12	172	6	0	605
i12	184	119	0	641
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	78	18	0	906
i13	105	7	0	807
i13	115	6	0	762
i13	134	9	0	906
i13	224	19	0	906
i13	252	8	0	807
i13	261	6	0	762
i13	278	8	0	906
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	370	0.9	1.0	1.0
