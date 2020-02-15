; This is '.', sound resource #22 of length 53.700001

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
i 99	0	59.700	0.9	1.0	1.0


; ****  Voice 1
; 1	time	dur	db	freq	pan
i 1	2.400	2.30	-20	329.972809	0.50
i 1	5.500	3.70	-20	440.396790	0.50
i 1	9.900	0.80	-20	415.839325	0.50
i 1	11.400	2.30	-20	440.396790	0.50
i 1	14.400	2.30	-20	553.766235	0.50
i 1	17.500	3.70	-20	494.959198	0.50
i 1	21.900	0.80	-20	466.086578	0.50
i 1	23.400	2.30	-20	494.959198	0.50
i 1	26.500	0.70	-20	553.766235	0.50
i 1	27.900	0.80	-20	494.959198	0.50
i 1	29.500	3.70	-20	440.396790	0.50
i 1	33.900	0.80	-20	370.399933	0.50
i 1	35.400	2.30	-20	370.399933	0.50
i 1	38.400	2.30	-20	329.972809	0.50
i 1	41.400	11.30	-20	440.396790	0.50


; ****  Voice 2
; 1	time	dur	db	freq	pan
i 1	5.500	11.30	-20	277.570190	0.70
i 1	17.500	11.20	-20	329.972809	0.70
i 1	29.500	11.20	-20	293.597839	0.70
i 1	41.500	11.20	-20	277.570190	0.70


; ****  Voice 3
; 1	time	dur	db	freq	pan
i 1	5.500	11.30	-20	329.972809	0.30
i 1	17.500	11.30	-20	415.839325	0.30
i 1	29.500	11.30	-20	370.399933	0.30
i 1	41.500	11.30	-20	329.972809	0.30


; ****  Voice 4 (Noise)
;  	time	dur	db	freq
