package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 * The IF 0xff instruction.
 * @author rwtodd
 */
public class IfAndInstruction implements Instruction {
    private final Instruction conditions, truePath, falsePath;
    
    public IfAndInstruction(final Instruction conditions, final Instruction truePath, final Instruction falsePath) {
        this.conditions = conditions;
        this.truePath = truePath;
        this.falsePath = falsePath;
    }

    @Override
    public int getLength() {
        // For plain IF-THEN:
        // 0xFF (conditions) 0xFF (JNZ JNZ) (truePath) 
        // For IF-THEN-ELSE:
        // 0xFF (conditions) 0xFF (JNZ JNZ) (truePath) (0xFE TGT TGT) (falsePath) 
        return 4 +
                conditions.getLength() + 
                truePath.getLength() + 
                ((falsePath != null) ? (3+falsePath.getLength()) : 0);
    }

    @Override
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation) {
        pw.printf("%04X: %sIF-AND(\n", baseLocation++, indentation);
        var indentFurther = indentation + "    ";
        conditions.printTo(pw, script, resLoader, baseLocation, indentFurther);
        baseLocation += conditions.getLength();
        pw.printf("%04X: %s) {\n", baseLocation, indentation);
        baseLocation += 3;
        truePath.printTo(pw, script, resLoader, baseLocation, indentFurther);
        baseLocation += truePath.getLength();
        if(falsePath != null) {
            pw.printf("%04X: %s} else {\n", baseLocation, indentation);
            baseLocation += 3;
            falsePath.printTo(pw, script, resLoader, baseLocation, indentFurther);
            pw.printf("      %s}\n", indentation); // no address at end of ELSE
        } else {
            pw.printf("      %s}\n", indentation); // no address at end of bare THEN
        }
    }
}

