package org.rwtodd.agi.disassembler;

import java.io.PrintWriter;

/**
 * A label in the disassembly stream.
 * @author rwtodd
 * @deprecated maybe don't need?
 */
public class LabelInstruction implements Instruction {
    private final String name;
    
    public LabelInstruction(String name) {
        this.name = name;
    }
    
    @Override
    public int getLength() {
        return 0; // labels don't have lengths
    }

    @Override
    public void printTo(final PrintWriter pw, final LogicScript script, final int baseLocation, final String indentation) {
        pw.printf("%04X: %s:\n", baseLocation, name);
    }
    
}
