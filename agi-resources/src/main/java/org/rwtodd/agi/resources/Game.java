package org.rwtodd.agi.resources;

import java.nio.file.Path;
import java.nio.file.Paths;

/** Represents an AGI Game.
  @author Richard Todd
*/
public class Game {
    private final Path gameDir;
    private final EngineVersion version;
    
    public Game(Path directory) throws AGIException {
        gameDir = directory;
        version = new EngineVersion(gameDir);
        System.out.println("Game is version " + version.toString());
    }
    
    public Game(String directory) throws AGIException {
        this(Paths.get(directory));
    }
}
