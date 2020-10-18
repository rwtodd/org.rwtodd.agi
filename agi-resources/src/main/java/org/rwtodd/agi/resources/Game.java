package org.rwtodd.agi.resources;

import java.nio.file.Path;
import java.nio.file.Paths;

/** Represents an AGI Game.
  @author Richard Todd
*/
public class Game {
    private final Path gamePath;
    private final EngineVersion version;
    private final ResourceDirectory directory;
    
    public Game(final Path gamePath) throws AGIException {
        this.gamePath = gamePath;
        version = new EngineVersion(gamePath);
        System.out.println("Game is version " + version.toString());
        if(version.isV3()) System.out.println("Game prefix is: " + version.getPrefix());
        directory = ResourceDirectory.create(gamePath, version);
        System.out.printf("There are %d logics.\n", directory.getLogicCount());
        System.out.printf("There are %d pics.\n", directory.getPicCount());
        System.out.printf("There are %d views.\n", directory.getViewCount());
        System.out.printf("There are %d sounds.\n", directory.getSoundCount());
    }
    
    public Game(final String pathString) throws AGIException {
        this(Paths.get(pathString));
    }
    
    @Override
    public String toString() {
        return String.format("AGI Game (%s)", gamePath);
    }
}
