package org.rwtodd.agi.resources;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A resource directory for V3+ AGI games.
 *
 * @author rwtodd
 */
public class V3ResourceDirectory implements ResourceDirectory {

    private final byte[] dir;
    private final int logicOffs; // where the logic resources start
    private final int viewOffs;  // where the view resources start
    private final int picOffs;   // where the pic resources start
    private final int soundOffs; // where the sound resources start

    V3ResourceDirectory(final Path gamePath, final String prefix) throws AGIException {
        try {
            dir = Files.readAllBytes(gamePath.resolve(prefix + "DIR"));
            logicOffs = (dir[0] & 0xff) | ((dir[1] & 0xff) << 8);
            picOffs = (dir[2] & 0xff) | ((dir[3] & 0xff) << 8);
            viewOffs = (dir[4] & 0xff) | ((dir[5] & 0xff) << 8);
            soundOffs = (dir[6] & 0xff) | ((dir[7] & 0xff) << 8);
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
        return (viewOffs - picOffs) / 3;
    }

    @Override
    public int getViewCount() {
        return (soundOffs - viewOffs) / 3;
    }

    @Override
    public int getLogicCount() {
        return (picOffs - logicOffs) / 3;
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
            byte volnum = (byte) ((dir[index] & 0xff) >> 4);
            int offs = ((dir[index] & 0x0f) << 16) | ((dir[index + 1] & 0xff) << 8) | (dir[index + 2] & 0xff);
            return DirEntry.of(volnum, offs);
        } catch (IndexOutOfBoundsException oob) {
            // if we fell off the sounds list, just tell the caller the resource does not exist.
            return DirEntry.NON_EXISTENT;
        }
    }
}
