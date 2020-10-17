; This is '.', sound resource #25 of length 32.400002

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0  0  1   0 40  ; violin
i 1  0  0  2   0 41  ; viola
i 1  0  0  3   0 42  ; cello

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.3
i 2  0  0  4 0


; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	38.400	0.9	1.0	1.0


; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	3.900	5.90	-20	537.792236	
i 11	+	2.00	-20	717.056274	
i 11	+	1.90	-20	677.944153	
i 11	+	1.50	-20	804.753845	
i 11	15.700	3.90	-20	570.718262	
i 11	+	3.30	-20	537.792236	
i 11	23.500	1.80	-20	1075.584473	
i 11	25.500	0.80	-20	1215.878052	
i 11	26.500	0.80	-20	1364.155884	
i 11	27.500	2.10	-20	1434.112549	
i 11	31.400	1.00	-20	717.056274	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	3.900	3.90	-26	358.528137	
i 12	+	7.70	-26	268.896118	
i 12	15.700	7.30	-26	338.972076	
i 12	23.500	2.00	-26	268.896118	
i 12	25.600	0.80	-26	239.530579	
i 12	26.600	0.70	-26	201.188461	
i 12	27.400	2.10	-26	179.264069	
i 12	31.300	0.90	-26	179.264069	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	5.900	2.60	-23	426.949554	
i 13	9.800	2.50	-23	426.949554	
i 13	23.500	2.00	-23	338.972076	
i 13	+	1.00	-23	402.376923	
i 13	+	1.00	-23	508.458099	
i 13	+	1.90	-23	452.877655	
i 13	31.400	1.00	-23	358.528137	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
