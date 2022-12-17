package rwt;

import org.junit.jupiter.api.Test;
import org.rwtodd.agires.AgiResourceLoader;
import org.rwtodd.agires.AgiSound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// note to self... to get raw data of resource... set a breakpoint in the resource loader and evaluate:
// IntStream.range(0, resbytes.length).mapToObj(i -> Byte.toString(resbytes[i])).collect(Collectors.joining(", "))


public class TestSound {

    @Test
    void testExampleSound() throws Exception {
       final var snd = new byte[] { 8, 0, 64, 1, -40, 1, 112, 2, 45, 0, 0, -113, -97, 11, 0, 73, -116, -112,
               11, 0, 0, -113, -97, 8, 0, 77, -128, -112, 4, 0, 0, -113, -97, 7, 0, 73, -116, -112, 4, 0, 0,
               -113, -97, 45, 0, 70, -120, -112, 23, 0, 0, -113, -97, 7, 0, 71, -124, -112, 4, 0, 0, -113,
               -97, 7, 0, 70, -120, -112, 4, 0, 0, -113, -97, 11, 0, 69, -116, -112, 12, 0, 0, -113, -97, 5,
               0, 70, -120, -112, 6, 0, 0, -113, -97, 7, 0, 71, -124, -112, 4, 0, 0, -113, -97, -106, 0, 70,
               -120, -112, 31, 0, 0, -113, -97, 7, 0, 73, -116, -112, 15, 0, 0, -113, -97, 8, 0, 77, -128,
               -112, 3, 0, 0, -113, -97, 6, 0, 73, -116, -112, 6, 0, 0, -113, -97, 54, 0, 70, -120, -112, 13,
               0, 0, -113, -97, 8, 0, 73, -116, -112, 4, 0, 0, -113, -97, 5, 0, 70, -120, -112, 6, 0, 0, -113,
               -97, 9, 0, 71, -124, -112, 13, 0, 0, -113, -97, 6, 0, 73, -116, -112, 6, 0, 0, -113, -97, 5, 0,
               71, -124, -112, 6, 0, 0, -113, -97, 11, 0, 70, -120, -112, 4, 0, 0, -113, -97, 11, 0, 71, -124,
               -112, 4, 0, 0, -113, -97, 11, 0, 73, -116, -112, 4, 0, 0, -113, -97, 11, 0, 71, -124, -112, 4,
               0, 0, -113, -97, 11, 0, 70, -120, -112, 4, 0, 0, -113, -97, 11, 0, 71, -124, -112, 4, 0, 0, -113,
               -97, 11, 0, 70, -120, -112, 4, 0, 0, -113, -97, 11, 0, 71, -124, -112, 4, 0, 0, -113, -97, 11, 0,
               73, -116, -112, 4, 0, 0, -113, -97, 11, 0, 71, -124, -112, 12, 0, 70, -120, -112, 11, 0, 0, -113,
               -97, 11, 0, 71, -124, -112, -109, 0, 68, -114, -112, -1, -1, -30, 0, 0, -81, -65, 11, 0, 103, -96,
               -78, 11, 0, 0, -81, -65, 6, 0, 116, -95, -78, 5, 0, 0, -81, -65, 6, 0, 103, -96, -78, 6, 0, 0, -81,
               -65, 45, 0, 90, -96, -78, 22, 0, 0, -81, -65, 6, 0, 103, -96, -78, 6, 0, 0, -81, -65, 5, 0, 90,
               -96, -78, 6, 0, 0, -81, -65, 6, 0, 85, -82, -78, 16, 0, 0, -81, -65, 12, 0, 87, -93, -78, 5, 0, 93,
               -93, -78, 6, 0, 0, -81, -65, -115, 0, 90, -96, -78, 39, 0, 0, -81, -65, 21, 0, 103, -96, -78, 24,
               0, 0, -81, -65, 21, 0, 116, -95, -78, 25, 0, 0, -81, -65, 20, 0, 103, -96, -78, 25, 0, 0, -81, -65,
               22, 0, 116, -95, -78, 17, 0, 0, -81, -65, 6, 0, 116, -95, -78, -114, 0, 100, -23, -78, -1, -1, -31,
               0, 0, -49, -33, 11, 0, 83, -56, -47, 12, 0, 0, -49, -33, 5, 0, 90, -64, -47, 6, 0, 0, -49, -33, 5,
               0, 83, -56, -47, 6, 0, 0, -49, -33, 45, 0, 77, -64, -47, 23, 0, 0, -49, -33, 5, 0, 83, -56, -47, 6,
               0, 0, -49, -33, 6, 0, 77, -64, -47, 5, 0, 0, -49, -33, 6, 0, 74, -49, -47, 17, 0, 0, -49, -33, 11,
               0, 75, -55, -47, 6, 0, 78, -55, -47, 5, 0, 0, -49, -33, -115, 0, 77, -64, -47, 40, 0, 0, -49, -33,
               20, 0, 83, -56, -47, 25, 0, 0, -49, -33, 20, 0, 90, -64, -47, 25, 0, 0, -49, -33, 21, 0, 83, -56,
               -47, 24, 0, 0, -49, -33, 23, 0, 90, -64, -47, 16, 0, 0, -49, -33, 6, 0, 90, -64, -47, -113, 0, 103,
               -64, -47, -1, -1, -1, -1 };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(1.419),
                new DummyResDir(100),
                new DummyVolMgr(snd))) {
            var s = rl.loadSound(10);
            assertTrue(s.noise().isEmpty());
            assertEquals(3, s.voices().size());
            assertEquals(913, s.getLength());
            assertEquals(78, s.voices().get(0).notes().get(0).toMidiNoteNumber());
            assertEquals(913, s.voices().get(0).getLength());
            assertEquals(909, s.voices().get(1).getLength());
            assertEquals(909, s.voices().get(2).getLength());
            assertEquals(32, s.voices().get(0).notes().size());
            assertEquals(16, s.voices().get(1).notes().size());
            assertEquals(16, s.voices().get(2).notes().size());
            assertEquals(383, s.voices().get(1).notes().get(7).startTime());
            assertEquals(45, s.voices().get(1).notes().get(3).duration());
            assertEquals(833, s.voices().get(1).notes().stream().mapToInt(AgiSound.Note::frequency).max().getAsInt());
            assertEquals(78, s.voices().get(0).notes().stream().mapToInt(AgiSound.Note::frequency).min().getAsInt());
        }
    }

    @Test
    void testNoisySound() throws Exception {
        final var snd = new byte[] {
                8, 0, 40, 0, 47, 0, 54, 0, 3, 0, 60, -128, -102, 3, 0, 58, -128, -104, 3, 0, 56, -128,
                -107, 3, 0, 54, -128, -106, 3, 0, 52, -128, -106, 3, 0, 63, -128, -97, -1, -1, 18, 0, 63,
                -96, -65, -1, -1, 18, 0, 63, -64, -33, -1, -1, 3, 0, 0, -26, -8, 6, 0, 0, -27, -12, 6, 0,
                0, -28, -16, 3, 0, 0, -26, -1, -1, -1
        };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(2.919),
                new DummyResDir(100),
                new DummyVolMgr(snd))) {
            final var s = rl.loadSound(10);
            assertTrue(s.noise().isPresent());
            assertEquals(1, s.voices().size());
            assertEquals(15, s.getLength());
            assertEquals(46, s.voices().get(0).notes().get(0).toMidiNoteNumber());
            assertEquals(15, s.voices().get(0).getLength());
            assertEquals(5, s.voices().get(0).notes().size());
            assertEquals(832, s.voices().get(0).notes().stream().mapToInt(AgiSound.Note::frequency).min().getAsInt());
            assertEquals(15, s.noise().get().getLength());
            assertEquals(3, s.noise().get().noises().size());
            assertEquals(6, s.noise().get().noises().stream().mapToInt(AgiSound.Noise::duration).max().getAsInt());
            assertEquals(3,s.noise().get().noises().get(0).duration());
            assertEquals(AgiSound.NoiseType.WHITE, s.noise().get().noises().get(0).type());
            assertEquals(AgiSound.NoiseType.WHITE, s.noise().get().noises().get(1).type());
            assertEquals(AgiSound.NoiseType.WHITE, s.noise().get().noises().get(2).type());
        }
    }
}
