; This is '.', sound resource #32 of length 22.200001

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
i 99	0	28.200	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	4.500	2.60	-20	537.792236	0.50
i 1	7.500	1.30	-20	804.753845	0.50
i 1	9.000	2.80	-20	604.652893	0.50
i 1	12.000	1.30	-20	804.753845	0.50
i 1	13.500	3.00	-20	677.944153	0.50
i 1	+	1.50	-20	604.652893	0.50
i 1	+	4.10	-20	537.792236	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	4.600	1.50	-26	537.792236	0.70
i 1	+	1.50	-26	508.458099	0.70
i 1	+	1.50	-26	452.877655	0.70
i 1	+	1.50	-26	402.376923	0.70
i 1	+	1.50	-26	452.877655	0.70
i 1	+	1.50	-26	508.458099	0.70
i 1	+	2.00	-26	537.792236	0.70
i 1	16.600	0.90	-26	402.376923	0.70
i 1	18.100	4.10	-26	338.972076	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	4.500	2.60	-23	537.792236	0.30
i 1	7.500	1.30	-23	804.753845	0.30
i 1	9.000	2.80	-23	604.652893	0.30
i 1	12.000	1.30	-23	804.753845	0.30
i 1	13.500	3.00	-23	677.944153	0.30
i 1	+	1.50	-23	604.652893	0.30
i 1	+	4.10	-23	537.792236	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
