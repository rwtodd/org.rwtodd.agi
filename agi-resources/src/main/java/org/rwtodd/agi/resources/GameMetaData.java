package org.rwtodd.agi.resources;

import java.nio.file.Path;

/**
 * Represents an ability to pull metadata (version string, etc) about a game.
 * Separated as an interface to enable mocking/testing etc.
 *
 * @author rwtodd
 */
public interface GameMetaData {

    /** 
     * Gets the version number of the game engine, as a string.
     * @return the version string
     */
    String getVersionString();

    /**
     * Returns the game's numeric version.
     * @return the version number
     */
    double getVersion();
    
    /**
     * Gets the game's prefix (e.g., KQ4) for resources and volumes
     * @return the prefix string, or null if there isn't one.
     */
    String getPrefix();
    
    /**
     * Gets the game's path on disk.
     * @return the path.
     */
    Path getGamePath();
    
    /**
     * Asks if the game version is 3 or higher.
     * @return true for yes, false for no.
     */
    default boolean isV3() { return getVersion() > 2.9999; }
    
    /**
     * asks if the game version is prior to version 3.
     * @return true for yes, false for no.
     */
    default boolean isBeforeV3() { return getVersion() < 3.0; }
    
    /**
     * A utility method to get a numeric version number from the version string.
     * @param versionString a string representation of an engine version
     * @return the numeric equivalent of the input
     * @throws AGIException if there is a problem converting the version string.
     */
    static double deriveNumericVersion(final String versionString) throws AGIException {
        // convert the string version to a number
        try {
            String looksLikeDouble = versionString;
            if (versionString.length() > 5) {
                looksLikeDouble = versionString.substring(0, 5) + versionString.substring(6);
            }
            return Double.valueOf(looksLikeDouble);
        } catch (NumberFormatException nfe) {
            throw new AGIException("Somehow extracted a bad version #" + versionString, nfe);
        }
    }
    

}
