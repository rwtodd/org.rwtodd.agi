package agiext.extractor;

import java.io.IOException;
import java.nio.file.Path;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.AgiSound;

import javax.sound.midi.*;

/**
 * A handler for sound resources which output CSound scores.
 *
 * @author Richard Todd
 */
abstract class MidiBuilder {
    static void writeMidiFile(Path outfile, int soundNumber, AgiSound sound) throws InvalidMidiDataException,AgiException, IOException {
        if(sound.voices().isEmpty())
            throw new AgiException("Sound " + soundNumber + " has no voices (only noise)... no MIDI output");

        final var seq = new Sequence(Sequence.PPQ, 60);
        int channel = 0;

        try {
            // create a name/tempo track...
            var metaString = String.format("AGI Sound Resource %d", soundNumber)
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

            // add tracks for each voice
            for(final var voice: sound.voices()) {
                ++channel;
                final var currentTrack = seq.createTrack();
                // name the track based on the voice/channel...
                metaString = String.format("AGI Voice %d", channel)
                        .getBytes(java.nio.charset.StandardCharsets.US_ASCII);
                currentTrack.add(new MidiEvent(new MetaMessage(0x03, metaString, metaString.length), 0));

                for(final var note: voice.notes()) {
                    final int noteNum = note.toMidiNoteNumber();

                    // try to map attenuation to velocity
                    final int velocity = 100 - 6 * note.attenuation();

                    // we are 120BPM, with 60 ticks per beat... the source data is 60 ticks per second... so double times...
                    final int time = note.startTime() * 2;
                    final int duration = note.duration() * 2;
                    currentTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, noteNum, velocity), time));
                    currentTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, noteNum, 0), time + duration));
                }

            }

        } finally {
            MidiSystem.write(seq, 1, outfile.toFile());
        }
    }

}
