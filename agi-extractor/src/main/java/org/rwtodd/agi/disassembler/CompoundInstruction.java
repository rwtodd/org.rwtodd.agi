/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.disassembler;

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
     * Get the number of instructions in this compound instruction.
     * @return the number of instructions.
     */
    public int getInstructionCount() {
        return contents.size();
    }
    
    /**
     * Get the component instruction indexed by {@code i}.
     * @param i the index of the desired instruction
     * @return the component instruction.
     */
    public Instruction get(int i) {
        return contents.get(i);
    }
}
