package org.rwtodd.agi.resources;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

/**
 * Determine the AGI Engine version of a game, and answer questions about it.
 * The DIR/VOL prefixes are considered aspects of the EngineVersion.
 *
 * @author rwtodd
 */
public class EngineVersion {

    private final double numericVersion;
    private final String strVersion;
    private final String gamePrefix;

    /**
     * Create an EngineVersion for the game rooted at gamePath.
     *
     * @param gamePath the root directory of the game
     * @throws AGIException if the version number cannot be determined.
     */
    public EngineVersion(Path gamePath) throws AGIException {
        strVersion = EngineVersion.extractVersion(gamePath);

        // convert the string version to a number
        try {
            String looksLikeDouble = strVersion;
            if (strVersion.length() > 5) {
                looksLikeDouble = strVersion.substring(0, 5) + strVersion.substring(6);
            }
            numericVersion = Double.valueOf(looksLikeDouble);
        } catch (NumberFormatException nfe) {
            throw new AGIException("Somehow extracted a bad version #" + strVersion, nfe);
        }

        // extract the game prefix, if needed...
        gamePrefix = (numericVersion > 2.9999)
                ? EngineVersion.determinePrefix(gamePath)
                : null;
    }

    @Override
    public String toString() {
        return strVersion;
    }

    /**
     * Get the prefix used by this game engine for VOL/DIR files.
     *
     * @return the prefix
     * @throws IllegalStateException when the game doesn't have a prefix.
     */
    public String getPrefix() throws IllegalStateException {
        if (gamePrefix == null) {
            throw new IllegalStateException("Game prefix is only for V3+ games!");
        }
        return gamePrefix;
    }

    /**
     * Determine if this is a version 3+ game
     *
     * @return true for yes, false for no
     */
    public boolean isV3() {
        return numericVersion > 2.9999;
    }

    /**
     * Determine if this is a version 1 or 2 game.
     *
     * @return true for yes, false for no.
     */
    public boolean isBeforeV3() {
        return numericVersion < 3.0;
    }

    /**
     * Search the bytes of AGIDATA.OVL for a version number.
     *
     * @param agidata the bytes of AGIDATA.OVL
     * @return a string of the version number
     * @throws AGIException if it can't find the version number
     */
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

    /**
     * Search the bytes of AGIDATA.OVL for a version number.
     *
     * @param gamePath the location of AGIDATA.OVL
     * @return a string of the version number
     * @throws AGIException if it can't find the version number
     */
    private static String extractVersion(Path gamePath) throws AGIException {
        try {
            final var ovlFile = gamePath.resolve("AGIDATA.OVL");
            final var agidata
                    = Files.readAllBytes(ovlFile);
            return extractVersion(agidata);
        } catch (IOException ioe) {
            throw new AGIException("Failed to extract AGI version!", ioe);
        }
    }

    /**
     * Figure out the game's prefix on DIR/VOL files.
     *
     * @param gamePath
     * @return the prefix string
     * @throws AGIException when the prefix cannot be determined
     */
    private static String determinePrefix(final Path gamePath) throws AGIException {
        try (final var matches = Files.newDirectoryStream(gamePath, "*VOL.0")) {
            for(final var match : matches) {
                if(!Files.isReadable(match)) continue;
                final var vol0 = match.getFileName().toString();
                final var pfx = vol0.substring(0, vol0.length()-5);
                // now, check if pfx+"DIR" also exists...
                if(Files.isReadable(match.resolveSibling(pfx+"DIR"))) {
                    return pfx;
                }
            }
            throw new AGIException("Could not find <pfx>DIR and <pfx>VOL.0 files (uppercase) to get game prefix!");
        } catch(Throwable tr) {
            throw new AGIException("Error while finding V3 game prefix!",tr);
        }
    }
}
