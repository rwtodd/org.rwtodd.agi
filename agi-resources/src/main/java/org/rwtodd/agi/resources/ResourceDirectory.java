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
 * Responsible for generating DirEntry objects for every resource in a game.
 *
 * @author rwtodd
 */
public interface ResourceDirectory {

    /**
     * A factory method for ResourceDirectories, creating an implementation
     * appropriate for the provided EngineVersion.
     *
     * @param gamePath the path to the game's files
     * @param version the engine version used by the game
     * @return a suitable ResourceDirectory.
     * @throws AGIException when there is a problem creating the directory
     */
    static ResourceDirectory create(final Path gamePath, final EngineVersion version)
            throws AGIException {
        if (version.isBeforeV3()) {
            return new V2ResourceDirectory(gamePath);
        } else {
            return new V3ResourceDirectory(gamePath, version);
        }
    }

    /**
     * Locate the sound resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findSound(int number);

    /**
     * Return the number of sounds available in this resource directory.
     *
     * @return the number of sounds
     */
    int getSoundCount();

    /**
     * Locate the pic resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findPic(int number);

    /**
     * Return the number of pics available in this resource directory.
     *
     * @return the number of pics
     */
    int getPicCount();

    /**
     * Locate the view resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findView(int number);

    /**
     * Return the number of views available in this resource directory.
     *
     * @return the number of views
     */
    int getViewCount();

    /**
     * Locate the logic resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findLogic(int number);

    /**
     * Return the number of logic scripts available in this resource directory.
     *
     * @return the number of logic scripts
     */
    int getLogicCount();

}

class V2ResourceDirectory implements ResourceDirectory {

    private final Path gamePath;
    private final byte[] sounds, views, pics, logics;

    V2ResourceDirectory(final Path _gamePath) throws AGIException {
        gamePath = _gamePath;
        try {
            sounds = Files.readAllBytes(gamePath.resolve("SNDDIR"));
            views = Files.readAllBytes(gamePath.resolve("VIEWDIR"));
            pics =  Files.readAllBytes(gamePath.resolve("PICDIR"));
            logics = Files.readAllBytes(gamePath.resolve("LOGDIR"));
        } catch (IOException e) {
            throw new AGIException("Could not load resource directories!", e);
        }
    }

    @Override
    public int getSoundCount() {
        return sounds.length / 3;
    }

    @Override
    public int getPicCount() {
        return pics.length / 3;
    }

    @Override
    public int getViewCount() {
        return views.length / 3;
    }

    @Override
    public int getLogicCount() {
        return logics.length / 3;
    }

    @Override
    public DirEntry findSound(final int number) {
        return V2ResourceDirectory.lookupItem(sounds, number);
    }

    @Override
    public DirEntry findPic(final int number) {
        return V2ResourceDirectory.lookupItem(pics, number);
    }

    @Override
    public DirEntry findView(final int number) {
        return V2ResourceDirectory.lookupItem(views, number);
    }

    @Override
    public DirEntry findLogic(final int number) {
        return V2ResourceDirectory.lookupItem(logics, number);
    }

    /**
     * Perform the lookup of an item in a resource directory.
     *
     * @param dir the bytes of the target directory
     * @param number the number of the resource to look up
     * @return the resulting DirEntry
     */
    private static DirEntry lookupItem(final byte[] dir, int number) {
        try {
            final var index = number * 3;
            byte volnum = (byte) (dir[index] >> 4);
            int offs = ((dir[index] & 0x0f) << 16) | (dir[index + 1] << 8) | (dir[index + 2]);
            return DirEntry.of(volnum, offs);
        } catch (IndexOutOfBoundsException oob) {
            // if we fell off the sounds list, just tell the caller the resource does not exist.
            return DirEntry.NON_EXISTENT;
        }
    }
}

class V3ResourceDirectory implements ResourceDirectory {

    private final Path gamePath;
    private final byte[] dir;
    private final int logicOffs; // where the logic resources start
    private final int viewOffs;  // where the view resources start
    private final int picOffs;   // where the pic resources start
    private final int soundOffs; // where the sound resources start

    V3ResourceDirectory(final Path _gamePath, final EngineVersion version) throws AGIException {
        gamePath = _gamePath;
        try {
            dir = Files.readAllBytes(gamePath.resolve(version.getPrefix()+"DIR"));
            logicOffs = (dir[0]&0xff) | ((dir[1]&0xff) << 8);
            viewOffs  = (dir[2]&0xff) | ((dir[3]&0xff) << 8);
            picOffs   = (dir[4]&0xff) | ((dir[5]&0xff) << 8);
            soundOffs = (dir[6]&0xff) | ((dir[7]&0xff) << 8);
        } catch (Throwable tr) {
            throw new AGIException("Could not load resource directory!", tr);
        }
    }

    @Override
    public int getSoundCount() {
        return (dir.length - soundOffs) / 3;
    }

    @Override
    public int getPicCount() {
        return (soundOffs - picOffs) / 3;
    }

    @Override
    public int getViewCount() {
        return (picOffs - viewOffs) / 3;
    }

    @Override
    public int getLogicCount() {
        return (viewOffs - logicOffs) / 3;
    }

    @Override
    public DirEntry findSound(final int number) {
        return (number < getSoundCount())
                ? lookupItem(soundOffs + (number * 3))
                : DirEntry.NON_EXISTENT;
    }

    @Override
    public DirEntry findPic(final int number) {
        return (number < getPicCount())
                ? lookupItem(picOffs + (number * 3))
                : DirEntry.NON_EXISTENT;
    }

    @Override
    public DirEntry findView(final int number) {
        return (number < getViewCount())
                ? lookupItem(viewOffs + (number * 3))
                : DirEntry.NON_EXISTENT;
    }

    @Override
    public DirEntry findLogic(final int number) {
        return (number < getLogicCount())
                ? lookupItem(logicOffs + (number * 3))
                : DirEntry.NON_EXISTENT;
    }

    /**
     * Perform the lookup of an item in a resource directory.
     *
     * @param dir the bytes of the target directory
     * @param number the number of the resource to look up
     * @return the resulting DirEntry
     */
    private DirEntry lookupItem(final int index) {
        try {
            byte volnum = (byte) (dir[index] >> 4);
            int offs = ((dir[index] & 0x0f) << 16) | (dir[index + 1] << 8) | (dir[index + 2]);
            return DirEntry.of(volnum, offs);
        } catch (IndexOutOfBoundsException oob) {
            // if we fell off the sounds list, just tell the caller the resource does not exist.
            return DirEntry.NON_EXISTENT;
        }
    }
}
