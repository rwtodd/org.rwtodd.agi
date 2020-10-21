;; AGI Sound Resource 39 (Volume 2 Offset 551824)


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
i11	6	3	0	339
i11	9	4	0	320
i11	13	17	0	339
i11	30	14	0	320
i11	46	15	0	269
i11	61	15	0	254
i11	76	15	0	226
i11	91	4	0	254
i11	95	4	0	226
i11	99	8	0	254
i11	107	15	0	269
i11	122	19	0	320
i11	141	84	0	339
i11	225	3	0	381
i11	228	4	0	339
i11	232	23	0	381
i11	256	60	0	403
i11	318	39	0	403
i11	359	199	0	480
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
i99	0	618	0.9	1.0	1.0
