package rwt;

import org.junit.jupiter.api.Test;
import org.rwtodd.agires.AgiResourceLoader;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestWords {
    private static String[] iterableToArray(Iterable<String> i) {
        return StreamSupport.stream(i.spliterator(), false).sorted().toArray(String[]::new);
    }

    @Test
    void testWordsTok1() throws Exception {
        // we'll test the default dictionary from DummyVolMgr first...
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(1.419),
                new DummyResDir(100),
                new DummyVolMgr(null,null,null))) {
            var w = rl.getDictionary();
            assertEquals(1, w.allWords().size());
            assertEquals(1, w.wordToId("abc"));
            //test other words get -1
            assertEquals(-1, w.wordToId("very"));
        }
    }

    @Test
    void testTwoWordsWithOverlap() throws Exception {
        final var w1 = DummyVolMgr.wordsTokStr("abc");
        final var w2 = DummyVolMgr.wordsTokStr("zz");
        var src = new byte[] { 0, 2, 0, w1[0], w1[1], w1[2], 1, 0, 1, w2[0], w2[1], 0, 20 };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(2.419),
                new DummyResDir(100),
                new DummyVolMgr(src,null,null))) {
            var w = rl.getDictionary();
            assertEquals(2, w.allWords().size());
            assertEquals(256, w.wordToId("abc"));
            assertEquals(20, w.wordToId("azz"));
        }
    }

    @Test
    void testThreeWordsWithOverlap() throws Exception {
        final var w1 = DummyVolMgr.wordsTokStr("abc");
        final var w2 = DummyVolMgr.wordsTokStr("zg");
        final var w3 = DummyVolMgr.wordsTokStr("png");
        var src = new byte[] {
                0, 2, 0, w1[0], w1[1], w1[2], 1, 0,
                1, w2[0], w2[1], 0, 20,
                0, w3[0], w3[1], w3[2], 0, 30 };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(2.419),
                new DummyResDir(100),
                new DummyVolMgr(src,null,null))) {
            var w = rl.getDictionary();
            assertEquals(3, w.allWords().size());
            assertEquals(256, w.wordToId("abc"));
            assertEquals(20, w.wordToId("azg"));
            assertEquals(30, w.wordToId("png"));
            assertArrayEquals(new String[] { "abc" }, iterableToArray(w.idToWords(256)));
            assertArrayEquals(new String[] { "azg" }, iterableToArray(w.idToWords(20)));
            assertArrayEquals(new String[] { "png" }, iterableToArray(w.idToWords(30)));
        }
    }

    @Test
    void testWordGroups() throws Exception {
        final var w1 = DummyVolMgr.wordsTokStr("abc");
        final var w2 = DummyVolMgr.wordsTokStr("zg");
        final var w3 = DummyVolMgr.wordsTokStr("png");
        var src = new byte[] {
                0, 2, 0, w1[0], w1[1], w1[2], 1, 0,
                1, w2[0], w2[1], 0, 20,
                0, w3[0], w3[1], w3[2], 1, 0 };
        try(var rl = new AgiResourceLoader(
                new DummyMetaData(3.419),
                new DummyResDir(100),
                new DummyVolMgr(src,null,null))) {
            var w = rl.getDictionary();
            assertEquals(3, w.allWords().size());
            assertEquals(256, w.wordToId("abc"));
            assertEquals(20, w.wordToId("azg"));
            assertEquals(256, w.wordToId("png"));
            assertArrayEquals(new String[] { "abc", "png"}, iterableToArray(w.idToWords(256)));
            assertArrayEquals(new String[] {"azg"}, iterableToArray(w.idToWords(20)));
            assertArrayEquals(new String[] {}, iterableToArray(w.idToWords(40)));
        }

    }
}
