;; AGI Sound Resource 93 (Volume 5 Offset 65812)


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
i11	0	6	0	1008
i11	6	6	0	992
i11	12	6	0	976
i11	18	6	0	960
i11	24	6	0	944
i11	30	6	0	960
i11	36	6	0	944
i11	42	6	0	928
i11	48	6	0	912
i11	54	6	0	896
i11	60	6	0	912
i11	66	6	0	896
i11	72	6	0	880
i11	78	6	0	864
i11	84	6	0	848
i11	90	6	0	832
i11	96	6	0	848
i11	102	6	0	832
i11	108	6	0	816
i11	114	6	0	800
i11	120	6	0	784
i11	126	6	0	768
i11	132	6	0	752
i11	138	6	0	768
i11	144	6	0	736
i11	150	6	0	720
i11	156	6	0	704
i11	162	3	0	688
i11	165	3	0	672
i11	168	3	0	656
i11	171	3	0	672
i11	174	3	0	640
i11	177	3	0	624
i11	180	3	0	640
i11	183	3	0	656
i11	186	3	0	640
i11	189	3	0	624
i11	192	3	0	640
i11	195	3	0	656
i11	198	3	0	640
i11	201	3	0	624
i11	204	3	0	608
i11	207	3	0	624
i11	210	3	0	640
i11	213	3	0	624
i11	216	3	0	608
i11	219	3	0	592
i11	222	6	0	528
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
i99	0	288	0.9	1.0	1.0
