package org.rwtodd.agires;

import java.util.Iterator;

/**
 * An interface for dot patterns that AGI pens use in PIC resources.
 *
 * @author Richard Todd
 */
sealed interface PenPattern extends Iterable<Boolean> permits SolidPenPattern, SplatterPattern {
    /**
     * Does this pen need an argument from the AGI resource (to set the pattern)?
     * @return true if yes, false if no
     */
    boolean takesArgument();
    
    /**
     * Set the pattern number to be used with this pen next.
     * @param patternNumber the pattern number
     */
    void setPattern(int patternNumber);
}


/**
 * A PenPattern that represents a solid fill.  Since there is no variation or state in
 * solid fills, an `enum` is used to create a single INSTANCE that all objects
 * can share.
 * @author Richard Todd
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


/**
 * A PenPattern with AGI splatter code
 *
 * @author Richard Todd
 */
final class SplatterPattern implements PenPattern {

    private int patternNumber; /* the current pattern number */

    private static class It implements Iterator<Boolean> {
        private int patternData;

        It(int pattern) {
            patternData = pattern | 0x01;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Boolean next() {
            patternData = (patternData >> 1) ^ ((patternData&1)*0xb8);
            return (patternData & 3) == 2;
        }
    }

    @Override
    public boolean takesArgument() {
        return true;
    }

    @Override
    public void setPattern(int patternNumber) {
        this.patternNumber = patternNumber;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new SplatterPattern.It(patternNumber);
    }

    public SplatterPattern() {
        patternNumber = 0;
    }
}
