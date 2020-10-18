package org.rwtodd.agi.resources;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

/**
 * Determine the AGI Engine version of a game, and answer questions about it.
 *
 * @author rwtodd
 */
public class EngineVersion {

    private final double numericVersion;
    private final String strVersion;

    public EngineVersion(Path gameDir) throws AGIException {
        strVersion = EngineVersion.extractVersion(gameDir);
        String looksLikeDouble = strVersion;
        if (strVersion.length() > 5) {
            looksLikeDouble = strVersion.substring(0, 5) + strVersion.substring(6);
        }
        try {
            numericVersion = Double.valueOf(looksLikeDouble);
        } catch (NumberFormatException nfe) {
            throw new AGIException("Somehow extracted a bad version #" + strVersion, nfe);
        }
    }

    @Override
    public String toString() {
        return strVersion;
    }

    /**
     * Determine if this is a version 3+ game
     *
     * @return true for yes, false for no
     */
    public boolean isV3() {
        return numericVersion >= 3.0;
    }

    /**
     * Determine if this is a version 1 or 2 game.
     *
     * @return true for yes, false for no.
     */
    public boolean isBeforeV3() {
        return numericVersion < 3.0;
    }

    private static String extractVersion(byte[] agidata) throws AGIException {
        final var buffer = new StringBuilder(16);
        for (final var b : agidata) {
            final int len = buffer.length();
            final boolean dotSpot = (len == 1 || len == 5);

            // at 1 and 5 we need a '.'
            if (dotSpot && (b == 0x2e)) {
                buffer.append((char) b);
            } else if (!dotSpot && (b >= 0x30 && b <= 0x39)) {
                // numeric ok in other spots
                buffer.append((char) b);
                if (len == 8) // len is 9 now
                {
                    return buffer.toString();
                }
            } else if (len == 5) {
                // must be a short version number
                return buffer.toString();
            } else {
                buffer.setLength(0);
            }
        }
        throw new AGIException("End of AGIDATA.OVL without finding version!");
    }

    private static String extractVersion(Path gameDir) throws AGIException {
        try {
            final var ovlFile = gameDir.resolve("AGIDATA.OVL");
            final var agidata
                    = Files.readAllBytes(ovlFile);
            return extractVersion(agidata);
        } catch (IOException ioe) {
            throw new AGIException("Failed to extract AGI version!", ioe);
        }
    }
}
