package org.rwtodd.agi.disassembler;

import org.rwtodd.agi.resources.AGIException;

/**
 * A factory for basic bytecode instructions.
 *
 * @author rwtodd
 */
public class InstructionFactory {

    private static final Instruction[] instList
            = new Instruction[]{};
    private final double version;
    
    public InstructionFactory(double agiVersion) {
        version = agiVersion;
    }

    public Instruction decode(int bytecode) throws AGIException {
        try {
            //TODO cover version-specific exceptions
            return instList[bytecode];
        } catch (IndexOutOfBoundsException ioob) {
            throw new AGIException("Unknown bytecode: " + bytecode, ioob);
        }
    }
}
