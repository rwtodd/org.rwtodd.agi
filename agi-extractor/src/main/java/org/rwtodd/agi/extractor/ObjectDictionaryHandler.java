package org.rwtodd.agi.extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.rwtodd.agi.resources.ObjectsResource;

/**
 * Builds a list of game objects as a handler for the ObjectsResource.
 * @author rwtodd
 */
class ObjectDictionaryHandler implements ObjectsResource.Handler {
    
    static class Entry implements Comparable<Entry> {
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
    
    private int maxAnimated;
    private List<Entry> objects;
    
    public ObjectDictionaryHandler() { 
        maxAnimated = -1;
        objects = new ArrayList<>();
    }

    @Override
    public void startObjects(int maximumAnimated) {
        maxAnimated = maximumAnimated;
    }

    @Override
    public void object(int startingRoom, String name) {
        objects.add(new Entry(name, startingRoom));
    }

    @Override
    public void endObjects() {
        // make the list read-only for safety.
        objects = Collections.unmodifiableList(objects);
    }
    
    public Entry get(int index) {
        return objects.get(index);
    }
    
    public List<Entry> getObjects() {
        return objects;
    }
    
    public Stream<Entry> objectStream() {
        return objects.stream();
    }
    
    public int getMaximumAnimated() { return maxAnimated; }
}
