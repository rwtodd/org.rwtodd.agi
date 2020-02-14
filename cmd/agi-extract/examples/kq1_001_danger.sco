; This is '.', sound resource #1 of length 17.400000

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
i 99	0	23.400	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	0.000	1.20	-20	604.652893	0.50
i 1	+	1.20	-20	677.944153	0.50
i 1	+	1.20	-20	717.056274	0.50
i 1	+	1.20	-20	677.944153	0.50
i 1	+	1.20	-20	604.652893	0.50
i 1	7.200	10.20	-20	853.899109	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	0.000	1.20	-20	301.511536	0.70
i 1	+	1.20	-20	338.972076	0.70
i 1	+	1.20	-20	358.528137	0.70
i 1	+	1.20	-20	338.972076	0.70
i 1	+	1.20	-20	301.511536	0.70
i 1	7.200	10.20	-20	508.458099	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	0.000	1.20	-20	150.755768	0.30
i 1	+	1.20	-20	169.229630	0.30
i 1	+	1.20	-20	179.264069	0.30
i 1	+	1.20	-20	169.229630	0.30
i 1	+	1.20	-20	150.755768	0.30
i 1	7.200	10.20	-20	301.511536	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
