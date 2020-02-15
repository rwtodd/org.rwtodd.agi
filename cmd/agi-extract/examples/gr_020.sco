; This is '.', sound resource #20 of length 37.299999

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
i 99	0	27.300	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	1.000	6.00	-20	261.356964	0.50
i 1	+	6.00	-20	310.724396	0.50
i 1	+	12.00	-20	370.399933	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	1.000	6.00	-20	207.533920	0.70
i 1	7.100	5.90	-20	246.933289	0.70
i 1	+	12.00	-20	293.597839	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	1.000	6.00	-20	164.743423	0.30
i 1	+	6.00	-20	195.903290	0.30
i 1	+	12.00	-20	233.043289	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
