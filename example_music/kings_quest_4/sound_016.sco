;; AGI Sound Resource 16 (Volume 1 Offset 376234)


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
i11	7	13	0	160
i11	20	15	0	160
i11	50	5	0	170
i11	57	14	0	190
i11	85	14	0	190
i11	106	10	0	170
i11	134	5	0	170
i11	150	9	0	190
i11	168	4	0	302
i11	176	4	0	302
i11	184	4	0	302
i11	193	5	0	302
i11	204	3	0	302
i11	276	31	0	160
i11	307	7	0	170
i11	314	17	0	190
i11	334	22	0	190
i11	356	24	0	170
i11	381	25	0	160
i11	411	4	0	170
i11	416	2	0	160
i11	418	33	0	170
i11	452	9	0	190
i11	461	15	0	214
i11	487	43	0	214
i11	572	32	0	160
i11	609	8	0	170
i11	617	29	0	190
i11	647	24	0	190
i11	671	27	0	170
i11	698	17	0	160
i11	717	3	0	170
i11	720	4	0	160
i11	724	38	0	170
i11	782	11	0	214
i11	813	60	0	254
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	7	61	0	302
i12	127	11	0	302
i12	269	119	0	381
i12	405	123	0	508
i12	566	53	0	381
i12	723	61	2	508
i12	808	61	2	381
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	275	116	0	254
i13	409	77	0	339
i13	486	39	0	285
i13	567	60	0	254
i13	726	24	2	254
i13	774	5	2	285
i13	808	9	2	339
i13	817	12	2	320
i13	829	43	2	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	933	0.9	1.0	1.0
