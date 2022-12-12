package org.rwtodd.agires;

import org.rwtodd.agires.restypes.SoundResource;

public abstract class Builders {
    /**
     * An interface for accepting events from a SoundResource, with the intent
     * of building whatever derived object from the data the client needs. This
     * takes the tedium of parsing the source bytes and encapsulates it away
     * from the clients.
     */
    public interface SoundBuilder {
        /**
         * The two types of noise the tandy/PCJr can make.
         */
        enum NoiseType {
            LINEAR, WHITE
        };

        void soundStart(int number, String desc) throws Exception;

        void voiceStart(int num) throws Exception;

        void voiceNote(int time, int duration, int freq, int attenuation) throws Exception;

        void voiceEnd() throws Exception;

        void noiseStart() throws Exception;

        void noiseNote(int time, int duration, int freq, int attenuation, NoiseType type) throws Exception;

        void noiseEnd() throws Exception;

        void soundEnd(int totalDuration) throws Exception;
    }

}
