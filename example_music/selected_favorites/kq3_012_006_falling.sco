; This is '.', sound resource #12 of length 5.400000

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0  0  1   0 86
i 1  0  0  2   0 86

; set up the panning
i 2  0  0  1 0.4
i 2  0  0  2 0.6
i 2  0  0  4 1

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99    0   5.5     0.5 1.0 1.0
i 99	+	14.5	0.9	1.0	1.0

; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	0.30	-20	112.762886	
i 11	+	0.30	-20	116.521645	
i 11	+	0.30	-20	120.539635	
i 11	+	0.30	-20	124.844620	
i 11	+	1.20	-20	131.911301	
i 11	+	0.30	-20	129.468491	
i 11	+	0.30	-20	127.114525	
i 11	+	0.60	-20	124.844620	
i 11	+	0.30	-20	120.539635	
i 11	+	0.60	-20	116.521645	
i 11	+	0.30	-20	112.762886	
i 11	+	0.30	-20	110.973000	

; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.30	-20	116.521645	
i 12	+	0.30	-20	120.539635	
i 12	+	0.30	-20	124.844620	
i 12	+	0.30	-20	129.468491	
i 12	+	0.30	-20	134.448059	
i 12	+	1.20	-20	137.084290	
i 12	+	0.30	-20	134.448059	
i 12	+	0.60	-20	129.468491	
i 12	+	0.30	-20	124.844620	
i 12	+	0.60	-20	120.539635	
i 12	+	0.30	-20	116.521645	
i 12	+	0.30	-20	114.611458	


b 6.4
i 1  0  0  1   0 7
i 1  0  0  2   0 7
i 1  0  0  3   0 7

i 2 0 0 1 0.5
i 2 0 0 2 0.7
i 2 0 0 3 0.3

; This is '.', sound resource #6 of length 3.900

; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	0.90	-23	1747.824707	
i 11	1.200	0.60	-23	1398.259766	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.30	-29	388.405487	
i 12	+	0.60	-29	436.956177	
i 12	1.200	0.30	-29	258.936981	
i 12	+	0.30	-29	349.564941	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	0.300	0.30	-26	466.086578	
i 13	+	0.30	-26	436.956177	
i 13	1.200	0.30	-26	367.963104	
i 13	+	0.30	-26	349.564941	

b 10.3

; This is '.', sound resource #6 of length 3.900

; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	0.90	-23	1747.824707	
i 11	1.200	0.60	-23	1398.259766	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.30	-29	388.405487	
i 12	+	0.60	-29	436.956177	
i 12	1.200	0.30	-29	258.936981	
i 12	+	0.30	-29	349.564941	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	0.300	0.30	-26	466.086578	
i 13	+	0.30	-26	436.956177	
i 13	1.200	0.30	-26	367.963104	
i 13	+	0.30	-26	349.564941	

b 14.2

; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	0.90	-23	1747.824707	
i 11	1.200	0.60	-23	1398.259766	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.30	-29	388.405487	
i 12	+	0.60	-29	436.956177	
i 12	1.200	0.30	-29	258.936981	
i 12	+	0.30	-29	349.564941	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	0.300	0.30	-26	466.086578	
i 13	+	0.30	-26	436.956177	
i 13	1.200	0.30	-26	367.963104	
i 13	+	0.30	-26	349.564941	

