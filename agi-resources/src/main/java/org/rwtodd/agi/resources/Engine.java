package org.rwtodd.agi.resources;

import java.nio.file.Path;

/**
 * Determine the AGI Engine version of a game, and answer questions about it.
 * The DIR/VOL prefixes are considered aspects of the EngineVersion.
 *
 * @author rwtodd
 */
public class Engine {

    private final ResourceLoadingFactory loadingFactory;
    private final Path gamePath;
    private final double numericVersion;
    private final String strVersion;
    private final String gamePrefix;
    
    private ResourceDirectory resourceDir;

    /**
     * Create an Engine with the specified version number, resource path,
     * and prefix.
     *
     * @param gamePath the location of the resource files
     * @param rlf a ResourceLoadingFactory for the needed components
     * @throws AGIException if there is an error during creation.
     */
    public Engine(final Path gamePath, final ResourceLoadingFactory rlf)
            throws AGIException {
        this.gamePath = gamePath;
        loadingFactory = rlf;
        final var meta = rlf.createGameMetaData(gamePath);
        strVersion = meta.getVersionString();
        numericVersion = meta.getVersionNumber();
        gamePrefix = meta.getPrefix();
        resourceDir = null; // not loaded yet
    }

    @Override
    public String toString() {
        return String.format("%s Engine v%s", (gamePrefix==null)?"AGI":gamePrefix, strVersion);
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
     * A factory method for ResourceDirectories, creating an implementation
     * appropriate for this engine.
     *
     * @return a suitable ResourceDirectory.
     * @throws AGIException when there is a problem creating the directory
     */
    public ResourceDirectory getResourceDirectory()
            throws AGIException {
        if(resourceDir == null)
            resourceDir = loadingFactory.createResourceDirectory(this);
        return resourceDir;
    }
    
    /**
     * Gets the root game path for this engine.
     * @return the root path
     */
    public Path getGamePath() {
        return gamePath;
    }
}
