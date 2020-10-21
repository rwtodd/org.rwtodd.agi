;; AGI Sound Resource 19 (Volume 0 Offset 23624)


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
i11	0	3	0	1008
i11	6	3	0	720
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	2	32
i21	3	6	0	32
i21	9	3	2	32
i21	12	3	4	32
i21	15	3	6	32
i21	18	3	8	32
i21	21	3	10	32
i21	24	3	12	32
i21	27	3	14	32
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	90	0.9	1.0	1.0
