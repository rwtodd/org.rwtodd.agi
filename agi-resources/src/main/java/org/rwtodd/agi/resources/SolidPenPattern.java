package org.rwtodd.agi.resources;

import java.util.Iterator;

/**
 * A PenPattern that represents a solid fill.
 * @author rwtodd
 */
public class SolidPenPattern implements PenPattern, Iterator<Boolean> {

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
