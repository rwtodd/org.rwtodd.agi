package org.rwtodd.agi.disassembler;

import java.io.PrintWriter;

/**
 * A GOTO Instruction
 * @author rwtodd
 */
public class GotoInstruction implements Instruction {
    private final int relativeTarget;
    
    public GotoInstruction(final int target) {
        relativeTarget = target;
    }
    
    @Override
    public int getLength() {
        return 3;
    }

    @Override
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, String indentation) {
        pw.printf("%04X: %sGOTO(%04X)\n", baseLocation, indentation, baseLocation + relativeTarget + 3);
    }
    
}
