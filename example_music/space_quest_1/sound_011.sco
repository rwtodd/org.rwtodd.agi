;; AGI Sound Resource 11 (Volume 0 Offset 34077)


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
i11	0	3	0	224
i11	3	3	0	240
i11	6	3	0	256
i11	9	3	0	272
i11	12	3	0	288
i11	15	3	0	304
i11	18	3	0	320
i11	21	3	1	336
i11	24	3	1	352
i11	27	3	1	368
i11	30	3	1	384
i11	33	3	2	400
i11	36	3	2	416
i11	39	3	2	432
i11	42	3	2	448
i11	45	3	3	464
i11	48	3	3	480
i11	51	3	3	496
i11	54	3	3	512
i11	57	3	4	528
i11	60	3	4	544
i11	63	3	4	560
i11	66	3	4	576
i11	69	3	5	592
i11	72	3	5	608
i11	75	3	5	624
i11	78	3	5	640
i11	81	3	6	656
i11	84	3	6	672
i11	87	3	6	688
i11	90	3	6	704
i11	93	3	7	720
i11	96	3	7	736
i11	99	3	7	752
i11	102	3	7	768
i11	105	3	8	784
i11	108	3	8	800
i11	111	3	8	816
i11	114	3	8	832
i11	117	3	9	848
i11	120	3	9	864
i11	123	3	9	880
i11	126	3	9	896
i11	129	3	10	912
i11	132	3	10	928
i11	135	3	10	944
i11	138	3	10	960
i11	141	3	10	976
i11	144	3	10	992
i11	147	3	10	1008
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
i99	0	210	0.9	1.0	1.0
