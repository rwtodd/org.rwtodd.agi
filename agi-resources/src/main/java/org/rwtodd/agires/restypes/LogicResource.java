package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;
import org.rwtodd.agires.util.Util;
import java.util.Arrays;

/**
 * Decodes a logic resource.
 * @author Richard Todd
 */
public abstract class LogicResource {

    public static AgiLogicScript build(final AgiResourceLoader resLoader, final byte[] src) {
        final var textArea = ((src[0] & 0xff) | ((src[1] & 0xff) << 8)) + 2;
        final var msgCount = src[textArea] & 0xff;
        final var scriptStrings = new String[msgCount];
        final var empty = "";
        var stringIndex = textArea + 3;
        for (int n = 0; n < msgCount; ++n, stringIndex += 2) {
            final var offset = ((src[stringIndex] & 0xff) | ((src[stringIndex + 1] & 0xff) << 8));
            if (offset != 0) {
                scriptStrings[n] = Util.asciizString(src, textArea + offset + 1);
            } else {
                scriptStrings[n] = empty;
            }
        }
        final var byteCode = Arrays.copyOfRange(src, 2, textArea);
        return new AgiLogicScript(resLoader, scriptStrings, byteCode);
    }

}
