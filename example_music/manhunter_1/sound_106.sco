;; AGI Sound Resource 106 (Volume 1 Offset 142528)


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
i11	0	29	0	143
i11	29	29	0	113
i11	58	29	0	95
i11	87	29	0	143
i11	116	29	0	151
i11	145	29	0	113
i11	174	29	0	95
i11	203	29	0	190
i11	232	29	0	170
i11	261	29	0	127
i11	290	29	0	107
i11	319	29	0	170
i11	348	29	0	151
i11	377	29	0	127
i11	406	29	0	95
i11	435	26	0	151
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	0	43	0	285
i12	87	14	0	285
i12	116	43	0	302
i12	203	14	0	381
i12	232	43	0	339
i12	319	14	0	285
i12	348	43	0	302
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	15	0	571
i13	29	15	0	571
i13	58	15	0	571
i13	87	15	0	571
i13	116	15	0	453
i13	145	15	0	453
i13	174	15	0	453
i13	203	15	0	453
i13	232	15	0	508
i13	261	15	0	508
i13	290	15	0	508
i13	319	15	0	508
i13	348	15	0	381
i13	377	15	0	381
i13	407	14	0	381
i13	436	14	0	381
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	521	0.9	1.0	1.0
