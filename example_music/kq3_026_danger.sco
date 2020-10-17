; This is '.', sound resource #26 of length 18.200001

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0  0  1   0 6  ; harpsichord
i 1  0  0  2   0 6  ; harpsichord
i 1  0  0  3   0 6  ; harpsichord

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.3
i 2  0  0  4 0

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	24.200	0.9	1.0	1.0


; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.100	0.80	-20	537.792236	
i 11	+	0.90	-20	604.652893	
i 11	+	0.80	-20	639.204468	
i 11	+	0.90	-20	717.056274	
i 11	+	0.80	-20	804.753845	
i 11	+	0.90	-20	853.899109	
i 11	+	2.60	-20	804.753845	
i 11	+	2.60	-20	639.204468	
i 11	+	7.80	-20	537.792236	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.90	-26	268.896118	
i 12	+	0.80	-26	301.511536	
i 12	+	0.90	-26	319.602234	
i 12	+	0.80	-26	358.528137	
i 12	+	0.90	-26	402.376923	
i 12	+	0.90	-26	426.949554	
i 12	+	2.50	-26	402.376923	
i 12	+	2.60	-26	319.602234	
i 12	+	7.80	-26	268.896118	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	0.000	0.90	-23	134.286652	
i 13	+	0.80	-23	150.755768	
i 13	1.800	0.80	-23	159.801117	
i 13	+	0.90	-23	179.264069	
i 13	+	0.80	-23	201.188461	
i 13	+	0.90	-23	213.068161	
i 13	+	2.60	-23	201.188461	
i 13	+	2.50	-23	159.801117	
i 13	+	7.90	-23	134.286652	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
