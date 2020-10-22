/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

import java.io.Closeable;

/**
 * An interface describing classes that actually load resources and construct
 * objects of them.
 *
 * @author rwtodd
 */
public interface ResourceLoader extends Closeable {

    static ResourceLoader createDefault(GameMetaData meta, ResourceDirectory resdir, VolumeManager volmgr)
            throws AGIException {
        return new AGIResourceLoader(meta.getGamePath(), meta.getVersion(), resdir, volmgr);
    }

    static ResourceLoader createDefault(GameMetaData meta) throws AGIException {
        return ResourceLoader.createDefault(
                meta,
                ResourceDirectory.createDefault(meta),
                VolumeManager.createDefault(meta));
    }

    int getSoundCount();

    int getPicCount();

    int getViewCount();

    int getLogicCount();

    SoundResource loadSound(int number) throws AGIException, ResourceNotPresentException;

    WordsResource loadWords() throws AGIException;
}
