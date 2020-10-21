;; AGI Sound Resource 21 (Volume 2 Offset 4222)


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
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
i21	0	27	3	64
i21	27	18	1	64
i21	45	18	4	64
i21	63	15	5	64
i21	78	12	6	64
i21	90	9	5	64
i21	99	12	4	64
i21	111	12	5	64
i21	123	9	4	64
i21	132	21	3	64
i21	153	12	4	64
i21	165	15	3	64
i21	180	12	2	64
i21	192	12	1	64
i21	204	9	2	64
i21	213	18	3	64
i21	231	9	2	64
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	300	0.9	1.0	1.0
