package org.rwtodd.agi.resources;

import java.io.ByteArrayOutputStream;

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
     * Decode bytes against an XOR'ed code word. The bytes are altered in place.
     *
     * @param code the code word(s)
     * @param src the source bytes (modified by this function)
     * @return the source bytes
     */
    static byte[] decodeInPlace(final byte[] code, final byte[] src, final int start, final int end) {
        int cIdx = 0;
        for (int sIdx = start; sIdx < end; ++sIdx) {
            src[sIdx] ^= code[cIdx];
            if (++cIdx == code.length) {
                cIdx = 0;
            }
        }
        return src;
    }

    /**
     * Decode source bytes, in place, with Sierra-standard codeword "Avis
     * Durgan."
     *
     * @param src source bytes (modified in place)
     * @param start where to start in `src`
     * @param end where to stop in `src` (exclusive).
     * @return the source bytes
     */
    static byte[] decodeInPlace(final byte[] src, final int start, final int end) {
        return decodeInPlace(AVIS, src, start, end);
    }
    
    /**
     * Decode source bytes, in place, with Sierra-standard codeword "Avis
     * Durgan."
     *
     * @param src source bytes (modified in place)
     * @return the source bytes
     */
    static byte[] decodeInPlace(final byte[] src) {
        return decodeInPlace(AVIS, src, 0, src.length);
    }

    /**
     * Pull a null-terminated ASCII string out of source bytes. The string
     * starts at the source bytes at the given offset.
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
        return new String(src, offs, zero - offs, java.nio.charset.StandardCharsets.US_ASCII);
    }

    /**
     * Expand a compressed byte array into a newly-allocated byte array.
     *
     * @param src the compressed source bytes
     * @param expandedSize the expected size of the decompressed output
     * @return the decompressed results
     * @throws AGIException when there is a problem expanding the src
     */
    static byte[] lzwExpand(final byte[] src, final int expandedSize) throws AGIException {
        // keep track of our output
        final var output = new byte[expandedSize];
        int outIdx = 0;

        // some useful constants
        final short INVALID = -1;   // an invalid code.
        final int MAX_WIDTH = 11; // this is what's unusual about sierra's LZW...
        // it maxes out at 11 bits instead of the typical 12
        final short CLEAR = 256;   // hard-code clear and eof codes
        final short EOF = 257;

        // our compression dictionary
        short[] prefix = new short[1 << MAX_WIDTH];
        byte[] suffix = new byte[1 << MAX_WIDTH];
        int width = 9;             // the number of bits to read at a time
        int hi = EOF;              // the highest code
        int overflow = (1 << 9);   // the point where the width expands again
        short last = INVALID;             // the last code seen

        // reading n bits at a time requires some buffereing
        int read_bits = 0;
        int read_bits_size = 0;

        // here's the main loop
        for (final byte srcByte : src) {
            // STEP ONE... read until we have the next token
            read_bits |= ((srcByte & 0xff) << read_bits_size);
            read_bits_size += 8;
            if (read_bits_size < width) {
                continue;
            }
            final short current = (short) ((read_bits & ((1 << width) - 1)) & 0xffff);
            read_bits_size -= width;
            read_bits >>= width;

            // STEP TWO: interpret `current`
            if (current < CLEAR) {
                // literal byte...
                final byte asByte = (byte) (current & 0xff);
                output[outIdx++] = asByte;
                if (last != INVALID) {
                    suffix[hi] = asByte;
                    prefix[hi] = last;
                }
            } else if (current == CLEAR) {
                width = 9;
                hi = EOF;
                overflow = 1 << 9;
                last = INVALID;
                continue;
            } else if (current == EOF) {
                break;
            } else if (current <= hi) {
                // need to expand a dictionary token
                short token = current;
                final int beginIdx = outIdx; // remember where we started
                // there is a special case for current == hi
                if ((token == hi) && (last != INVALID)) {
                    token = last;
                    while (token >= CLEAR) {
                        token = prefix[token];
                    }
                    output[outIdx++] = (byte) (token & 0xff);
                    token = last;
                }
                
                // do the expansion
                while (token >= CLEAR) {
                    output[outIdx++] = suffix[token];
                    token = prefix[token];
                }
                output[outIdx++] = (byte) (token & 0xff);

                // now reverse the expanded codes
                final int middle = (outIdx - beginIdx) / 2;
                for (int i = 0; i < middle; ++i) {
                    final int left = beginIdx + i;
                    final int right = outIdx - i - 1;
                    final byte j = output[left];
                    output[left] = output[right];
                    output[right] = j;
                }

                // finally, store the meaning of this new suffix
                if (last != INVALID) {
                    suffix[hi] = (byte) (token & 0xff);
                    prefix[hi] = last;
                }
            } else {
                throw new AGIException("Malformed LZW resource!");
            }

            // STEP THREE: now get ready for the next loop
            last = current;
            if (++hi >= overflow) {
                if (width == MAX_WIDTH) {
                    // can't expand width, so set us up to avoid storing more
                    last = INVALID;
                    --hi;
                } else {
                    // expand the width
                    ++width;
                    overflow <<= 1;
                }
            }
        }

        // make sure we got what we expected
        if (outIdx != output.length) {
            throw new AGIException(
                    String.format("LZW: resource was supposed to expand to %d bytes but only got %d!",
                            outIdx,
                            output.length));
        }
        return output;
    }
}
