;; AGI Sound Resource 31 (Volume 3 Offset 154107)


t 0 3600 ;; AGI runs in 1/60th second ticks


; set up the instruments
i 1  0  0  1   0 72  ; piccolo

; set up the panning
i 2  0  0  1 0.5

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	869	0.9	1.0	1.0
i 99    +   159 0.9 1.0 0.001

;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	0	21	2	69
i11	32	18	3	52
i11	57	8	2	52
i11	65	11	0	61
i11	82	10	0	78
i11	98	33	0	92
i11	131	11	0	69
i11	147	11	0	82
i11	163	11	0	104
i11	180	11	0	82
i11	196	59	1	92
i11	262	22	0	69
i11	295	13	0	52
i11	318	8	0	55
i11	327	11	0	61
i11	344	11	0	78
i11	360	11	0	92
i11	377	11	0	61
i11	393	11	0	69
i11	409	11	0	52
i11	442	11	2	55
i11	459	50	3	52
;; End of instrument 11

b 519


;; Start of voice 1 (instrument 11)
;;	start	dur	level	freq
i11	0	21	2	69
i11	32	18	3	52
i11	57	8	2	52
i11	65	11	0	61
i11	82	10	0	78
i11	98	33	0	92
i11	131	11	0	69
i11	147	11	0	82
i11	163	11	0	104
i11	180	11	0	82
i11	196	59	2	92
i11	262	22	0	69
i11	295	13	0	52
i11	318	8	0	55
i11	327	11	0	61
i11	344	11	0	78
i11	360	11	0	92
i11	377	11	0	61
i11	393	11	0	69
i11	409	11	0	52
i11	442	11	0	55
i11	459	50	0	52
;; End of instrument 11
