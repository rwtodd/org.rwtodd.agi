;; AGI Sound Resource 8 (Volume 3 Offset 150974)


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
i11	0	3	4	1008
i11	3	3	3	1008
i11	6	3	2	1008
i11	9	3	1	1008
i11	12	51	0	1008
i11	63	3	1	1008
i11	66	3	2	1008
i11	69	3	3	1008
i11	72	3	4	1008
i11	75	3	5	1008
i11	78	3	6	1008
i11	81	3	7	1008
i11	84	3	8	1008
i11	87	3	9	1008
i11	90	3	10	1008
i11	93	3	11	1008
i11	96	3	12	1008
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	4	992
i12	3	3	3	992
i12	6	3	2	992
i12	9	3	1	992
i12	12	45	0	992
i12	57	3	1	992
i12	60	3	2	992
i12	63	3	3	992
i12	66	3	4	992
i12	69	3	5	992
i12	72	3	6	992
i12	75	3	7	992
i12	78	3	8	992
i12	81	3	9	992
i12	84	3	10	992
i12	87	3	11	992
i12	90	3	12	992
i12	93	3	13	992
i12	96	3	14	992
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	159	0.9	1.0	1.0
