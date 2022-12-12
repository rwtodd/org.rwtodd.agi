package agiext.disassembler;

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
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, String indentation) {
        pw.printf("      %s;; %s\n", indentation, comment);
    }
    
}
