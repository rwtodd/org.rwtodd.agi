package org.rwtodd.agi.resources;

import java.nio.file.Path;

/**
 * A disk-backed volume manager for V1 and V2 AGI game.
 * @author rwtodd
 */
public class V2VolumeManager implements VolumeManager {

    public V2VolumeManager(Path gamePath) { }
    
    @Override
    public byte[] getResource(DirEntry de) throws AGIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
