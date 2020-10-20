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
 *
 * @author rwtodd
 */
public class V2ResourceLoader implements ResourceLoader {

    private final VolumeManager vmgr;
    private final ResourceDirectory rdir;

    public V2ResourceLoader(ResourceDirectory rd, VolumeManager vm) throws AGIException {
        rdir = rd;
        vmgr = vm;
    }

    @Override
    public void loadSound(int number) throws AGIException, ResourceNotPresentException {
        final var dirEntry = rdir.findSound(number);
        if(!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("Sound resource " + number + " isn't present!");
        }
        loadSound(dirEntry);
    }

    @Override
    public void loadSound(DirEntry de) throws AGIException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        try {
            Files.write(Path.of(".",de.toString()), resbytes);
        } catch (IOException ioe) {
            throw new AGIException("Problem writing out resource.", ioe);
        }
    }

}
