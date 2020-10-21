;; AGI Sound Resource 113 (Volume 1 Offset 143837)


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
i11	3	8	0	80
i11	25	8	0	160
i11	46	8	0	95
i11	68	8	0	151
i11	89	8	0	71
i11	111	8	0	143
i11	132	8	0	85
i11	153	8	0	143
i11	175	8	0	80
i11	196	8	0	160
i11	218	8	0	95
i11	239	8	0	151
i11	261	8	0	71
i11	282	8	0	143
i11	303	8	0	85
i11	325	8	0	143
i11	346	9	0	80
i11	368	8	0	160
i11	389	8	0	95
i11	410	9	0	80
i11	432	8	0	95
i11	453	32	0	95
i11	496	16	0	80
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	25	9	0	254
i12	68	8	0	214
i12	111	8	0	226
i12	154	8	0	240
i12	197	8	0	254
i12	240	8	0	214
i12	283	8	0	226
i12	325	8	0	240
i12	347	8	0	214
i12	390	8	0	214
i12	432	8	0	214
i12	475	8	0	214
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	347	8	0	160
i13	390	8	0	160
i13	433	8	0	160
i13	476	8	0	160
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	572	0.9	1.0	1.0
