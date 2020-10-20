;; AGI Sound Resource 27 (Volume 0 Offset 22872)


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
i11	27	83	0	208
i11	111	23	0	220
i11	137	21	0	262
i11	161	22	0	278
i11	186	151	0	294
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	241	1	2	208
i12	248	3	2	208
i12	298	1	2	208
i12	305	2	2	208
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	239	3	1	220
i13	248	3	1	220
i13	297	2	1	220
i13	305	1	1	220
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	397	0.9	1.0	1.0
