package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiDictionary;
import org.rwtodd.agires.AgiException;

/**
 * Represents the contents of an AGI WORDS.TOK file.
 *
 * @author rwtodd
 */
public abstract class WordsResource {

    public static AgiDictionary build(final byte[] data) throws AgiException {
        final var answer = new AgiDictionary();
        final var buffer = new StringBuilder(32);
        try {
            for (int idx = (data[1]&0xff); idx < data.length;) {
                // compute the prefix
                final int toSkip = data[idx++];
                if (toSkip > buffer.length()) {
                    throw new AgiException("Malformed WORDS.TOK: prefix larger than last word!");
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
                
                answer.addWord(buffer.toString(), groupNumber);
            }
            return answer;
        } catch (AgiException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AgiException("Error while parsing WORDS.TOK", e);
        }
    }

}
