;; AGI Sound Resource 82 (Volume 3 Offset 209361)


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
i11	0	3	0	768
i11	3	3	0	720
i11	6	3	0	688
i11	9	3	0	656
i11	12	3	0	624
i11	15	3	0	592
i11	18	3	0	560
i11	21	3	0	528
i11	24	3	0	496
i11	27	3	0	464
i11	30	3	0	432
i11	33	3	0	416
i11	36	3	0	384
i11	39	3	0	352
i11	42	3	0	336
i11	45	6	0	320
i11	51	6	0	336
i11	57	6	0	352
i11	63	6	0	368
i11	69	6	0	384
i11	75	6	0	400
i11	81	6	0	416
i11	87	6	0	432
i11	93	6	0	464
i11	99	6	0	496
i11	105	6	0	512
i11	111	6	0	544
i11	117	6	0	576
i11	123	6	0	592
i11	129	6	0	608
i11	135	6	0	624
i11	141	6	0	656
i11	147	6	0	688
i11	153	6	0	720
i11	159	6	0	752
i11	165	6	0	784
i11	171	3	0	800
i11	174	6	0	816
i11	180	6	0	832
i11	186	6	0	848
i11	192	6	0	864
i11	198	6	0	880
i11	204	6	0	896
i11	210	6	0	912
i11	216	6	0	928
i11	222	6	0	944
i11	228	6	0	976
i11	234	3	0	1008
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
i99	0	297	0.9	1.0	1.0
