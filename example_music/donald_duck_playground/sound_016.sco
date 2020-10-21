;; AGI Sound Resource 16 (Volume 0 Offset 165453)


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
i11	0	3	9	1008
i11	3	3	9	992
i11	6	3	9	976
i11	9	3	9	960
i11	12	3	9	944
i11	15	3	9	928
i11	18	3	9	912
i11	21	3	9	896
i11	24	3	8	880
i11	27	3	8	864
i11	30	3	8	848
i11	33	3	8	832
i11	36	3	8	816
i11	39	3	8	800
i11	42	3	8	784
i11	45	3	7	768
i11	48	3	7	752
i11	51	3	7	736
i11	54	3	7	720
i11	57	3	7	704
i11	60	3	7	688
i11	63	3	6	672
i11	66	3	6	656
i11	69	3	6	640
i11	72	3	6	624
i11	75	3	6	608
i11	78	3	6	592
i11	81	3	5	576
i11	84	3	5	560
i11	87	3	5	544
i11	90	3	5	528
i11	93	3	5	512
i11	96	3	5	496
i11	99	3	5	480
i11	102	3	4	464
i11	105	3	4	448
i11	108	3	4	432
i11	111	3	4	416
i11	114	3	4	400
i11	117	3	4	384
i11	120	3	3	368
i11	123	3	3	352
i11	126	3	3	336
i11	129	3	3	320
i11	132	3	3	304
i11	135	3	3	288
i11	138	3	3	272
i11	141	3	2	256
i11	144	3	2	240
i11	147	3	2	224
i11	150	3	2	208
i11	153	3	3	192
i11	156	3	4	176
i11	159	3	5	160
i11	162	3	6	144
i11	165	3	7	128
i11	168	3	8	112
i11	171	3	9	96
i11	174	3	10	80
i11	177	3	11	64
i11	180	3	12	48
i11	183	3	13	32
i11	186	3	14	16
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	24	14	64
i21	24	21	13	64
i21	45	18	12	64
i21	63	18	11	64
i21	81	21	10	64
i21	102	18	9	64
i21	120	27	8	64
i21	147	12	9	64
i21	159	9	10	64
i21	168	9	11	64
i21	177	9	12	64
i21	186	3	13	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	249	0.9	1.0	1.0
