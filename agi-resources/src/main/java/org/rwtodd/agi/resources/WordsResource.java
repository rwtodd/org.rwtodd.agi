package org.rwtodd.agi.resources;

/**
 * Represents the contents of an AGI WORDS.TOK file.
 *
 * @author rwtodd
 */
public class WordsResource {

    /**
     * An interface for accepting events from a WordsResource, with the intent
     * of building whatever derived object from the data the client needs. This
     * takes the tedium of parsing the source bytes and encapsulates it away
     * from the clients.
     */
    public static interface Builder {

        void wordsStart();

        void word(String word, int group);

        void wordsEnd();
    }

    private final byte[] data;

    public WordsResource(final byte[] src) {
        data = src;
    }

    public void build(final Builder b) throws AGIException {
        final var buffer = new StringBuilder(32);
        try {
            b.wordsStart();
            for (int idx = (data[1]&0xff); idx < data.length;) {
                // compute the prefix
                final int toSkip = data[idx++];
                if (toSkip > buffer.length()) {
                    throw new AGIException("Malformed WORDS.TOK: prefix larger than last word!");
                }
                buffer.setLength(toSkip);
                
                // we might be out of data, so check...
                if(idx == data.length) break;
                
                // read the suffix
                byte nextChar;
                do {
                    nextChar = data[idx++];
                    buffer.append((char) ((nextChar^0x7f)&0x7f));
                } while(nextChar >= 0);
                
                // read the group number, big-endian 
                final int groupNumber = ((data[idx]&0xff)<<8)|(data[idx+1]&0xff);
                idx+=2;
                
                b.word(buffer.toString(), groupNumber);
            }
            b.wordsEnd();
        } catch (AGIException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AGIException("Error while parsing WORDS.TOK", e);
        }
    }

}
