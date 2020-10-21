;; AGI Sound Resource 64 (Volume 2 Offset 95291)


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
i11	0	33	0	960
i11	33	3	0	912
i11	36	33	0	928
i11	69	3	0	864
i11	72	33	0	880
i11	105	3	0	832
i11	108	33	0	848
i11	141	3	0	800
i11	144	33	0	832
i11	177	3	0	800
i11	180	33	0	768
i11	213	3	0	784
i11	216	33	0	800
i11	249	3	0	768
i11	252	33	0	736
i11	285	3	0	768
i11	288	33	0	720
i11	321	3	0	672
i11	324	33	0	704
i11	357	3	0	640
i11	360	33	0	656
i11	393	3	0	640
i11	396	33	0	624
i11	429	3	0	560
i11	432	33	0	576
i11	465	3	0	544
i11	468	33	0	560
i11	501	3	0	496
i11	504	33	0	512
i11	537	3	0	528
i11	540	33	0	480
i11	573	3	0	448
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
i99	0	636	0.9	1.0	1.0
