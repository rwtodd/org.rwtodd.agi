package org.rwtodd.agi.extractor;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.rwtodd.agi.resources.SoundResource;

/**
 * A handler for sound resources which output CSound scores.
 *
 * @author rwtodd
 */
class CSoundHandler implements SoundResource.Handler, Closeable {

    private final PrintWriter out;
    private int curVoice;

    CSoundHandler(Path outfile) throws IOException {
        out = new PrintWriter(
                Files.newBufferedWriter(outfile, java.nio.charset.StandardCharsets.UTF_8));
        curVoice = -1;
    }

    @Override
    public void soundStart(int num, String desc) {
        out.printf(";; AGI Sound Resource %d (%s)\n\n", num, desc);
        out.print("""
                    
                  t 0 3600 ;; AGI runs in 1/60th second ticks
                    
                  ; set up the instruments
                  i 1  0  0  1   0 4   ;; 4 Rhodes piano 
                  i 1  0  0  2   0 4   ;; 4 Rhodes piano 
                  i 1  0  0  3   0 4   ;; 4 Rhodes piano 
                    
                  ; set up the panning
                  i 2  0  0  1 0.5     ;; middle
                  i 2  0  0  2 0.7     ;; right
                  i 2  0  0  3 0.3     ;; left
                  
                  
                  """);
    }

    @Override
    public void voiceStart(int num) {
        curVoice = 10 + num;
        out.printf(";; Start of voice %d (instrument %d)\n", num, curVoice);
    }

    @Override
    public void voiceNote(int time, int duration, int freq, int attenuation) {
        out.printf("i%d\t%d\t%d\t%d\t%d\n",
                curVoice,
                time,
                duration,
                attenuation,
                freq);
    }

    @Override
    public void voiceEnd() {
        out.printf(";; End of instrument %d\n\n", curVoice);
        curVoice = -1;
    }

    @Override
    public void noiseStart() {
        out.print(";; Start of noise channel (instrument 21 an 31)\n");
    }

    @Override
    public void noiseNote(int time, int duration, int freq, int attenuation, SoundResource.NoiseType type) {
        final int inst = switch (type) {
            case WHITE ->
                21;
            case LINEAR ->
                31;
        };
        out.printf("i%d\t%d\t%d\t%d\t%d\n",
                inst,
                time,
                duration,
                attenuation,
                freq);

    }

    @Override
    public void noiseEnd() {
        out.print(";; End of noise channel\n");
    }

    @Override
    public void soundEnd(int totalDuration) {
        out.print(";; mixer\n");
        out.printf("i99 0 %d 0.9 1.0 1.0\n",totalDuration+60);
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

}
