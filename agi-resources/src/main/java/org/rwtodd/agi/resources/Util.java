package org.rwtodd.agi.resources;

/**
 * A set of static utility functions
 *
 * @author rwtodd
 */
class Util {

    // "Avis Durgan"
    private static final byte[] AVIS
            = {65, 118, 105, 115, 32, 68, 117, 114, 103, 97, 110};

    /**
     * Decode bytes against an XOR'ed code word.  The bytes are altered in
     * place.
     * 
     * @param code the code word(s)
     * @param src  the source bytes (modified by this function)
     * @return the source bytes
     */
    static byte[] decodeInPlace(final byte[] code, final byte[] src) {
        int cIdx = 0;
        for (int sIdx = 0; sIdx < src.length; ++sIdx) {
            src[sIdx] ^= code[cIdx];
            if (++cIdx == code.length) {
                cIdx = 0;
            }
        }
        return src;
    }

    /**
     * Decode source bytes, in place, with Sierra-standard codeword
     * "Avis Durgan."
     * 
     * @param src source bytes (modified in place)
     * @return the source bytes
     */
    static byte[] decodeInPlace(final byte[] src) {
        return decodeInPlace(AVIS, src);
    }

    /**
     * Pull a null-terminated ASCII string out of source bytes.  The string starts
     * at the source bytes at the given offset.
     * 
     * @param src the source bytes of the ASCII string.
     * @param offs the start of the string, in src
     * @return A string made of bytes copied from the src array.
     */
    static String asciizString(final byte[] src, final int offs) {
        int zero = offs;
        while ((zero < src.length) && (src[zero] != 0)) {
            ++zero;
        }
        return new String(src,offs,zero - offs,java.nio.charset.StandardCharsets.US_ASCII);
    }
}
