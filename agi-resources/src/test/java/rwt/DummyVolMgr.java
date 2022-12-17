package rwt;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.DirEntry;
import org.rwtodd.agires.ResourceNotPresentException;
import org.rwtodd.agires.VolumeManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A VolumeManager that just returns canned data.
 */
class DummyVolMgr implements VolumeManager {
    private final byte[] cannedData;
    private final byte[] cannedWords;
    private final byte[] cannedObjects;

    DummyVolMgr(byte[] words, byte[] objects, byte[] data) {
        if(words == null) {
            byte[] abc = wordsTokStr("abc");
            words = new byte[] { 0, 2, 0, abc[0], abc[1], abc[2], 0, 1 };
        }
        cannedWords = words;
        if(objects == null) {
            objects = new byte[] {
                    3,0,16,  // object names start at index 6, 16 maximum animated
                    3,0,1,   // the only object is named at 6 and starts in room 1
                    'b','o','o','k',0 // the object is named "book"
            };
        }
        cannedObjects = objects;
        cannedData = data;
    }

    DummyVolMgr(byte[] data) { this(null, null, data); }

    @Override
    public byte[] getResource(DirEntry de) throws AgiException, ResourceNotPresentException {
        return cannedData;
    }

    @Override
    public byte[] getWordsData() throws IOException {
        return cannedWords;
    }

    @Override
    public byte[] getObjectsData() throws IOException {
        return cannedObjects;
    }

    @Override
    public void close() throws IOException {
        /* nothing! */
    }

    /**
     * helper method to adjust words for use in WORDS.TOK dummy data.
     * @param word the word to convert
     * @return the converted byte array
     */
    static byte[] wordsTokStr(String word) {
        byte[] answer = word.getBytes(StandardCharsets.US_ASCII);
        for(int i = 0; i < answer.length; ++i) {
            answer[i] = (byte)((answer[i] & 0x7f) ^ 0x7f);
        }
        //set the high bit on the last char
        answer[answer.length-1] |= 0x80;
        return answer;
    }
}
