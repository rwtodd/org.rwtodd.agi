;; AGI Sound Resource 18 (Volume 0 Offset 47748)


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
i11	0	3	0	192
i11	3	3	0	208
i11	6	3	0	240
i11	9	3	0	256
i11	12	3	0	272
i11	15	3	0	288
i11	18	3	0	304
i11	21	3	0	320
i11	24	3	0	336
i11	27	3	0	352
i11	30	3	0	368
i11	33	3	0	384
i11	36	3	0	400
i11	39	3	0	416
i11	42	3	0	432
i11	45	3	0	448
i11	48	3	0	464
i11	51	9	0	480
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	3	3	224
i12	3	3	3	256
i12	6	3	3	288
i12	9	3	3	320
i12	12	3	3	352
i12	15	3	3	368
i12	18	3	3	384
i12	21	3	3	400
i12	24	3	3	416
i12	27	3	3	432
i12	30	3	3	448
i12	33	3	3	464
i12	36	6	3	480
i12	42	18	3	496
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	3	14	48
i13	3	3	14	64
i13	6	3	14	80
i13	9	3	14	96
i13	12	3	14	112
i13	15	3	14	128
i13	18	3	14	144
i13	21	3	14	160
i13	24	3	14	176
i13	27	6	14	208
i13	33	3	14	224
i13	36	3	14	240
i13	39	3	14	272
i13	42	3	14	256
i13	45	6	14	288
i13	51	9	11	304
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	51	14	16
i21	51	9	4	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	120	0.9	1.0	1.0
