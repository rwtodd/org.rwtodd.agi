; This is '.', sound resource #15 of length 13.500000

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 1 for the main voice (square wave)
; Instrument 2 for white noise
; Instrument 3 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second


; Set the reverb for 2 seconds longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	19.500	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	0.000	0.60	-20	179.264069	0.50
i 1	0.900	0.60	-20	268.896118	0.50
i 1	1.800	0.30	-20	179.264069	0.50
i 1	2.400	0.30	-20	179.264069	0.50
i 1	3.000	0.90	-20	268.896118	0.50
i 1	4.200	0.30	-20	179.264069	0.50
i 1	4.800	0.30	-20	179.264069	0.50
i 1	5.400	3.60	-20	406.766479	0.50
i 1	+	1.50	-26	406.766479	0.50
i 1	+	0.90	-32	406.766479	0.50
i 1	+	1.80	-47	406.766479	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	0.000	9.00	-26	134.448059	0.70
i 1	+	4.20	-26	403.829529	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	0.000	9.00	-32	268.896118	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
