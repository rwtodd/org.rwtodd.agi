;; AGI Sound Resource 44 (Volume 1 Offset 190310)


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
i11	0	6	1	1008
i11	6	3	1	976
i11	9	3	1	944
i11	12	3	1	912
i11	15	3	1	880
i11	18	3	1	848
i11	21	3	1	816
i11	24	3	1	784
i11	27	3	1	752
i11	30	3	1	720
i11	33	3	1	688
i11	36	3	1	656
i11	39	3	1	624
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	6	1	992
i12	6	3	1	960
i12	9	3	1	928
i12	12	3	1	896
i12	15	3	1	864
i12	18	3	1	832
i12	21	3	1	800
i12	24	3	1	768
i12	27	3	1	736
i12	30	3	1	704
i12	33	3	1	672
i12	36	3	1	640
i12	39	3	1	608
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	102	0.9	1.0	1.0
