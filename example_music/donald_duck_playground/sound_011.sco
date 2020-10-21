;; AGI Sound Resource 11 (Volume 0 Offset 147712)


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
i11	0	45	0	896
i11	45	3	3	896
i11	51	42	0	896
i11	93	3	3	896
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	45	0	912
i12	45	3	3	912
i12	51	42	0	912
i12	93	3	3	912
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	45	0	928
i13	45	3	3	928
i13	51	42	0	928
i13	93	3	3	928
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	45	0	32
i21	45	3	3	32
i21	51	42	0	32
i21	93	3	3	32
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	156	0.9	1.0	1.0
