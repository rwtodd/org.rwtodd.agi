;; AGI Sound Resource 25 (Volume 0 Offset 23244)


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
i11	0	23	0	143
i11	23	11	0	135
i11	34	22	0	180
i11	56	37	0	143
i11	98	37	0	143
i11	150	22	0	143
i11	172	10	0	135
i11	184	24	0	143
i11	209	72	0	107
i11	299	8	0	85
i11	312	6	0	90
i11	320	5	0	85
i11	338	9	0	107
i11	350	6	0	95
i11	373	9	0	107
i11	386	7	0	113
i11	393	10	0	120
i11	404	28	0	127
i11	449	9	0	85
i11	461	6	0	90
i11	468	7	0	85
i11	487	7	0	107
i11	498	6	0	95
i11	518	7	0	107
i11	561	7	0	53
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	49	2	428
i12	75	56	2	428
i12	150	57	2	320
i12	225	58	2	428
i12	300	24	2	381
i12	338	19	2	571
i12	375	7	2	428
i12	388	7	2	453
i12	395	6	2	480
i12	406	33	2	508
i12	450	6	2	381
i12	469	7	2	381
i12	488	9	2	571
i12	506	9	2	571
i12	525	7	2	428
i12	562	8	2	428
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	48	4	285
i13	75	55	4	285
i13	150	47	4	214
i13	225	60	4	285
i13	300	13	4	151
i13	318	14	4	151
i13	337	9	4	160
i13	356	11	4	160
i13	376	12	4	170
i13	388	6	4	180
i13	394	12	4	190
i13	406	30	4	202
i13	450	10	4	151
i13	468	13	4	151
i13	487	11	4	160
i13	506	9	4	160
i13	525	10	4	170
i13	562	8	4	170
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	630	0.9	1.0	1.0
