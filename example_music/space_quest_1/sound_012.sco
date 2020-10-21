;; AGI Sound Resource 12 (Volume 0 Offset 34363)


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
i11	0	3	0	816
i11	3	3	2	816
i11	6	3	0	816
i11	9	3	2	816
i11	12	6	0	816
i11	18	6	3	816
i11	24	6	1	816
i11	30	9	4	816
i11	39	6	2	816
i11	45	6	5	816
i11	51	3	6	816
i11	54	3	7	816
i11	57	3	8	816
i11	60	3	9	816
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
i99	0	123	0.9	1.0	1.0
