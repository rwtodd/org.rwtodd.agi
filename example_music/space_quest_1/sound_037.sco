;; AGI Sound Resource 37 (Volume 2 Offset 257994)


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
i11	0	3	0	320
i11	3	3	0	352
i11	6	3	0	384
i11	9	3	0	416
i11	12	3	0	448
i11	15	3	0	480
i11	18	3	0	512
i11	21	3	0	544
i11	24	3	0	576
i11	27	3	0	608
i11	30	3	0	640
i11	33	3	0	608
i11	36	3	0	576
i11	39	3	0	544
i11	42	3	0	512
i11	45	3	0	480
i11	48	3	0	448
i11	51	3	0	416
i11	54	3	0	384
i11	57	3	0	352
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	400
i12	3	3	0	432
i12	6	3	0	464
i12	9	3	0	496
i12	12	3	0	528
i12	15	3	0	560
i12	18	3	0	592
i12	21	3	0	624
i12	24	3	0	656
i12	27	3	0	672
i12	30	3	0	704
i12	33	3	0	672
i12	36	3	0	640
i12	39	3	0	608
i12	42	3	0	576
i12	45	3	0	544
i12	48	3	0	512
i12	51	3	0	480
i12	54	3	0	448
i12	57	3	0	416
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	80
i13	30	3	0	160
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	120	0.9	1.0	1.0
