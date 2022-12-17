package org.rwtodd.agires;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A resource directory for V1 an V2 AGI games.
 * @author rwtodd
 */
class V2ResourceDirectory implements ResourceDirectory {

    private final byte[] sounds, views, pics, logics;

    public V2ResourceDirectory(final Path gamePath) throws AgiException {
        try {
            sounds = Files.readAllBytes(gamePath.resolve("SNDDIR"));
            views = Files.readAllBytes(gamePath.resolve("VIEWDIR"));
            pics =  Files.readAllBytes(gamePath.resolve("PICDIR"));
            logics = Files.readAllBytes(gamePath.resolve("LOGDIR"));
        } catch (IOException e) {
            throw new AgiException("Could not load resource directories!", e);
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
            byte volnum = (byte) ((dir[index] & 0xff) >> 4);
            int offs = ((dir[index] & 0x0f) << 16) | ((dir[index + 1]&0xff) << 8) | (dir[index + 2]&0xff);
            return DirEntry.of(volnum, offs);
        } catch (IndexOutOfBoundsException oob) {
            // if we fell off the sounds list, just tell the caller the resource does not exist.
            return DirEntry.NON_EXISTENT;
        }
    }
}
