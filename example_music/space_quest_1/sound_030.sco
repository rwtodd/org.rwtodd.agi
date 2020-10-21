;; AGI Sound Resource 30 (Volume 1 Offset 222409)


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
i11	0	3	0	96
i11	3	3	10	96
i11	6	3	0	96
i11	9	3	10	96
i11	12	3	0	96
i11	15	3	11	96
i11	18	3	1	96
i11	21	3	11	96
i11	24	3	2	96
i11	27	3	11	96
i11	30	3	3	96
i11	33	3	11	96
i11	36	3	4	96
i11	39	3	11	96
i11	42	3	5	96
i11	45	3	11	96
i11	48	3	6	96
i11	51	3	11	96
i11	54	3	7	96
i11	57	3	11	96
i11	60	3	8	96
i11	63	3	11	96
i11	66	3	9	96
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
i99	0	129	0.9	1.0	1.0
