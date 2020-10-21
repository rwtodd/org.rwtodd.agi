;; AGI Sound Resource 13 (Volume 0 Offset 8927)


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
i11	0	3	0	256
i11	3	3	0	272
i11	6	3	0	256
i11	9	3	0	272
i11	12	3	0	256
i11	15	6	1	272
i11	21	6	1	256
i11	27	3	2	272
i11	30	3	2	256
i11	33	9	3	272
i11	42	9	4	272
i11	51	6	5	272
i11	57	3	6	272
i11	60	3	7	272
i11	63	3	8	272
i11	66	3	9	272
i11	69	3	10	272
i11	72	3	11	272
i11	75	3	12	272
i11	78	3	13	272
i11	81	3	14	272
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
i99	0	144	0.9	1.0	1.0
