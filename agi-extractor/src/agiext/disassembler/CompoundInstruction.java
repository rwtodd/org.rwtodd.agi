package agiext.disassembler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rwtodd
 */
public class CompoundInstruction implements Instruction {
    protected final List<Instruction> contents;
    
    @Override
    public int getLength() {
        return contents.stream().mapToInt(Instruction::getLength).sum();
    }

    @Override
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, String indentation) {
        for(final var i: contents) {
            i.printTo(pw, script, baseLocation, indentation);
            baseLocation += i.getLength();
        }
    }

    public CompoundInstruction() {
        this.contents = new ArrayList<>();
    }
    
    public void add(final Instruction i) {
        contents.add(i);
    }
    
    /**
     * Reduces a single-instruction CompoundInstruction to just the contents. There's
     * no sense in carrying around this container if there's only one instruction in it.
     * @return either the whole CompoundInstruction or a single Instruction.
     */
    public Instruction compress() {
        return (contents.size() == 1) ? contents.get(0) : this;
    }
}
