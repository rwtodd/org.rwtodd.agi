; This is '.', sound resource #9 of length 59.200001

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 1 for the main voice (square wave)
; Instrument 2 for white noise
; Instrument 3 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second


; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	65.200	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	2.200	1.20	-20	522.713928	0.50
i 1	3.900	1.20	-20	588.740967	0.50
i 1	5.500	2.90	-20	621.448792	0.50
i 1	8.800	3.00	-20	782.243225	0.50
i 1	12.200	4.60	-20	782.243225	0.50
i 1	17.200	1.20	-20	828.598389	0.50
i 1	18.800	3.00	-20	782.243225	0.50
i 1	22.200	2.90	-20	621.448792	0.50
i 1	25.500	4.60	-20	522.713928	0.50
i 1	30.500	1.30	-20	588.740967	0.50
i 1	32.200	2.90	-20	621.448792	0.50
i 1	35.500	2.90	-20	621.448792	0.50
i 1	38.800	2.90	-20	588.740967	0.50
i 1	42.200	3.30	-20	588.740967	0.50
i 1	+	11.70	-20	522.713928	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	5.600	5.00	-20	392.493958	0.70
i 1	12.200	5.00	-20	415.839325	0.70
i 1	18.900	5.00	-20	392.493958	0.70
i 1	25.600	5.00	-20	392.493958	0.70
i 1	32.200	5.00	-20	392.493958	0.70
i 1	38.900	5.00	-20	349.564941	0.70
i 1	45.500	11.70	-20	310.724396	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	5.600	5.00	-20	261.356964	0.30
i 1	12.300	5.00	-20	261.356964	0.30
i 1	18.900	5.00	-20	261.356964	0.30
i 1	25.600	5.00	-20	310.724396	0.30
i 1	32.300	4.90	-20	261.356964	0.30
i 1	38.900	5.00	-20	246.933289	0.30
i 1	45.600	11.60	-20	261.356964	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
