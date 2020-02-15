; This is '.', sound resource #3 of length 47.599998

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
i 99	0	53.600	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	2.200	0.60	-20	494.959198	0.50
i 1	4.100	0.70	-20	658.004578	0.50
i 1	+	9.90	-20	621.448792	0.50
i 1	15.400	7.30	-20	658.004578	0.50
i 1	23.400	0.60	-20	494.959198	0.50
i 1	25.300	0.70	-20	658.004578	0.50
i 1	+	9.90	-20	621.448792	0.50
i 1	36.600	5.30	-20	494.959198	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	4.900	1.90	-20	329.972809	0.70
i 1	7.500	0.70	-20	415.839325	0.70
i 1	9.500	0.70	-20	494.959198	0.70
i 1	+	2.60	-20	466.086578	0.70
i 1	+	2.60	-20	440.396790	0.70
i 1	15.500	1.90	-20	329.972809	0.70
i 1	18.100	0.60	-20	415.839325	0.70
i 1	20.100	0.60	-20	494.959198	0.70
i 1	+	2.70	-20	466.086578	0.70
i 1	+	2.60	-20	440.396790	0.70
i 1	+	2.00	-20	329.972809	0.70
i 1	28.700	0.60	-20	415.839325	0.70
i 1	30.700	0.60	-20	494.959198	0.70
i 1	+	2.70	-20	466.086578	0.70
i 1	+	2.60	-20	440.396790	0.70
i 1	+	5.30	-20	329.972809	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
