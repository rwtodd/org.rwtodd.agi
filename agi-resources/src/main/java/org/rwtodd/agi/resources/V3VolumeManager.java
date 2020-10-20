package org.rwtodd.agi.resources;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A disk-backed volume manager for V3 AGI game.
 * @author rwtodd
 */
public class V3VolumeManager implements VolumeManager {

    public V3VolumeManager(Path gamePath, String gamePrefix) { }
    
    @Override
    public byte[] getResource(DirEntry de) throws AGIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
