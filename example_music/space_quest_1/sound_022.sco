;; AGI Sound Resource 22 (Volume 1 Offset 222213)


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
i11	0	3	3	1008
i11	3	3	1	1008
i11	6	3	0	992
i11	15	3	0	960
i11	18	3	1	992
i11	21	3	2	992
i11	24	3	2	1008
i11	27	3	4	1008
i11	30	3	6	1008
i11	33	3	8	1008
i11	36	3	10	1008
i11	39	3	12	1008
i11	42	3	4	944
i11	45	3	3	944
i11	48	3	1	912
i11	51	6	0	896
i11	60	3	0	864
i11	63	3	0	816
i11	66	3	0	944
i11	69	3	0	1008
i11	75	3	5	1008
i11	78	3	0	976
i11	84	3	0	976
i11	87	3	0	992
i11	90	3	2	976
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
i99	0	153	0.9	1.0	1.0
