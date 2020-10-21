;; AGI Sound Resource 171 (Volume 3 Offset 87162)


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
i11	0	6	0	656
i11	6	3	0	640
i11	24	3	0	656
i11	27	3	0	640
i11	45	3	0	656
i11	48	3	0	640
i11	66	3	0	656
i11	69	3	0	640
i11	87	3	0	656
i11	90	3	0	640
i11	108	3	0	656
i11	111	3	0	640
i11	129	3	0	656
i11	132	3	0	640
i11	150	3	0	656
i11	153	3	0	640
i11	156	3	0	656
i11	174	3	0	656
i11	177	3	0	640
i11	195	3	0	656
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
i99	0	258	0.9	1.0	1.0
