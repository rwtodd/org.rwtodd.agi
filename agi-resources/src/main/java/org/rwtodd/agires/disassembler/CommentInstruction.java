package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 * An pseudo-instruction which puts a comment into the disassembly...
 * @author rwtodd
 */
public class CommentInstruction implements Instruction {
    private final String comment;
    
    public CommentInstruction(final String cmt) {
        comment = cmt;
    }

    @Override
    public int getLength() { return 0;   }

    @Override
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, String indentation) {
        pw.printf("      %s[ %s\n", indentation, comment);
    }
    
}
