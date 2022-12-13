package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 * An interface for disassembled AGI bytecodes.
 * @author rwtodd
 */
public interface Instruction {
    /**
     * Gets the length of the instruction, in bytes.
     * @return the byte-length.
     */
    int getLength();   
    
    /**
     * Prints a formatted rep`representation of the instruction.
     * @param pw the destination PrintWRiter
     * @param script the logic script that "owns" this instruction
     * @param resLoader the current game's resource loader
     * @param baseLocation where in the bytecodes does this instruction start?
     * @param indentation a string to prefix onto each line of our output
     */
    void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation);

}
