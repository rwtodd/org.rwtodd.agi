package rwt;

import org.junit.jupiter.api.Test;
import org.rwtodd.agires.AgiResourceLoader;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestPic {

    @Test
    void testLoadKnownPic() throws Exception {
        final var input =  this.getClass().getResourceAsStream("srcbytes.bin").readAllBytes();
        final var outPic = this.getClass().getResourceAsStream("picbytes.bin").readAllBytes();
        final var outPri = this.getClass().getResourceAsStream("pribytes.bin").readAllBytes();
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
