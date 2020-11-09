package org.rwtodd.agi.disassembler;

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
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, String indentation) {
        pw.printf("%04X: %sUNLESS(\n", baseLocation++, indentation);
        var indentFurther = indentation + "    ";
        conditions.printTo(pw, script, baseLocation, indentFurther);
        baseLocation += conditions.getLength();
        jumpIns.printTo(pw, script, baseLocation, indentation + ") ");
    }
}

