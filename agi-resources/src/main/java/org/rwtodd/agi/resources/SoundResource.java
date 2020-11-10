package org.rwtodd.agi.resources;

/**
 * Represents a 4-voice AGI sound resource. It does minimal interpretation and
 * validation of the resource, and works with a client-provided Builder to build
 * the representation of the sound needed by the client.
 *
 * @author rwtodd
 */
public class SoundResource {

    /**
     * The two types of noise the tandy/PCJr can make.
     */
    public static enum NoiseType {
        LINEAR, WHITE
    };

    /**
     * An interface for accepting events from a SoundResource, with the intent
     * of building whatever derived object from the data the client needs. This
     * takes the tedium of parsing the source bytes and encapsulates it away
     * from the clients.
     */
    public static interface Builder {

        void soundStart(int number, String desc);

        void voiceStart(int num);

        void voiceNote(int time, int duration, int freq, int attenuation);

        void voiceEnd();

        void noiseStart();

        void noiseNote(int time, int duration, int freq, int attenuation, NoiseType type);

        void noiseEnd();

        void soundEnd(int totalDuration);
    }

    private final byte[] data;
    private final int resNumber;
    private final DirEntry dirEntry;
    
    public SoundResource(final int number, final DirEntry de, final byte[] src) {
        resNumber = number;
        dirEntry = de;
        data = src;
    }

    public void build(final Builder b) throws AGIException {
        int totalLength = 0;
        try {
            b.soundStart(resNumber, dirEntry.toString());

            totalLength = Math.max(totalLength, streamVoice(1, b));
            totalLength = Math.max(totalLength, streamVoice(2, b));
            totalLength = Math.max(totalLength, streamVoice(3, b));
            totalLength = Math.max(totalLength, streamNoise(b));

            b.soundEnd(totalLength);
        } catch (Exception e) {
            throw new AGIException("Error while parsing a sound resource", e);
        }
    }

    private void checkVoiceLength(int offset, int len) throws AGIException {
        final int remainder = len % 5;
        if ((remainder == 0)
                || ((remainder == 2) && (data[offset + len - 1] == -1) && (data[offset + len - 2] == -1))) {
            return;
        }
        throw new AGIException("Sound has irregular voice of length " + len);
    }

    private int streamVoice(final int num, final Builder b) throws AGIException {
        int curTime = 0;
        int audibleLength = 0;

        final int base = (num - 1) * 2;
        int idx = (data[base] & 0xff) | ((data[base + 1] & 0xff) << 8);
        final int end = (data[base + 2] & 0xff) | ((data[base + 3] & 0xff) << 8);
        checkVoiceLength(idx, end - idx);
        b.voiceStart(num);

        while ((idx + 4) < end) {
            final int startTime = curTime;
            final int attenuation = data[idx + 4] & 0x0f;
            final int duration = (data[idx] & 0xff) | ((data[idx + 1] & 0xff) << 8);
            curTime += duration;
            if (attenuation < 15) {
                // audible
                final int freq = ((data[idx + 2] & 0x3f) << 4) | (data[idx + 3] & 0x0f);
                b.voiceNote(startTime, duration, freq, attenuation);
                audibleLength = curTime;
            }
            idx += 5;
        }

        b.voiceEnd();
        return audibleLength;
    }

    private int streamNoise(final Builder b) throws AGIException {
        int curTime = 0;
        int audibleLength = 0;

        int idx = (data[6] & 0xff) | ((data[7] & 0xff) << 8);
        final int end = data.length;
        checkVoiceLength(idx, end - idx);
        b.noiseStart();

        while ((idx + 4) < end) {
            final NoiseType nt = ((data[idx + 3] & 0x04) == 0)
                    ? NoiseType.LINEAR
                    : NoiseType.WHITE;
            final int startTime = curTime;
            final int attenuation = data[idx + 4] & 0x0f;
            final int duration = (data[idx] & 0xff) | ((data[idx + 1] & 0xff) << 8);
            curTime += duration;
            if (attenuation < 15) {
                // audible
                final int freq = switch (data[idx + 3] & 0x03) {
                    case 0 ->
                        0x10;
                    case 1 ->
                        0x20;
                    case 2 ->
                        0x40;
                    default ->
                        0x00;
                };
                b.noiseNote(startTime, duration, freq, attenuation, nt);
                audibleLength = curTime;
            }
            idx += 5;
        }

        b.noiseEnd();
        return audibleLength;
    }
}
