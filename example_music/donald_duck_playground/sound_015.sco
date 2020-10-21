;; AGI Sound Resource 15 (Volume 0 Offset 165112)


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
i11	0	3	4	192
i11	3	3	4	208
i11	6	3	4	224
i11	9	3	4	240
i11	12	3	4	256
i11	15	3	5	272
i11	18	3	5	288
i11	21	3	5	304
i11	24	3	5	320
i11	27	3	5	336
i11	30	3	5	352
i11	33	3	6	368
i11	36	3	6	384
i11	39	3	6	400
i11	42	3	6	416
i11	45	3	6	432
i11	48	3	6	448
i11	51	3	6	464
i11	54	3	6	480
i11	57	3	6	496
i11	60	3	6	512
i11	63	3	6	528
i11	66	3	7	544
i11	69	3	7	560
i11	72	3	7	576
i11	75	3	7	592
i11	78	3	7	608
i11	81	3	7	624
i11	84	3	7	640
i11	87	3	7	656
i11	90	3	7	672
i11	93	3	7	688
i11	96	3	7	704
i11	99	3	7	720
i11	102	3	7	736
i11	105	3	8	752
i11	108	3	8	768
i11	111	3	8	784
i11	114	3	8	800
i11	117	3	8	816
i11	120	3	8	832
i11	123	3	8	848
i11	126	3	9	864
i11	129	3	9	880
i11	132	3	9	896
i11	135	3	9	912
i11	138	6	5	928
i11	144	21	12	944
i11	165	24	12	960
i11	189	21	12	976
i11	210	27	12	992
i11	237	36	12	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	15	4	32
i21	15	18	5	32
i21	33	33	6	32
i21	66	39	7	32
i21	105	21	8	32
i21	126	12	9	32
i21	138	6	11	32
i21	144	243	12	32
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	447	0.9	1.0	1.0
