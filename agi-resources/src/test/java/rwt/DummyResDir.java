package rwt;

import org.rwtodd.agires.DirEntry;
import org.rwtodd.agires.ResourceDirectory;

public class DummyResDir implements ResourceDirectory {
    private final int count;

    /**
     * This resdir will have {@code count} entries for each resource type, and
     * returns a dummy {@code DirEntry} for any resource that exists.
     *
     * @param count the number of entries
     */
    DummyResDir(int count) {
        this.count = count;
    }

    private DirEntry genericFind(int num) {
        if (num >= count) {
            return DirEntry.NON_EXISTENT;
        }
        return DirEntry.of((byte)1,100);
    }
    @Override
    public DirEntry findSound(int number) {
        return genericFind(number);
    }

    @Override
    public int getSoundCount() {
        return count;
    }

    @Override
    public DirEntry findPic(int number) {
        return genericFind(number);
    }

    @Override
    public int getPicCount() {
        return count;
    }

    @Override
    public DirEntry findView(int number) {
        return genericFind(number);
    }

    @Override
    public int getViewCount() {
        return count;
    }

    @Override
    public DirEntry findLogic(int number) {
        return genericFind(number);
    }

    @Override
    public int getLogicCount() {
        return count;
    }
}
