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
 *
 * @author rwtodd
 */
public class AGIResourceLoader implements ResourceLoader {

    private final VolumeManager vmgr;
    private final ResourceDirectory rdir;
    private final Path gamePath;
    private final double version;

    public AGIResourceLoader(
            final Path gamePath,
            double version,
            final ResourceDirectory rd,
            final VolumeManager vm) throws AGIException {
        this.version = version;
        this.gamePath = gamePath;
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
    public SoundResource loadSound(int number) throws AGIException, ResourceNotPresentException {
        final var dirEntry = rdir.findSound(number);
        if (!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("Sound resource " + number + " isn't present!");
        }
        return loadSound(number, dirEntry);
    }

    protected SoundResource loadSound(int number, DirEntry de) throws AGIException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        return new SoundResource(number, de, resbytes);
    }

    @Override
    public WordsResource loadWords() throws AGIException {
        try {
            final var wordsTok = Files.readAllBytes(gamePath.resolve("WORDS.TOK"));
            return new WordsResource(wordsTok);
        } catch(Exception e) {
            throw new AGIException("Could not load WORDS.TOK!", e);
        }
    }
    
    @Override
    public ObjectsResource loadObjects() throws AGIException {
        try {
            final var objfile = Files.readAllBytes(gamePath.resolve("OBJECT"));
            if(version >= 2.411) {
                Util.decodeInPlace(objfile);
            }
            return new ObjectsResource(objfile);
        } catch(Exception e) {
            throw new AGIException("Could not load OBJECT!", e);
        }
    }
}
