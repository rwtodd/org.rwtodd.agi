package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.AgiSound;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a 4-voice AGI sound resource. It does minimal interpretation and
 * validation of the resource, and works with a client-provided Builder to build
 * the representation of the sound needed by the client.
 *
 * @author Richard Todd
 */
public abstract class SoundResource {


    public static AgiSound build(final byte[] data) throws AgiException {
        try {
            final var voices = new ArrayList<AgiSound.ToneChannel>();
            for(int which = 1; which <= 3; ++which) {
                final AgiSound.ToneChannel ch = streamVoice(data, which);
                if (ch != null) voices.add(ch);
            }
            final AgiSound.NoiseChannel nc = streamNoise(data);
            return new AgiSound(voices, Optional.ofNullable(nc));
        } catch (Exception e) {
            throw new AgiException("Error while parsing a sound resource", e);
        }
    }

    private static void checkVoiceLength(final byte[] data, int offset, int len) throws AgiException {
        final int remainder = len % 5;
        if ((remainder == 0)
                || ((remainder == 2) && (data[offset + len - 1] == -1) && (data[offset + len - 2] == -1))) {
            return;
        }
        throw new AgiException("Sound has irregular voice of length " + len);
    }

    private static AgiSound.ToneChannel streamVoice(final byte[] data, final int num) throws Exception {
        int curTime = 0;

        ArrayList<AgiSound.Note> noteList = null;
        final int base = (num - 1) * 2;
        int idx = (data[base] & 0xff) | ((data[base + 1] & 0xff) << 8);
        final int end = (data[base + 2] & 0xff) | ((data[base + 3] & 0xff) << 8);
        checkVoiceLength(data, idx, end - idx);

        while ((idx + 4) < end) {
            final int startTime = curTime;
            final int attenuation = data[idx + 4] & 0x0f;
            final int duration = (data[idx] & 0xff) | ((data[idx + 1] & 0xff) << 8);
            curTime += duration;
            if (attenuation < 15) {
                // audible
                final int freq = ((data[idx + 2] & 0x3f) << 4) | (data[idx + 3] & 0x0f);
                if(noteList == null) noteList = new ArrayList<>();
                noteList.add(new AgiSound.Note(startTime, duration, freq, attenuation));
            }
            idx += 5;
        }
        return (noteList != null) ? new AgiSound.ToneChannel(noteList) : null;
    }

    private static AgiSound.NoiseChannel streamNoise(final byte[] data) throws Exception {
        int curTime = 0;

        int idx = (data[6] & 0xff) | ((data[7] & 0xff) << 8);
        final int end = data.length;
        checkVoiceLength(data, idx, end - idx);
        ArrayList<AgiSound.Noise> noiseList = null;

        while ((idx + 4) < end) {
            final var nt = ((data[idx + 3] & 0x04) == 0)
                    ? AgiSound.NoiseType.LINEAR
                    : AgiSound.NoiseType.WHITE;
            final int startTime = curTime;
            final int attenuation = data[idx + 4] & 0x0f;
            final int duration = (data[idx] & 0xff) | ((data[idx + 1] & 0xff) << 8);
            curTime += duration;
            if (attenuation < 15) {
                // audible
                final int freq = switch (data[idx + 3] & 0x03) {
                    case 0 ->  0x10;
                    case 1 ->  0x20;
                    case 2 ->  0x40;
                    default -> 0x00;
                };
                if(noiseList == null) noiseList = new ArrayList<>();
                noiseList.add(new AgiSound.Noise(startTime, duration, freq, attenuation, nt));
            }
            idx += 5;
        }

        return (noiseList != null) ? new AgiSound.NoiseChannel(noiseList) : null;
    }
}
