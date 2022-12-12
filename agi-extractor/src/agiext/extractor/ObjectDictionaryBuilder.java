package agiext.extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.rwtodd.agires.ObjectsResource;

/**
 * Builds a list of game objects as a handler for the ObjectsResource.
 * @author rwtodd
 */
class ObjectDictionaryBuilder implements ObjectsResource.Builder, ObjectDictionary {
  
    private int maxAnimated;
    private List<ObjectDictionary.Entry> objects;
    
    public ObjectDictionaryBuilder() { 
        maxAnimated = -1;
        objects = new ArrayList<>();
    }

    @Override
    public void startObjects(int maximumAnimated) {
        maxAnimated = maximumAnimated;
    }

    @Override
    public void object(int startingRoom, String name) {
        objects.add(new ObjectDictionary.Entry(name, startingRoom));
    }

    @Override
    public void endObjects() {
        // make the list read-only for safety.
        objects = Collections.unmodifiableList(objects);
    }
    
    
    @Override
    public List<ObjectDictionary.Entry> getObjects() {
        return objects;
    }
        
    @Override
    public int getMaximumAnimated() { return maxAnimated; }
}
