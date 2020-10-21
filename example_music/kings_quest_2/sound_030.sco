;; AGI Sound Resource 30 (Volume 0 Offset 46550)


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
i11	0	3	0	544
i11	3	3	0	528
i11	6	6	2	528
i11	12	6	0	528
i11	18	6	3	528
i11	24	6	2	528
i11	30	6	4	528
i11	36	6	3	528
i11	42	6	5	528
i11	48	6	4	528
i11	54	6	6	528
i11	60	6	5	528
i11	66	6	7	528
i11	72	6	6	528
i11	78	6	8	528
i11	84	6	7	528
i11	90	6	9	528
i11	96	6	8	528
i11	102	6	10	528
i11	108	3	9	528
i11	111	3	11	528
i11	114	3	10	528
i11	117	3	12	528
i11	120	3	11	528
i11	123	3	13	528
i11	126	3	14	528
i11	129	3	13	528
i11	132	3	14	528
i11	135	3	13	528
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
i99	0	198	0.9	1.0	1.0
