;; AGI Sound Resource 4 (Volume 1 Offset 149371)


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
i11	0	3	4	288
i11	3	3	1	752
i11	6	6	5	192
i11	30	9	14	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	2	304
i12	3	3	4	240
i12	6	6	7	416
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	0	320
i13	3	3	2	160
i13	6	6	4	432
i13	12	15	14	1008
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	12	0	16
i21	12	3	1	16
i21	15	3	2	16
i21	18	3	4	32
i21	21	3	6	32
i21	24	3	8	16
i21	27	3	9	32
i21	30	3	8	32
i21	33	3	10	16
i21	36	3	12	32
i21	39	3	14	16
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	102	0.9	1.0	1.0
