/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Extracts metadata from the disk files of the game.
 * @author rwtodd
 */
public class OnDiskMetaData implements GameMetaData {

    private final String versionString;
    private final String prefixString;

    public OnDiskMetaData(Path gamePath) throws AGIException {
        versionString = OnDiskMetaData.extractVersion(gamePath);
        prefixString = (versionString.charAt(0) == '3')
                ? OnDiskMetaData.determinePrefix(gamePath)
                : null;
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    @Override
    public String getPrefix() {
        return prefixString;
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
            for (final var match : matches) {
                if (!Files.isReadable(match)) {
                    continue;
                }
                final var vol0 = match.getFileName().toString();
                final var pfx = vol0.substring(0, vol0.length() - 5);
                // now, check if pfx+"DIR" also exists...
                if (Files.isReadable(match.resolveSibling(pfx + "DIR"))) {
                    return pfx;
                }
            }
            throw new AGIException("Could not find <pfx>DIR and <pfx>VOL.0 files (uppercase) to get game prefix!");
        } catch (Throwable tr) {
            throw new AGIException("Error while finding V3 game prefix!", tr);
        }
    }

}
