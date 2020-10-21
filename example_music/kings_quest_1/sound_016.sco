;; AGI Sound Resource 16 (Volume 0 Offset 47271)


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
i11	0	3	0	896
i11	6	3	0	896
i11	12	3	0	784
i11	18	3	0	736
i11	24	3	0	800
i11	39	3	0	896
i11	45	3	0	896
i11	51	3	0	896
i11	57	3	0	896
i11	63	3	0	896
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	8	912
i12	6	3	8	912
i12	12	3	8	800
i12	18	3	8	720
i12	24	3	8	816
i12	39	3	8	912
i12	45	3	8	912
i12	51	3	8	912
i12	57	3	8	912
i12	63	3	8	912
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	3	14	64
i21	6	3	14	64
i21	12	3	14	64
i21	18	3	13	32
i21	24	3	13	32
i21	39	3	14	64
i21	45	3	14	64
i21	51	3	14	64
i21	57	3	14	64
i21	63	3	14	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	126	0.9	1.0	1.0
