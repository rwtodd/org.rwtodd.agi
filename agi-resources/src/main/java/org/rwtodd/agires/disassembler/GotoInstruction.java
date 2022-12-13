package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

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
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation) {
        pw.printf("%04X: %sGOTO(%04X)\n", baseLocation, indentation, baseLocation + relativeTarget + 3);
    }
    
}
