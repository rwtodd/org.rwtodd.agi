;; AGI Sound Resource 50 (Volume 2 Offset 196935)


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
i11	1	14	0	254
i11	15	14	0	339
i11	29	13	0	254
i11	42	14	0	339
i11	56	14	0	254
i11	70	13	0	339
i11	83	14	0	254
i11	97	14	0	339
i11	111	47	0	127
i11	165	14	0	127
i11	179	13	0	107
i11	192	14	0	101
i11	206	10	0	95
i11	220	47	0	95
i11	274	14	0	95
i11	288	13	0	101
i11	301	14	0	107
i11	315	14	0	127
i11	329	14	0	254
i11	343	13	0	339
i11	356	14	0	254
i11	370	14	0	339
i11	384	13	0	254
i11	397	14	0	339
i11	411	14	0	254
i11	425	10	0	339
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	2	95	0	508
i12	110	103	0	508
i12	220	47	0	508
i12	274	48	0	381
i12	329	102	0	508
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	110	13	0	254
i13	123	14	0	339
i13	137	14	0	254
i13	151	13	0	339
i13	164	14	0	254
i13	178	14	0	339
i13	192	13	0	254
i13	205	14	0	339
i13	219	14	0	254
i13	233	13	0	339
i13	246	14	0	254
i13	260	13	0	339
i13	273	14	0	254
i13	287	14	0	339
i13	301	13	0	254
i13	314	13	0	339
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	495	0.9	1.0	1.0
