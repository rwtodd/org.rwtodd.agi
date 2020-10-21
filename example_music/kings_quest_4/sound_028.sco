;; AGI Sound Resource 28 (Volume 1 Offset 377730)


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
i11	8	8	0	807
i11	20	3	0	762
i11	56	4	0	807
i11	63	6	0	762
i11	103	4	0	807
i11	112	7	0	762
i11	123	8	0	641
i11	135	8	0	762
i11	148	12	0	807
i11	253	8	0	807
i11	261	7	0	762
i11	302	8	0	807
i11	311	6	0	762
i11	347	8	0	807
i11	357	7	0	762
i11	372	7	0	641
i11	382	8	0	762
i11	395	10	0	807
i11	497	9	0	641
i11	506	6	0	719
i11	518	5	0	807
i11	529	7	0	855
i11	547	30	0	960
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	55	54	0	67
i12	199	25	0	71
i12	224	24	0	90
i12	248	52	0	80
i12	301	129	0	67
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	8	13	0	641
i13	57	11	0	641
i13	104	8	0	641
i13	149	14	0	539
i13	253	12	0	641
i13	302	12	0	641
i13	347	11	0	641
i13	396	12	0	539
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	637	0.9	1.0	1.0
