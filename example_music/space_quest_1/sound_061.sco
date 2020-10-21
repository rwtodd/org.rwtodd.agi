;; AGI Sound Resource 61 (Volume 1 Offset 222616)


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
i11	0	30	0	330
i11	40	5	0	330
i11	50	5	0	330
i11	60	8	0	247
i11	70	8	0	330
i11	80	8	0	247
i11	90	60	0	165
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	30	0	110
i12	45	5	0	110
i12	55	2	0	110
i12	60	5	0	104
i12	70	5	0	104
i12	80	2	0	104
i12	90	60	0	110
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	30	0	165
i13	60	10	0	165
i13	80	10	0	165
i13	90	60	0	55
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	210	0.9	1.0	1.0
