; This is '.', sound resource #26 of length 18.200001

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
i 99	0	24.200	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	0.100	0.80	-20	537.792236	0.50
i 1	+	0.90	-20	604.652893	0.50
i 1	+	0.80	-20	639.204468	0.50
i 1	+	0.90	-20	717.056274	0.50
i 1	+	0.80	-20	804.753845	0.50
i 1	+	0.90	-20	853.899109	0.50
i 1	+	2.60	-20	804.753845	0.50
i 1	+	2.60	-20	639.204468	0.50
i 1	+	7.80	-20	537.792236	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	0.000	0.90	-26	268.896118	0.70
i 1	+	0.80	-26	301.511536	0.70
i 1	+	0.90	-26	319.602234	0.70
i 1	+	0.80	-26	358.528137	0.70
i 1	+	0.90	-26	402.376923	0.70
i 1	+	0.90	-26	426.949554	0.70
i 1	+	2.50	-26	402.376923	0.70
i 1	+	2.60	-26	319.602234	0.70
i 1	+	7.80	-26	268.896118	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	0.000	0.90	-23	134.286652	0.30
i 1	+	0.80	-23	150.755768	0.30
i 1	1.800	0.80	-23	159.801117	0.30
i 1	+	0.90	-23	179.264069	0.30
i 1	+	0.80	-23	201.188461	0.30
i 1	+	0.90	-23	213.068161	0.30
i 1	+	2.60	-23	201.188461	0.30
i 1	+	2.50	-23	159.801117	0.30
i 1	+	7.90	-23	134.286652	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
