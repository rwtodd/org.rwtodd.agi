package org.rwtodd.agires;

import java.util.List;
import java.util.Optional;

public record AgiSound(List<ToneChannel> voices, Optional<NoiseChannel> noise) {

    public AgiSound {
        // ensure all provided voices have a length
        if (voices.size() > 0 && voices.stream().anyMatch(tc -> tc.notes().isEmpty())) {
            throw new IllegalArgumentException("Can't give an empty ToneChannel to voices!");
        }
        if (noise.isPresent() && noise.get().noises.isEmpty()) {
            throw new IllegalArgumentException("Can't provide a present but empty Noise channel!");
        }
    }

    public int getLength() {
        return Math.max(
                voices.stream().mapToInt(ToneChannel::getLength).max().getAsInt(),
                noise.map(NoiseChannel::getLength).orElse(0)
        );
    }

    public record ToneChannel(List<Note> notes) {
        public int getLength() { return notes.get(notes.size() - 1).getEndTime(); }
    }

    public record Note(int startTime, int duration, int frequency, int attenuation) {
        boolean isSilent() { return attenuation == 15; }
        int getEndTime() { return startTime + duration; }

        private static final double log2 = Math.log(2.0);
        private static final double tandy_freq = 111860.78125;

        public int toMidiNoteNumber() {
            // this is the formula that seems to work best for AGI games...
            // but the actual frequencies put out by the PCJr/Tandy do not match
            // 12ET 440Hz tuning.  This is just an approximation.
            final double hz = tandy_freq / frequency;
            return  (int) Math.round(36 + 12 * Math.log(hz / 64.0) / log2);
        }

        public double toFrequencyInHz() {
            return tandy_freq / frequency;
        }

    }

    public record NoiseChannel(List<Noise> noises) {
        public int getLength() { return noises.get(noises.size() - 1).getEndTime(); }
    }

    public enum NoiseType { WHITE, LINEAR }
    public record Noise(int startTime, int duration, int frequency, int attenuation, NoiseType type) {
        boolean isSilent() { return attenuation == 15; }
        int getEndTime() { return startTime + duration; }
    }
}
