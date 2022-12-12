package org.rwtodd.agires;

import org.rwtodd.agires.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Extracts metadata from the disk files of the game.
 *
 * @author rwtodd
 */
public class OnDiskMetaData implements GameMetaData {

    private final String versionString;
    private final String prefixString;
    private final double version;
    private final Path gamePath;
    private final byte[] decryptionKey;

    public OnDiskMetaData(final Path gamePath) throws AgiException {
        this.gamePath = gamePath;

        // assume it's a sierra game until I have some future way to determine that it's not...
        decryptionKey = Util.AVIS;

        versionString = OnDiskMetaData.extractVersion(gamePath);
        version = GameMetaData.deriveNumericVersion(versionString);
        prefixString = (version > 2.9999)
                ? OnDiskMetaData.determinePrefix(gamePath)
                : null;
    }

    @Override
    public Path getGamePath() {
        return gamePath;
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    @Override
    public String getPrefix() {
        return prefixString;
    }

    @Override
    public double getVersion() {
        return version;
    }

    @Override
    public byte[] getDecryptionKey() { return decryptionKey; }

    /**
     * Search the bytes of AGIDATA.OVL for a version number.
     *
     * @param agidata the bytes of AGIDATA.OVL
     * @return a string of the version number
     * @throws AgiException if it can't find the version number
     */
    private static String extractVersion(byte[] agidata) throws AgiException {
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
        throw new AgiException("End of AGIDATA.OVL without finding version!");
    }

    /**
     * Search the bytes of AGIDATA.OVL for a version number.
     *
     * @param gamePath the location of AGIDATA.OVL
     * @return a string of the version number
     * @throws AgiException if it can't find the version number
     */
    private static String extractVersion(Path gamePath) throws AgiException {
        try {
            final var ovlFile = gamePath.resolve("AGIDATA.OVL");
            final var agidata
                    = Files.readAllBytes(ovlFile);
            return extractVersion(agidata);
        } catch (IOException ioe) {
            throw new AgiException("Failed to extract AGI version!", ioe);
        }
    }

    /**
     * Figure out the game's prefix on DIR/VOL files.
     *
     * @param gamePath
     * @return the prefix string
     * @throws AgiException when the prefix cannot be determined
     */
    private static String determinePrefix(final Path gamePath) throws AgiException {
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
            throw new AgiException("Could not find <pfx>DIR and <pfx>VOL.0 files (uppercase) to get game prefix!");
        } catch (Throwable tr) {
            throw new AgiException("Error while finding V3 game prefix!", tr);
        }
    }

}
