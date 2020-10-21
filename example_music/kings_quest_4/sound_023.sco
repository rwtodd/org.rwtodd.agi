;; AGI Sound Resource 23 (Volume 2 Offset 550021)


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
i11	14	26	0	339
i11	40	27	0	320
i11	67	103	0	339
i11	171	19	0	360
i11	190	30	0	381
i11	222	29	0	480
i11	251	26	0	428
i11	277	94	0	480
i11	371	18	0	508
i11	389	46	0	539
i11	435	30	0	339
i11	467	25	0	320
i11	492	105	0	339
i11	597	24	0	360
i11	621	26	0	381
i11	651	24	0	480
i11	675	29	0	428
i11	704	134	0	480
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	213	76	1	42
i12	521	59	1	85
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	8	206	1	170
i13	214	218	1	190
i13	432	215	1	170
i13	647	191	1	190
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	898	0.9	1.0	1.0
