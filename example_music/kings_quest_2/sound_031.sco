;; AGI Sound Resource 31 (Volume 0 Offset 46741)


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
i11	0	3	8	1008
i11	3	6	7	1008
i11	9	3	10	992
i11	12	3	5	992
i11	15	3	1	912
i11	18	3	0	912
i11	21	3	1	912
i11	24	3	0	912
i11	27	3	1	912
i11	30	3	0	912
i11	33	3	1	912
i11	36	6	0	912
i11	42	3	3	912
i11	45	3	6	928
i11	48	3	7	928
i11	51	3	11	944
i11	54	3	10	944
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	6	992
i12	3	3	5	992
i12	6	3	4	992
i12	9	6	3	976
i12	15	30	0	960
i12	45	6	2	960
i12	51	3	4	960
i12	54	6	7	960
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	6	4	64
i21	6	3	5	64
i21	9	6	4	64
i21	15	3	5	32
i21	18	27	6	16
i21	45	3	5	64
i21	48	3	7	64
i21	51	6	14	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	120	0.9	1.0	1.0
