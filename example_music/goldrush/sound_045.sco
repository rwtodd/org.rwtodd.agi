;; AGI Sound Resource 45 (Volume 2 Offset 555879)


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
i11	0	3	12	928
i11	3	3	13	944
i11	6	3	12	928
i11	9	3	13	944
i11	12	3	12	928
i11	15	3	13	944
i11	18	3	12	928
i11	21	3	13	944
i11	24	3	12	928
i11	27	3	13	944
i11	30	3	12	928
i11	33	3	13	944
i11	36	3	12	928
i11	39	3	13	944
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	6	8	64
i21	6	3	9	64
i21	9	3	10	64
i21	12	3	9	64
i21	15	3	8	64
i21	18	3	7	64
i21	21	3	9	64
i21	24	3	10	64
i21	27	3	9	64
i21	30	6	7	64
i21	36	3	9	64
i21	39	3	10	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	102	0.9	1.0	1.0
