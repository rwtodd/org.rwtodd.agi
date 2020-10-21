; This is '.', sound resource #35 of length 78.199997

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb

t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments

i 1 0 0 1 0 98  ;; crystal
i 1  0  0  2   0 54 ;; synth vox
i 1  0  0  3   0 89  ;; warm pad

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.3
i 2  0  0  4 0

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	106.00	0.9	1.0	1.0
i 99	+	46.200	0.9	1.0	0.01

a 0 1 5
; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	6.000	6.10	-20	2033.832397	
i 11	+	6.00	-20	1928.634155	
i 11	+	6.00	-20	1364.155884	
i 11	+	11.70	-20	1434.112549	
i 11	36.100	6.00	-20	1833.783325	
i 11	+	3.00	-20	1720.935059
i 11	+	3.00	-20	1621.170776	
i 11	+	3.00	-20	1075.584473	
i 11	+	3.00	-20	1141.436523	
i 11	+	3.00	-20	1621.170776	
i 11	+	3.00	-20	1532.339478	
i 11	60.200	18.00	-20	1215.878052	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	6.000	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	45.100	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	6.000	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	39.100	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	


b 72.1

; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	6.000	6.10	-20	2033.832397	
i 11	+	6.00	-20	1928.634155	
i 11	+	6.00	-20	1364.155884	
i 11	+	11.70	-20	1434.112549	
i 11	36.100	6.00	-20	1833.783325	
i 11	+	3.00	-20	1720.935059	
i 11	+	3.00	-20	1621.170776	
i 11	+	3.00	-20	1075.584473	
i 11	+	3.00	-20	1141.436523	
i 11	+	3.00	-20	1621.170776	
i 11	+	3.00	-20	1532.339478	
i 11	60.200	18.00	-20	1215.878052	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	6.000	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	45.100	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	
i 12	+	3.00	-26	508.458099	
i 12	+	3.00	-26	537.792236	


; ****  Voice 3
; 13	time	dur	db	freq	pan
i 13	6.000	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	39.100	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	
i 13	+	3.00	-23	639.204468	
i 13	+	3.00	-23	677.944153	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq