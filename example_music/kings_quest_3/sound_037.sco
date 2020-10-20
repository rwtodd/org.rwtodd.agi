;; AGI Sound Resource 37 (Volume 2 Offset 144585)


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
i11	0	25	0	58
i11	25	26	0	55
i11	51	26	0	58
i11	77	26	0	61
i11	103	25	0	73
i11	128	26	0	61
i11	154	26	0	58
i11	180	26	0	55
i11	206	25	0	58
i11	232	25	0	61
i11	257	26	0	73
i11	283	26	0	61
i11	309	26	0	58
i11	335	25	0	55
i11	360	26	0	52
i11	386	26	0	55
i11	412	26	0	46
i11	438	25	0	55
i11	463	26	0	58
i11	489	26	0	55
i11	515	26	0	52
i11	541	25	0	55
i11	566	26	0	78
i11	592	26	0	61
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	154	2	165
i12	154	155	2	175
i12	309	155	2	185
i12	464	128	2	196
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	154	1	185
i13	154	155	1	196
i13	309	155	1	208
i13	464	154	1	220
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	678	0.9	1.0	1.0
