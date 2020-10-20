;; AGI Sound Resource 31 (Volume 3 Offset 154107)


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
i11	0	21	0	69
i11	32	18	0	52
i11	57	8	0	52
i11	65	11	0	61
i11	82	10	0	78
i11	98	33	0	92
i11	131	11	0	69
i11	147	11	0	82
i11	163	11	0	104
i11	180	11	0	82
i11	196	59	0	92
i11	262	22	0	69
i11	295	13	0	52
i11	318	8	0	55
i11	327	11	0	61
i11	344	11	0	78
i11	360	11	0	92
i11	377	11	0	61
i11	393	11	0	69
i11	409	11	0	52
i11	442	11	0	55
i11	459	50	0	52
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
i99	0	569	0.9	1.0	1.0
