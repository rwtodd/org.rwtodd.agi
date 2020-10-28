package org.rwtodd.agi.resources;

import java.util.BitSet;
import java.util.Iterator;

/**
 * A PenPattern with AGI splatter code
 *
 * @author rwtodd
 */
public class SplatterPattern implements PenPattern {

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
