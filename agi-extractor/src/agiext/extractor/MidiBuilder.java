package agiext.extractor;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import javax.sound.midi.InvalidMidiDataException;
import org.rwtodd.agires.Builders.SoundBuilder;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiSystem;

/**
 * A handler for sound resources which output CSound scores.
 *
 * @author rwtodd
 */
class MidiBuilder implements SoundBuilder, Closeable {

    private final Path outFile;
    private final Sequence seq;
    private Track currentTrack;
    private int channel;

    MidiBuilder(Path outfile) throws InvalidMidiDataException {
        outFile = outfile;
        seq = new Sequence(Sequence.PPQ, 60);
        currentTrack = null;
        channel = -1;
    }

    @Override
    public void soundStart(int num, String desc) throws InvalidMidiDataException {
        // create a name/tempo track...
        final var metaString = String.format("AGI Sound Resource %d (%s)", num, desc)
                .getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        final var tempoTrack = seq.createTrack();
        tempoTrack.add(new MidiEvent(new MetaMessage(0x03, metaString, metaString.length), 0));

        /* set the tempo to 120BPM... which is the default but let's be precise */
        final int tempo = 60000000 / 120;
        tempoTrack.add(
                new MidiEvent(
                        new MetaMessage(
                                0x51,
                                new byte[]{(byte) ((tempo >> 16) & 0xff), (byte) ((tempo >> 8) & 0xff), (byte) (tempo & 0xff)},
                                3),
                        0));

    }

    @Override
    public void voiceStart(int num) throws InvalidMidiDataException {
        currentTrack = seq.createTrack();
        ++channel;
        // name the track based on the voice/channel...
            final var metaString = String.format("AGI Voice %d", channel)
                    .getBytes(java.nio.charset.StandardCharsets.US_ASCII);
            currentTrack.add(new MidiEvent(new MetaMessage(0x03, metaString, metaString.length), 0));
    }

    /* constant needed for frequency to midi-note conversion */
    private static final double log2 = Math.log(2.0);

    @Override
    public void voiceNote(int time, int duration, int freq, int attenuation) throws InvalidMidiDataException {
        // this is the formula that seems to work best for AGI games...
        // but the actual frequencies put out by the PCJr/Tandy do not match
        // 12ET 440Hz tuning.  This is just an approximation.
        final double hz = 111860.78125 / freq;
        final int noteNum = (int) Math.round(36 + 12 * Math.log(hz / 64.0) / log2);

        // try to map attenuation to velocity 
        final int velocity = 100 - 6 * attenuation;

        // we are 120BPM, with 60 ticks per beat... the source data is 60 ticks per second... so double times...
        time *= 2;
        duration *= 2;

            currentTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, noteNum, velocity), time));
            currentTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, noteNum, 0), time + duration));

    }

    @Override
    public void voiceEnd() {
        currentTrack = null;
    }

    @Override
    public void noiseStart() {
        /* we ignore noise */
    }

    @Override
    public void noiseNote(int time, int duration, int freq, int attenuation, SoundBuilder.NoiseType type) {
        /* we ignore noise */
    }

    @Override
    public void noiseEnd() {
        /* we ignore noise */
    }

    @Override
    public void soundEnd(int totalDuration) {
        /* do nothing */
    }

    @Override
    public void close() throws IOException {
        MidiSystem.write(seq, 1, outFile.toFile());
    }

}
