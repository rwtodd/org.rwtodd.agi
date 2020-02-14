; This is '.', sound resource #25 of length 32.400002

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
i 99	0	38.400	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	3.900	5.90	-20	537.792236	0.50
i 1	+	2.00	-20	717.056274	0.50
i 1	+	1.90	-20	677.944153	0.50
i 1	+	1.50	-20	804.753845	0.50
i 1	15.700	3.90	-20	570.718262	0.50
i 1	+	3.30	-20	537.792236	0.50
i 1	23.500	1.80	-20	1075.584473	0.50
i 1	25.500	0.80	-20	1215.878052	0.50
i 1	26.500	0.80	-20	1364.155884	0.50
i 1	27.500	2.10	-20	1434.112549	0.50
i 1	31.400	1.00	-20	717.056274	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	3.900	3.90	-26	358.528137	0.70
i 1	+	7.70	-26	268.896118	0.70
i 1	15.700	7.30	-26	338.972076	0.70
i 1	23.500	2.00	-26	268.896118	0.70
i 1	25.600	0.80	-26	239.530579	0.70
i 1	26.600	0.70	-26	201.188461	0.70
i 1	27.400	2.10	-26	179.264069	0.70
i 1	31.300	0.90	-26	179.264069	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	5.900	2.60	-23	426.949554	0.30
i 1	9.800	2.50	-23	426.949554	0.30
i 1	23.500	2.00	-23	338.972076	0.30
i 1	+	1.00	-23	402.376923	0.30
i 1	+	1.00	-23	508.458099	0.30
i 1	+	1.90	-23	452.877655	0.30
i 1	31.400	1.00	-23	358.528137	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
