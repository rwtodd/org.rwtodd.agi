package org.rwtodd.agi.resources;

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
     * Gets the game's prefix (e.g., KQ4) for resources and volumes
     * @return the prefix string, or null if there isn't one.
     */
    String getPrefix();

    /**
     * Generates a numeric version number from the version string.
     * @return the number (as a double)
     * @throws AGIException if there is a problem converting the version string to a double
     */
    default double getVersionNumber() throws AGIException {
        // convert the string version to a number
        String strVersion = getVersionString();
        try {
            String looksLikeDouble = strVersion;
            if (strVersion.length() > 5) {
                looksLikeDouble = strVersion.substring(0, 5) + strVersion.substring(6);
            }
            return Double.valueOf(looksLikeDouble);
        } catch (NumberFormatException nfe) {
            throw new AGIException("Somehow extracted a bad version #" + strVersion, nfe);
        }
    }

}
