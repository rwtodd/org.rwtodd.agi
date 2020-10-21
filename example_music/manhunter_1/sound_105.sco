;; AGI Sound Resource 105 (Volume 1 Offset 142336)


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
i11	3	5	0	127
i11	13	4	0	95
i11	22	5	0	85
i11	31	19	0	71
i11	50	33	0	85
i11	88	4	0	127
i11	97	5	0	95
i11	106	5	0	85
i11	116	39	0	71
i11	155	3	0	127
i11	163	5	0	95
i11	172	5	0	85
i11	181	19	0	71
i11	200	38	0	85
i11	238	19	0	64
i11	257	4	0	71
i11	266	5	0	85
i11	275	29	0	95
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	4	14	0	508
i12	88	14	0	508
i12	155	13	0	508
i12	238	14	0	381
i12	257	7	0	428
i12	266	7	0	381
i12	276	7	0	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	3	14	0	679
i13	88	14	0	679
i13	154	13	0	679
i13	238	14	0	508
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	364	0.9	1.0	1.0
