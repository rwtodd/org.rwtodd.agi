; This is '.', sound resource #45 of length 80.800003

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
i 99	0	86.800	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	0.400	1.40	-20	261.356964	0.50
i 1	2.900	1.30	-20	392.493958	0.50
i 1	+	1.20	-20	522.713928	0.50
i 1	+	1.30	-20	658.004578	0.50
i 1	+	1.20	-20	522.713928	0.50
i 1	+	1.30	-20	392.493958	0.50
i 1	+	1.40	-20	261.356964	0.50
i 1	11.700	1.20	-20	392.493958	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	1.20	-20	658.004578	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	2.50	-20	440.396790	0.50
i 1	+	2.50	-20	349.564941	0.50
i 1	+	1.20	-20	440.396790	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	1.40	-20	699.129883	0.50
i 1	26.700	1.20	-20	522.713928	0.50
i 1	+	1.30	-20	440.396790	0.50
i 1	+	1.30	-20	349.564941	0.50
i 1	+	1.20	-20	392.493958	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	1.20	-20	658.004578	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	1.20	-20	392.493958	0.50
i 1	+	1.40	-20	261.356964	0.50
i 1	39.200	1.40	-20	392.493958	0.50
i 1	41.700	1.30	-20	658.004578	0.50
i 1	+	1.20	-20	522.713928	0.50
i 1	+	1.30	-20	392.493958	0.50
i 1	+	1.30	-20	261.356964	0.50
i 1	48.000	1.20	-20	392.493958	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	+	1.20	-20	588.740967	0.50
i 1	+	1.20	-20	494.959198	0.50
i 1	+	1.30	-20	392.493958	0.50
i 1	+	1.40	-20	782.243225	0.50
i 1	56.700	1.20	-20	989.918396	0.50
i 1	+	1.30	-20	1177.481934	0.50
i 1	+	1.30	-20	1575.503906	0.50
i 1	+	1.20	-20	1398.259766	0.50
i 1	+	1.30	-20	1045.427856	0.50
i 1	+	1.30	-20	880.793579	0.50
i 1	65.400	1.30	-20	880.793579	0.50
i 1	+	1.30	-20	1045.427856	0.50
i 1	+	1.20	-20	1398.259766	0.50
i 1	+	1.30	-20	1045.427856	0.50
i 1	+	1.30	-20	522.713928	0.50
i 1	73.000	1.20	-20	782.243225	0.50
i 1	+	1.30	-20	1045.427856	0.50
i 1	+	1.20	-20	1316.009155	0.50
i 1	+	1.30	-20	1045.427856	0.50
i 1	+	1.20	-20	782.243225	0.50
i 1	+	1.20	-20	522.713928	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	0.500	1.30	-20	130.831329	0.70
i 1	3.000	1.30	-20	130.831329	0.70
i 1	5.500	1.30	-20	130.831329	0.70
i 1	8.000	1.30	-20	130.831329	0.70
i 1	10.500	1.30	-20	164.743423	0.70
i 1	13.000	1.30	-20	164.743423	0.70
i 1	15.500	1.30	-20	164.743423	0.70
i 1	18.000	1.30	-20	164.743423	0.70
i 1	20.500	1.30	-20	174.509796	0.70
i 1	23.000	1.30	-20	174.509796	0.70
i 1	25.500	1.30	-20	174.509796	0.70
i 1	28.000	1.30	-20	174.509796	0.70
i 1	30.500	1.30	-20	130.831329	0.70
i 1	33.000	1.40	-20	130.831329	0.70
i 1	35.500	1.40	-20	130.831329	0.70
i 1	38.000	1.40	-20	130.831329	0.70
i 1	40.500	1.40	-20	130.831329	0.70
i 1	43.000	1.40	-20	130.831329	0.70
i 1	45.500	1.40	-20	130.831329	0.70
i 1	48.000	1.40	-20	130.831329	0.70
i 1	50.500	0.60	-20	195.903290	0.70
i 1	51.700	0.60	-20	195.903290	0.70
i 1	53.000	0.60	-20	195.903290	0.70
i 1	54.200	0.60	-20	195.903290	0.70
i 1	55.500	0.60	-20	195.903290	0.70
i 1	56.700	0.60	-20	195.903290	0.70
i 1	58.000	0.60	-20	195.903290	0.70
i 1	59.200	0.60	-20	195.903290	0.70
i 1	60.500	0.60	-20	174.509796	0.70
i 1	61.700	0.60	-20	174.509796	0.70
i 1	63.000	0.60	-20	174.509796	0.70
i 1	64.200	0.60	-20	174.509796	0.70
i 1	65.500	0.60	-20	174.509796	0.70
i 1	66.700	0.60	-20	174.509796	0.70
i 1	68.000	0.60	-20	174.509796	0.70
i 1	69.200	0.70	-20	174.509796	0.70
i 1	70.500	0.60	-20	130.831329	0.70
i 1	71.700	0.70	-20	130.831329	0.70
i 1	73.000	0.60	-20	130.831329	0.70
i 1	74.200	0.70	-20	261.356964	0.70
i 1	75.500	0.60	-20	130.831329	0.70
i 1	76.800	0.60	-20	261.356964	0.70
i 1	78.000	0.60	-20	130.831329	0.70
i 1	79.200	0.70	-20	261.356964	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	1.700	1.30	-20	329.972809	0.30
i 1	4.200	1.30	-20	329.972809	0.30
i 1	6.700	1.30	-20	329.972809	0.30
i 1	9.200	1.30	-20	329.972809	0.30
i 1	11.700	1.30	-20	329.972809	0.30
i 1	14.200	1.30	-20	329.972809	0.30
i 1	16.700	1.30	-20	329.972809	0.30
i 1	19.200	1.30	-20	329.972809	0.30
i 1	21.700	1.30	-20	349.564941	0.30
i 1	24.200	1.30	-20	349.564941	0.30
i 1	26.700	1.30	-20	349.564941	0.30
i 1	29.200	1.30	-20	349.564941	0.30
i 1	31.800	1.20	-20	329.972809	0.30
i 1	34.300	1.20	-20	329.972809	0.30
i 1	36.800	1.20	-20	329.972809	0.30
i 1	39.300	1.20	-20	329.972809	0.30
i 1	41.800	1.20	-20	329.972809	0.30
i 1	44.300	1.20	-20	329.972809	0.30
i 1	46.700	1.30	-20	329.972809	0.30
i 1	49.300	1.20	-20	329.972809	0.30
i 1	55.500	5.00	-20	392.493958	0.30
i 1	63.000	5.00	-20	349.564941	0.30
i 1	71.800	5.60	-20	261.356964	0.30
i 1	79.300	1.20	-20	392.493958	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
