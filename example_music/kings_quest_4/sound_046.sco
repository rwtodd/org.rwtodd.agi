;; AGI Sound Resource 46 (Volume 3 Offset 163092)


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
i11	15	69	0	571
i11	84	51	0	381
i11	135	38	0	285
i11	173	39	0	143
i11	212	51	0	107
i11	266	157	0	80
i11	425	10	0	95
i11	435	7	0	80
i11	442	8	0	95
i11	451	8	0	80
i11	460	7	0	95
i11	470	8	0	80
i11	480	35	0	95
i11	554	6	0	80
i11	560	9	0	95
i11	571	41	0	60
i11	612	8	0	64
i11	621	11	0	71
i11	637	93	0	71
i11	730	22	0	143
i11	761	23	0	127
i11	785	35	0	120
i11	820	120	0	101
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	84	51	2	571
i12	135	38	2	381
i12	173	39	2	190
i12	212	51	2	143
i12	266	75	2	107
i12	341	453	2	571
i12	796	144	2	539
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	135	38	2	571
i13	173	39	2	285
i13	212	52	2	190
i13	266	75	2	143
i13	341	344	2	381
i13	805	135	2	135
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1000	0.9	1.0	1.0
