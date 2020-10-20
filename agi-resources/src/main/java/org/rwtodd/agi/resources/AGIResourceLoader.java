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
 * A resource loader for all AGI games.
 * @author rwtodd
 */
public class AGIResourceLoader implements ResourceLoader {

    private final VolumeManager vmgr;
    private final ResourceDirectory rdir;
    private final double version;

    public AGIResourceLoader(double version, ResourceDirectory rd, VolumeManager vm) throws AGIException {
        this.version = version;
        rdir = rd;
        vmgr = vm;
    }

    @Override
    public void close() throws IOException {
        vmgr.close();
    }
    
    @Override
    public int getSoundCount() {
        return rdir.getSoundCount();
    }
    @Override
    public int getPicCount() {
        return rdir.getPicCount();
    }
    @Override
    public int getViewCount() {
        return rdir.getViewCount();
    }
    @Override
    public int getLogicCount() {
        return rdir.getLogicCount();
    }
    
    @Override
    public void loadSound(int number) throws AGIException, ResourceNotPresentException {
        final var dirEntry = rdir.findSound(number);
        if(!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("Sound resource " + number + " isn't present!");
        }
        loadSound(dirEntry);
    }

    protected void loadSound(DirEntry de) throws AGIException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        try {
            Files.write(Path.of(".",de.toString()), resbytes);
        } catch (IOException ioe) {
            throw new AGIException("Problem writing out resource.", ioe);
        }
    }

}
