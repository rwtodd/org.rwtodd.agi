;; AGI Sound Resource 54 (Volume 1 Offset 377966)


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
i11	49	38	0	160
i11	87	11	0	170
i11	98	19	0	190
i11	121	28	0	190
i11	149	25	0	170
i11	176	21	0	160
i11	198	4	0	170
i11	203	4	0	160
i11	207	19	0	170
i11	228	25	0	214
i11	253	29	0	254
i11	282	30	0	214
i11	312	41	0	170
i11	400	32	0	160
i11	432	7	0	170
i11	439	24	0	190
i11	468	24	0	190
i11	492	28	0	170
i11	521	29	0	160
i11	555	23	0	143
i11	580	3	0	170
i11	583	3	0	143
i11	586	28	0	170
i11	615	16	0	214
i11	670	102	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	40	1	2	320
i12	41	60	2	254
i12	202	150	2	339
i12	386	7	2	320
i12	393	66	2	254
i12	467	62	2	254
i12	547	4	2	339
i12	552	22	2	254
i12	581	32	2	285
i12	614	14	2	339
i12	661	23	2	339
i12	684	17	2	320
i12	701	71	2	339
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	34	5	2	508
i13	42	129	2	381
i13	199	149	2	508
i13	385	70	2	381
i13	463	58	2	381
i13	543	90	2	508
i13	661	107	2	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	832	0.9	1.0	1.0
