; This is '.', sound resource #10 of length 54.299999

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0    0  1  0 48 ;; strings (0 46 ;; harp also works)
i 1  0    0  2  0 48 ;; strings (0 46 ;; harp also works)
i 1  0    0  3  0 48 ;; strings (0 46 ;; harp also works)

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.3

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	60.300	0.9	1.0	1.0


; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	1.20	-20	1016.916199	
i 11	+	1.20	-20	1075.584473	
i 11	+	1.20	-20	1016.916199	
i 11	+	3.60	-20	804.753845	
i 11	+	3.60	-20	677.944153	
i 11	+	1.20	-20	909.437256	
i 11	+	1.20	-20	964.317078	
i 11	+	1.20	-20	909.437256	
i 11	+	3.60	-20	717.056274	
i 11	+	3.60	-20	604.652893	
i 11	+	1.20	-20	804.753845	
i 11	+	1.20	-20	853.899109	
i 11	+	1.20	-20	804.753845	
i 11	+	3.60	-20	639.204468	
i 11	+	3.60	-20	537.792236	
i 11	+	1.80	-20	604.652893	
i 11	+	1.80	-20	677.944153	
i 11	+	3.60	-20	717.056274	
i 11	+	3.60	-20	604.652893	
i 11	+	11.10	-20	677.944153	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	3.60	-20	677.944153	
i 12	+	3.60	-20	508.458099	
i 12	+	3.60	-20	402.376923	
i 12	+	3.60	-20	604.652893	
i 12	+	3.60	-20	452.877655	
i 12	+	3.60	-20	358.528137	
i 12	+	3.60	-20	537.792236	
i 12	+	3.60	-20	402.376923	
i 12	+	3.60	-20	319.602234	
i 12	+	7.20	-20	537.792236	
i 12	+	3.60	-20	508.458099	
i 12	+	11.10	-20	508.458099	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	0.000	10.80	-20	268.896118	
i 13	+	10.80	-20	239.530579	
i 13	+	10.80	-20	213.068161	
i 13	+	3.60	-20	358.528137	
i 13	+	3.60	-20	402.376923	
i 13	+	3.60	-20	201.188461	
i 13	+	11.10	-20	268.896118	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
