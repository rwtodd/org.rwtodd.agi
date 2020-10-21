;; AGI Sound Resource 19 (Volume 1 Offset 221896)


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
i11	3	3	2	992
i11	6	3	0	976
i11	9	3	2	960
i11	12	3	0	960
i11	15	3	2	976
i11	18	3	0	944
i11	21	3	3	976
i11	24	3	2	944
i11	27	3	5	960
i11	30	3	3	976
i11	33	3	6	992
i11	36	3	4	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	0	992
i12	3	3	2	944
i12	6	3	0	912
i12	9	3	2	896
i12	12	3	0	912
i12	15	3	2	896
i12	18	3	1	912
i12	21	3	4	896
i12	24	3	3	928
i12	27	3	5	928
i12	30	3	4	976
i12	33	3	5	1008
i12	36	3	4	1008
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	99	0.9	1.0	1.0
