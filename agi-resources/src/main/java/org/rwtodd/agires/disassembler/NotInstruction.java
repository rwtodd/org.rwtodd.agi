package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 * A NOT as part of an if statement
 * @author rwtodd
 */
public class NotInstruction implements Instruction {
    private final Instruction wrapped;
    
    public NotInstruction(Instruction wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public int getLength() {
        return wrapped.getLength() + 1;
    }

    @Override
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation) {
        wrapped.printTo(pw, script, resLoader, baseLocation + 1, indentation + "NOT ");
    }
}
