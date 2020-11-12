package org.rwtodd.agi.resources;

import java.util.Iterator;

/**
 * A PenPattern that represents a solid fill.  Since there is no variation or state in
 * solid fills, an `enum` is used to create a single INSTANCE that all objects
 * can share.
 * @author rwtodd
 */
enum SolidPenPattern implements PenPattern, Iterator<Boolean> {
    INSTANCE;
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Boolean next() {
        return Boolean.TRUE;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return this;
    }

    @Override
    public boolean takesArgument() {
        return false;
    }

    @Override
    public void setPattern(int patternNumber) {
        throw new IllegalStateException("Should never call 'setPattern' on a Solid Pattern!");
    }
    
}
