; This is '.', sound resource #32 of length 22.200001

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0  0  1   0 10; music box
i 1  0  0  2   0 10; music box
i 1  0  0  3   0 11; vibraphone

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.2
i 2  0  0  4 0

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	28.200	0.9	1.0	1.0


; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	4.500	2.60	-20	537.792236	
i 11	7.500	1.30	-20	804.753845	
i 11	9.000	2.80	-20	604.652893	
i 11	12.000	1.30	-20	804.753845	
i 11	13.500	3.00	-20	677.944153	
i 11	+	1.50	-20	604.652893	
i 11	+	4.10	-20	537.792236	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	4.600	1.50	-23	537.792236	
i 12	+	1.50	-23	508.458099	
i 12	+	1.50	-23	452.877655	
i 12	+	1.50	-23	402.376923	
i 12	+	1.50	-23	452.877655	
i 12	+	1.50	-23	508.458099	
i 12	+	2.00	-23	537.792236	
i 12	16.600	0.90	-23	402.376923	
i 12	18.100	4.10	-23	338.972076	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	4.500	2.60	-24	537.792236	
i 13	7.500	1.30	-24	804.753845	
i 13	9.000	2.80	-24	604.652893	
i 13	12.000	1.30	-24	804.753845	
i 13	13.500	3.00	-24	677.944153	
i 13	+	1.50	-24	604.652893	
i 13	+	4.10	-24	537.792236	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
