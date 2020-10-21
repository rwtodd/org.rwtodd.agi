;; AGI Sound Resource 105 (Volume 2 Offset 187269)


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
i11	0	6	0	944
i11	6	6	0	912
i11	12	6	0	928
i11	18	3	0	624
i11	21	6	0	928
i11	27	6	0	912
i11	33	6	0	928
i11	39	3	0	640
i11	42	3	0	928
i11	45	6	0	912
i11	51	6	0	928
i11	57	3	0	640
i11	60	6	0	928
i11	66	6	0	912
i11	72	6	0	928
i11	78	3	0	640
i11	81	6	0	928
i11	87	6	0	912
i11	93	6	0	928
i11	99	3	0	624
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
