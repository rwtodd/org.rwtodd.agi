;; AGI Sound Resource 26 (Volume 0 Offset 22706)


t 0 3600 ;; AGI runs in 1/60th second ticks

; set up the instruments
i 1  0  0  1   0 6  ; harpsichord
i 1  0  0  2   0 6  ; harpsichord
i 1  0  0  3   0 6  ; harpsichord

; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	1	8	0	208
i11	9	9	0	185
i11	18	8	0	175
i11	26	9	0	156
i11	35	8	0	139
i11	43	9	0	131
i11	52	26	0	139
i11	78	26	0	175
i11	104	78	0	208
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	9	2	416
i12	9	8	2	371
i12	17	9	2	350
i12	26	8	2	312
i12	34	9	2	278
i12	43	9	2	262
i12	52	25	2	278
i12	77	26	2	350
i12	103	78	2	416
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	9	1	833
i13	9	8	1	742
i13	18	8	1	700
i13	26	9	1	624
i13	35	8	1	556
i13	43	9	1	525
i13	52	26	1	556
i13	78	25	1	700
i13	103	79	1	833
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	242	0.9	1.0	1.0
