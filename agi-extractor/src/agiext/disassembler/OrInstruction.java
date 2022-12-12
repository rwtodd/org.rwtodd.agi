package agiext.disassembler;

import java.io.PrintWriter;

/**
 * Represents an OR-ed series of tests.
 * @author rwtodd
 */
public class OrInstruction implements Instruction {

    private final Instruction tests;

    public OrInstruction(Instruction tests) {
        this.tests = tests;
    }

    @Override
    public int getLength() {
        return tests.getLength() + 2; // 2 for the 0xfe ... 0xfe
    }

    @Override
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, String indentation) {
        pw.printf("%04X: %sOR(\n", baseLocation++, indentation);
        var indentFurther = indentation + "    ";
        tests.printTo(pw, script, baseLocation, indentFurther);
        baseLocation += tests.getLength();
        pw.printf("%04X: %s)\n", baseLocation, indentation);
    }

}
