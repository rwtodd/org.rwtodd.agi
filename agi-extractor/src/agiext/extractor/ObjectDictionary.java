package agiext.extractor;

import java.util.List;
import java.util.stream.Stream;

/**
 * An interface representing a dictionary of AGI objects.
 * @author rwtodd
 */
public interface ObjectDictionary {    
    
    /**
     * Returns the object signified by `index`, or NO_OBJECT otherwise.
     * @param index the index of the desired obejct
     * @return the object, or {@code NO_OBJECT} if it does not exist.
     */
    default Entry get(int index) {
        final var o = getObjects();
        return (index < o.size()) ? o.get(index) : NO_OBJECT;
    }
    
    default Stream<Entry> objectStream() { return getObjects().stream(); }
    
    List<Entry> getObjects();
    
    int getMaximumAnimated();

    public static class Entry implements Comparable<Entry> {
        private final String name;
        private final int startingRoom;
        
        public Entry(String n, int sr) {
            name = n;
            startingRoom = sr;
        }
        
        public String getName() { return name; }
        public int getStartingRoom() { return startingRoom; }

        @Override
        public int compareTo(final Entry o) {
            return name.compareTo(o.name);
        }
    }
    
    public final static Entry NO_OBJECT = new Entry("Unknown Object!", 255);
}
