; This is '.', sound resource #24 of length 65.099998

; orchestra is expected to have equivalents for
; PCJr/Tandy 3-voice sounds:
; Instrument 11-13 for the main voice (square wave)
; Instrument 21 for white noise
; Instrument 31 for 'linear noise'
; Instrument 99 for mixing/reverb
t 0 360  ; 1/6th second, aligns with AGI timing of 1/60th second

; set up the instruments
i 1  0    0  1  0 7 ;; clavinet
i 1  0    0  2  0 7 ;; clavinet
i 1  0    0  3  11 88 ;; harpsipad

; set up the panning
i 2  0  0  1 0.5
i 2  0  0  2 0.7
i 2  0  0  3 0.3

; Set the reverb for 1 second longer than the song
;   				reverb	gain	gain
; 99	start	dur	depth	Start	End
i 99	0	71.100	0.9	1.0	1.0


; ****  Voice 1
; 11	time	dur	db	freq	pan
i 11	0.000	0.90	-20	804.753845	
i 11	1.800	5.10	-20	804.753845	
i 11	7.200	0.60	-20	804.753845	
i 11	8.100	0.60	-20	804.753845	
i 11	9.000	0.60	-20	717.056274	
i 11	9.900	0.60	-20	717.056274	
i 11	10.800	0.60	-20	639.204468	
i 11	11.700	0.60	-20	639.204468	
i 11	12.600	0.60	-20	717.056274	
i 11	13.500	0.60	-20	717.056274	
i 11	14.400	0.90	-20	804.753845	
i 11	16.200	5.10	-20	804.753845	
i 11	21.600	0.60	-20	804.753845	
i 11	22.500	0.60	-20	804.753845	
i 11	23.400	0.60	-20	717.056274	
i 11	24.300	0.60	-20	717.056274	
i 11	25.200	0.60	-20	639.204468	
i 11	26.100	0.60	-20	639.204468	
i 11	27.000	0.60	-20	717.056274	
i 11	27.900	0.60	-20	717.056274	
i 11	28.800	0.90	-20	804.753845	
i 11	30.600	5.10	-20	804.753845	
i 11	36.000	0.60	-20	804.753845	
i 11	36.900	0.60	-20	804.753845	
i 11	37.800	0.60	-20	717.056274	
i 11	38.700	0.60	-20	717.056274	
i 11	39.600	0.60	-20	639.204468	
i 11	40.500	0.60	-20	639.204468	
i 11	41.400	0.60	-20	717.056274	
i 11	42.300	0.60	-20	717.056274	
i 11	43.200	0.90	-20	804.753845	
i 11	45.000	5.10	-20	804.753845	
i 11	50.400	0.60	-20	804.753845	
i 11	51.300	0.60	-20	804.753845	
i 11	52.200	0.60	-20	717.056274	
i 11	53.100	0.60	-20	717.056274	
i 11	54.000	0.60	-20	639.204468	
i 11	54.900	0.60	-20	639.204468	
i 11	55.800	0.60	-20	804.753845	
i 11	56.700	0.60	-20	964.317078	
i 11	57.600	0.90	-20	1075.584473	
i 11	59.400	5.70	-20	1075.584473	


; ****  Voice 2
; 12	time	dur	db	freq	pan
i 12	0.000	0.90	-20	402.376923	
i 12	1.800	5.10	-20	402.376923	
i 12	7.200	0.60	-20	402.376923	
i 12	8.100	0.60	-20	402.376923	
i 12	9.000	0.60	-20	358.528137	
i 12	9.900	0.60	-20	358.528137	
i 12	10.800	0.60	-20	319.602234	
i 12	11.700	0.60	-20	319.602234	
i 12	12.600	0.60	-20	358.528137	
i 12	13.500	0.60	-20	358.528137	
i 12	14.400	0.90	-20	402.376923	
i 12	16.200	5.10	-20	402.376923	
i 12	21.600	0.60	-20	402.376923	
i 12	22.500	0.60	-20	402.376923	
i 12	23.400	0.60	-20	358.528137	
i 12	24.300	0.60	-20	358.528137	
i 12	25.200	0.60	-20	319.602234	
i 12	26.100	0.60	-20	319.602234	
i 12	27.000	0.60	-20	358.528137	
i 12	27.900	0.60	-20	358.528137	
i 12	28.800	0.90	-20	402.376923	
i 12	30.600	5.10	-20	402.376923	
i 12	36.000	0.60	-20	402.376923	
i 12	36.900	0.60	-20	402.376923	
i 12	37.800	0.60	-20	358.528137	
i 12	38.700	0.60	-20	358.528137	
i 12	39.600	0.60	-20	319.602234	
i 12	40.500	0.60	-20	319.602234	
i 12	41.400	0.60	-20	358.528137	
i 12	42.300	0.60	-20	358.528137	
i 12	43.200	0.90	-20	402.376923	
i 12	45.000	5.10	-20	402.376923	
i 12	50.400	0.60	-20	402.376923	
i 12	51.300	0.60	-20	402.376923	
i 12	52.200	0.60	-20	358.528137	
i 12	53.100	0.60	-20	358.528137	
i 12	54.000	0.60	-20	319.602234	
i 12	54.900	0.60	-20	319.602234	
i 12	55.800	0.60	-20	402.376923	
i 12	56.700	0.60	-20	480.089203	
i 12	57.600	0.90	-20	402.376923	
i 12	59.400	5.70	-20	402.376923	


; ****  Voice 3
; 13		time	dur	db	freq	pan
i 13	0.000	0.90	-20	268.896118	
i 13	1.800	5.10	-20	268.896118	
i 13	7.200	6.90	-20	268.896118	
i 13	14.400	0.90	-20	268.896118	
i 13	16.200	5.10	-20	268.896118	
i 13	21.600	6.90	-20	268.896118	
i 13	28.800	0.90	-20	268.896118	
i 13	30.600	5.10	-20	268.896118	
i 13	36.000	6.90	-20	268.896118	
i 13	43.200	0.90	-20	268.896118	
i 13	45.000	5.10	-20	268.896118	
i 13	50.400	6.90	-20	268.896118	
i 13	57.600	0.90	-20	268.896118	
i 13	59.400	5.70	-20	268.896118	


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
