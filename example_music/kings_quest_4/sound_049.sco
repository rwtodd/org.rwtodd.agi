;; AGI Sound Resource 49 (Volume 0 Offset 65111)


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
i11	17	80	0	539
i11	97	22	0	571
i11	119	39	0	641
i11	158	78	0	428
i11	236	27	0	480
i11	263	37	0	539
i11	301	75	0	571
i11	376	24	0	641
i11	402	37	0	571
i11	439	144	0	719
i11	585	81	0	641
i11	666	26	0	571
i11	693	53	0	539
i11	746	114	0	320
i11	863	24	0	285
i11	888	5	0	320
i11	893	5	0	285
i11	898	61	0	320
i11	959	58	0	339
i11	1017	68	0	381
i11	1085	153	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	10	250	2	428
i12	297	220	2	480
i12	579	280	2	539
i12	895	326	2	571
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	5	259	2	641
i13	292	230	2	719
i13	568	292	2	807
i13	893	322	2	855
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1298	0.9	1.0	1.0
