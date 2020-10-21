;; AGI Sound Resource 112 (Volume 1 Offset 93656)


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
i11	78	167	0	320
i11	266	17	0	320
i11	290	14	0	214
i11	311	137	0	214
i11	448	6	0	160
i11	459	7	0	107
i11	472	65	0	107
i11	555	8	0	160
i11	565	6	0	107
i11	581	148	0	107
i11	732	49	0	320
i11	796	7	0	320
i11	808	5	0	214
i11	821	80	0	214
i11	910	9	0	320
i11	921	6	0	214
i11	935	297	0	214
i11	1232	7	0	160
i11	1241	8	0	107
i11	1257	82	0	107
i11	1342	17	0	641
i11	1359	11	0	428
i11	1379	102	0	428
i11	1576	3	0	135
i11	1584	5	0	160
i11	1595	76	0	90
i11	1677	3	0	135
i11	1684	4	0	160
i11	1693	117	0	90
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	284	135	0	320
i12	440	172	0	160
i12	709	166	0	641
i12	1031	7	0	160
i12	1043	6	0	107
i12	1058	115	0	107
i12	1178	4	0	135
i12	1186	5	0	160
i12	1196	151	0	90
i12	1348	168	0	107
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	76	133	0	641
i13	225	106	0	641
i13	344	77	0	269
i13	435	193	0	403
i13	710	81	0	641
i13	791	51	0	214
i13	945	120	0	807
i13	1217	61	0	641
i13	1278	71	0	320
i13	1349	67	0	214
i13	1445	250	0	807
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	1870	0.9	1.0	1.0
