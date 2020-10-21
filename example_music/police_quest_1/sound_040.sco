;; AGI Sound Resource 40 (Volume 0 Offset 24849)


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
i11	0	23	0	480
i11	23	22	0	508
i11	45	23	0	480
i11	68	22	0	508
i11	90	23	0	480
i11	113	22	0	508
i11	135	23	0	480
i11	158	22	0	508
i11	180	23	0	539
i11	203	22	0	571
i11	225	23	0	539
i11	248	22	0	571
i11	270	23	0	539
i11	293	22	0	571
i11	315	23	0	539
i11	338	22	0	508
;; End of instrument 11

;; Start of voice 2 (instrument 12)
;;	start	dur	level	freq
i12	1	22	0	605
i12	23	22	0	641
i12	45	23	0	605
i12	68	22	0	641
i12	90	23	0	605
i12	113	22	0	641
i12	135	23	0	605
i12	158	22	0	641
i12	180	23	0	679
i12	203	22	0	719
i12	225	23	0	679
i12	248	22	0	719
i12	270	23	0	679
i12	293	22	0	719
i12	315	23	0	679
i12	338	22	0	641
;; End of instrument 12

;; Start of voice 3 (instrument 13)
;;	start	dur	level	freq
i13	0	23	0	762
i13	23	22	0	807
i13	45	23	0	762
i13	68	22	0	807
i13	90	23	0	762
i13	113	22	0	807
i13	135	23	0	762
i13	158	22	0	807
i13	180	23	0	855
i13	203	22	0	906
i13	225	23	0	855
i13	248	22	0	906
i13	270	23	0	855
i13	293	22	0	906
i13	315	23	0	855
i13	338	22	0	807
;; End of instrument 13

;; Start of noise channel (instrument 21 an 31)
;;	start	dur	level	freq
;; End of noise channel

;; mixer
;;	start	dur	rev	lvl1	lvl2
i99	0	420	0.9	1.0	1.0
