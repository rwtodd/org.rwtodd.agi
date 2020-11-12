package org.rwtodd.agi.resources;

/**
 * An interface for dot patterns that AGI pens use in PIC resources.
 * @author rwtodd
 */
interface PenPattern extends Iterable<Boolean> {
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
