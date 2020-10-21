;; AGI Sound Resource 199 (Volume 3 Offset 360069)


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
i11	3	33	0	976
i11	36	3	0	992
i11	39	33	0	960
i11	72	3	0	992
i11	75	33	0	944
i11	108	3	0	960
i11	111	33	0	928
i11	144	3	0	944
i11	147	33	0	896
i11	180	3	0	912
i11	183	33	0	928
i11	216	3	0	864
i11	219	33	0	880
i11	252	3	0	896
i11	255	33	0	880
i11	288	3	0	832
i11	291	33	0	848
i11	324	3	0	800
i11	327	33	0	832
i11	360	3	0	800
i11	363	33	0	768
i11	396	3	0	784
i11	399	33	0	800
i11	432	3	0	768
i11	435	33	0	736
i11	468	3	0	768
i11	471	33	0	720
i11	504	3	0	672
i11	507	33	0	704
i11	540	3	0	640
i11	543	33	0	656
i11	576	3	0	640
i11	579	33	0	624
i11	612	3	0	560
i11	615	33	0	576
i11	648	3	0	544
i11	651	33	0	560
i11	684	3	0	496
i11	687	33	0	512
i11	720	3	0	528
i11	723	33	0	480
i11	756	3	0	432
i11	759	33	0	448
i11	792	3	0	416
i11	795	3	0	448
i11	798	33	0	384
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
i99	0	891	0.9	1.0	1.0
