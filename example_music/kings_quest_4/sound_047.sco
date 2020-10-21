;; AGI Sound Resource 47 (Volume 2 Offset 552107)


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
i11	9	4	0	339
i11	13	6	0	360
i11	20	4	0	381
i11	24	7	0	403
i11	31	3	0	428
i11	34	5	0	453
i11	39	120	0	480
i11	159	164	0	508
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	8	4	0	679
i12	12	6	0	719
i12	18	7	0	762
i12	25	4	0	807
i12	30	5	0	855
i12	35	4	0	906
i12	39	119	0	960
i12	158	157	0	1017
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	383	0.9	1.0	1.0
