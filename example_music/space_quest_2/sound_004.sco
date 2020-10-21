;; AGI Sound Resource 4 (Volume 1 Offset 383)


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
i11	3	3	0	992
i11	6	3	0	976
i11	9	3	0	944
i11	12	3	0	928
i11	15	3	0	896
i11	18	3	0	880
i11	21	3	0	848
i11	24	3	0	832
i11	27	3	0	800
i11	30	3	0	768
i11	33	3	0	752
i11	36	3	0	720
i11	39	3	0	688
i11	42	3	0	656
i11	45	3	0	608
i11	48	3	0	560
i11	51	3	0	496
i11	54	6	0	464
i11	60	3	1	464
i11	63	3	2	464
i11	66	3	3	464
i11	69	3	4	464
i11	72	3	5	464
i11	75	3	6	464
i11	78	3	7	464
i11	81	3	8	464
i11	84	3	9	464
i11	87	3	10	464
i11	90	3	11	464
i11	93	3	12	464
i11	96	3	13	464
i11	99	3	14	464
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
i99	0	162	0.9	1.0	1.0
