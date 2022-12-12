package org.rwtodd.agires;

import org.rwtodd.agires.util.Util;

import java.util.Arrays;

/**
 * Represents the contents of a logic resource. It doesn't provide a builder
 * like the other resources do... logic resources are basically byte[] bytecodes
 * with associated string resources. So there's not much to "build."
 *
 * @author rwtodd
 */
public class LogicResource {

    private final String[] scriptStrings;
    private final byte[] script;

    LogicResource(final byte[] src) {
        final var textArea = ((src[0] & 0xff) | ((src[1] & 0xff) << 8)) + 2;
        final var msgCount = src[textArea] & 0xff;
        scriptStrings = new String[msgCount];
        final var empty = "";
        var stringIndex = textArea + 1;
        for (int n = 0; n < msgCount; ++n, stringIndex += 2) {
            final var offset = ((src[stringIndex] & 0xff) | ((src[stringIndex + 1] & 0xff) << 8));
            if (offset != 0) {
                scriptStrings[n] = Util.asciizString(src, textArea + offset + 1);
            } else {
                scriptStrings[n] = empty;
            }
        }
        script = Arrays.copyOfRange(src, 2, textArea);
    }

    public String getMessage(int n) {
        return (n < scriptStrings.length) ? scriptStrings[n] : "";
    }

    public int getMessageCount() {
        return scriptStrings.length;
    }

    public byte[] getByteCodes() {
        return script;
    }
}
