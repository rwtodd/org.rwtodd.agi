package agiext;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.rwtodd.agires.AgiSound;

/**
 * A handler for sound resources which output CSound scores.
 *
 * @author Richard Todd
 */
abstract class CSoundBuilder {

    static void writeScore(Path outfile, int soundNumber, AgiSound sound) throws Exception {
            try(final var out = new PrintWriter(
                    Files.newBufferedWriter(outfile, java.nio.charset.StandardCharsets.UTF_8))) {
                out.printf(";; AGI Sound Resource %d\n\n", soundNumber);
                out.print("""
                    
                  t 0 3600 ;; AGI runs in 1/60th second ticks
                    
                  ; set up the instruments if using a MIDI-converted orchestra
                  i 1  0  0  1   0 4   ;; 4 Rhodes piano
                  i 1  0  0  2   0 4   ;; 4 Rhodes piano
                  i 1  0  0  3   0 4   ;; 4 Rhodes piano
                    
                  ; set up the panning
                  i 2  0  0  1 0.5     ;; middle
                  i 2  0  0  2 0.7     ;; right
                  i 2  0  0  3 0.3     ;; left
                  
                  
                  """);
                int curVoice = 10;
                if(sound.voices().isEmpty()) out.printf(";; No tonal voices for this sound.  noise-only!");
                for(final var voice: sound.voices()) {
                    ++curVoice;
                    out.printf(";; Start of voice %d (instrument %d)\n", curVoice - 10, curVoice);
                    out.print(";;\tstart\tdur\tlevel\tfreq\n");

                    for(final var note: voice.notes()) {
                        out.printf("i%d\t%d\t%d\t%d\t%d\n",
                                curVoice,
                                note.startTime(),
                                note.duration(),
                                note.attenuation(),
                                note.frequency());
                    }

                    out.printf(";; End of instrument %d\n\n", curVoice);
                }
                if(sound.noise().isPresent()) {
                    out.print(";; Start of noise channel (instrument 21 an 31)\n");
                    out.print(";;\tstart\tdur\tlevel\tfreq\n");

                    for(final var noise: sound.noise().get().noises()) {
                        final int inst = switch (noise.type()) {
                            case WHITE -> 21;
                            case LINEAR -> 31;
                        };
                        out.printf("i%d\t%d\t%d\t%d\t%d\n",
                                inst,
                                noise.startTime(),
                                noise.duration(),
                                noise.attenuation(),
                                noise.frequency());
                    }
                    out.print(";; End of noise channel\n\n");
                } else {
                    out.print(";; No noise channel in this sound.\n\n");
                }

                out.print(";; mixer\n;;\tstart\tdur\trev\tlvl1\tlvl2\n");
                out.printf("i99\t0\t%d\t0.9\t1.0\t1.0\n", sound.getLength()+60);
            }
    }

}
