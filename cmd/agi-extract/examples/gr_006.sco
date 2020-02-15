; This is '.', sound resource #6 of length 10.900000

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
i 99	0	16.900	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	0.900	2.70	-20	370.399933	0.50
i 1	+	7.30	-20	246.933289	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	0.900	2.60	-20	349.564941	0.70
i 1	+	7.30	-20	233.043289	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
