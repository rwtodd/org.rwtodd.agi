package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 * The IF 0xff instruction, in cases where the THEN portion does not have room
 * according to the structured code one level above it.
 * @author rwtodd
 */
public class UnlessGotoInstruction implements Instruction {
    private final Instruction conditions, jumpIns;
    
    public UnlessGotoInstruction(final Instruction conditions, int relativeJump) {
        this.conditions = conditions;
        this.jumpIns = new GotoInstruction(relativeJump);
    }

    @Override
    public int getLength() {
        // 0xFF (conditions) 0xFF (JNZ JNZ)
        return 4 + conditions.getLength();
    }

    @Override
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation) {
        pw.printf("%04X: %sUNLESS(\n", baseLocation++, indentation);
        var indentFurther = indentation + "    ";
        conditions.printTo(pw, script, resLoader, baseLocation, indentFurther);
        baseLocation += conditions.getLength();
        jumpIns.printTo(pw, script, resLoader, baseLocation, indentation + ") ");
    }
}

