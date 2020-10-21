;; AGI Sound Resource 68 (Volume 3 Offset 163329)


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
i11	6	52	0	214
i11	59	45	0	160
i11	105	53	0	120
i11	160	24	0	127
i11	185	19	0	143
i11	207	24	0	143
i11	231	31	0	127
i11	264	178	0	214
i11	443	50	0	240
i11	493	53	0	180
i11	546	60	0	135
i11	606	29	0	143
i11	636	32	0	180
i11	673	31	0	180
i11	704	30	0	160
i11	736	167	0	120
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	293	4	0	64
i12	297	3	0	60
i12	303	1	0	64
i12	304	7	0	60
i12	315	15	0	64
i12	330	7	0	53
i12	373	4	0	64
i12	377	4	0	60
i12	381	3	0	64
i12	384	4	0	60
i12	388	7	0	64
i12	415	12	0	48
i12	530	4	0	60
i12	536	4	0	53
i12	540	2	0	60
i12	542	7	0	53
i12	559	19	0	60
i12	578	19	0	45
i12	644	3	0	60
i12	647	3	0	53
i12	651	5	0	60
i12	656	3	0	53
i12	659	12	0	60
i12	687	9	0	45
i12	718	86	0	40
i12	830	9	0	45
i12	839	7	0	48
i12	850	11	0	53
i12	861	13	0	60
i12	874	9	0	67
i12	884	14	0	71
i12	901	30	0	80
i12	931	37	0	71
i12	972	89	0	120
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1121	0.9	1.0	1.0
