package rwt;

import org.junit.jupiter.api.Test;
import org.rwtodd.agires.AgiResourceLoader;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestPic {
    private byte[] resourceBytes(String name) throws Exception {
        try (var rsc = this.getClass().getResourceAsStream(name)) {
            assertNotNull(rsc);
            return rsc.readAllBytes();
        }
    }
    @Test
    void testLoadKnownPic() throws Exception {
        final byte[] input = resourceBytes("srcbytes.bin"),
                outPic = resourceBytes("picbytes.bin"),
                outPri = resourceBytes("pribytes.bin");
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(2.936),
                new DummyResDir(100),
                new DummyVolMgr(input))) {
            final var p = rl.loadPic(99);
            assertArrayEquals(outPic, p.picture().pixels());
            assertArrayEquals(outPri, p.priority().pixels());
        }

    }
}
