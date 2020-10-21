;; AGI Sound Resource 146 (Volume 3 Offset 164813)


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
i11	4	12	0	143
i11	19	8	0	117
i11	64	7	0	143
i11	79	6	0	571
i11	94	10	0	135
i11	169	13	0	143
i11	184	6	0	571
i11	199	7	0	135
i11	214	6	0	539
i11	274	10	0	143
i11	289	7	0	117
i11	319	6	0	135
i11	326	6	0	180
i11	334	10	0	135
i11	379	7	0	571
i11	394	10	0	143
i11	409	5	0	571
i11	424	14	0	135
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	4	9	0	202
i12	63	8	0	202
i12	93	8	0	190
i12	168	10	0	202
i12	198	8	0	190
i12	273	7	0	202
i12	318	7	0	539
i12	326	5	0	719
i12	333	10	0	202
i12	393	7	0	202
i12	423	11	0	190
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	3	10	0	214
i13	63	7	0	214
i13	93	9	0	202
i13	168	10	0	214
i13	198	7	0	202
i13	273	7	0	214
i13	333	9	0	214
i13	393	6	0	214
i13	423	12	0	202
i13	477	1	0	0
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	538	0.9	1.0	1.0
