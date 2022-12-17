package rwt;

import org.junit.jupiter.api.Test;
import org.rwtodd.agires.AgiResourceLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestObjects {
    @Test
    void testDefaultObjects() throws Exception {
        // we'll test the default objects from DummyVolMgr first...
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(1.419),
                new DummyResDir(100),
                new DummyVolMgr(null,null,null))) {
            var o = rl.getInitialGameObjects();
            assertEquals(1, o.size());
            assertEquals("book", o.get(0).name());
            assertEquals(1, o.get(0).startingRoom());
            assertEquals(16, rl.getMaxAnimatedObjects());
        }
    }

    @Test
    void testTwoObjects() throws Exception {
        final byte[] obs = new byte[] {
                6,0,117,  // object names start at index 9, 117 maximum animated
                6,0,10,   // object 1 is in room 10
                11,0,30,  // object 2 is in room 30
                'b','a','n','k',0, // object 1
                'r','a','m',' ','h','e','a','d',0 // object 2
        };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(1.419),
                new DummyResDir(100),
                new DummyVolMgr(null, obs,null))) {
            var o = rl.getInitialGameObjects();
            assertEquals(2, o.size());
            assertEquals("bank", o.get(0).name());
            assertEquals(10, o.get(0).startingRoom());
            assertEquals("ram head", o.get(1).name());
            assertEquals(30, o.get(1).startingRoom());
            assertEquals(117, rl.getMaxAnimatedObjects());
        }
    }
}