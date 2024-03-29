package agiext.evaluator;

import org.rwtodd.agires.*;

class Instruction {
    protected int length;

    Instruction(int numArgs) {
        this.length = numArgs + 1;
    }

    /**
     * just skip the instruction by default.
     * @param vms the vm state
     */
    void eval(VMState vms) throws AgiException {
        vms.ip += length;
    }
}

/** return will push IP past the end of the byteCodes
 */
class ReturnInstruction extends Instruction {
    ReturnInstruction() {
        super(0);
    }

    @Override void eval(VMState vms) {
        vms.ip = vms.byteCode.length;
    }
}

/** Increment a variable.  increment(%v3)
 */
class IncrementInstruction extends Instruction {
    IncrementInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.variables[vms.getRelativeToIp(1)&0xff]++;
        super.eval(vms);
    }
}

/** Decrement a variable.  decrement(%v3)
 */
class DecrementInstruction extends Instruction {
    DecrementInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.variables[vms.getRelativeToIp(1)&0xff]--;
        super.eval(vms);
    }
}

/** Set a flag.  set(%f1)
 */
class SetInstruction extends Instruction {
    SetInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.getRelativeToIp(1)&0xff] = true;
        super.eval(vms);
    }
}

/** Reset a flag.  reset(%f1)
 */
class ResetInstruction extends Instruction {
    ResetInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.getRelativeToIp(1)&0xff] = false;
        super.eval(vms);
    }
}


/** Toggle a flag.  toggle(%f1)
 */
class ToggleInstruction extends Instruction {
    ToggleInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.getRelativeToIp(1)&0xff] ^= true;
        super.eval(vms);
    }
}

/** Set a flag pointed at by a variable.  set.v(%v1)
 */
class SetVInstruction extends Instruction {
    SetVInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.variables[vms.getRelativeToIp(1)&0xff]&0xff] = true;
        super.eval(vms);
    }
}

/** Reset a flag pointed at by a variable.  reset.v(%v1)
 */
class ResetVInstruction extends Instruction {
    ResetVInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.variables[vms.getRelativeToIp(1)&0xff]&0xff] = false;
        super.eval(vms);
    }
}


/** Toggle a flag pointed at by a variable.  toggle.v(%v1)
 */
class ToggleVInstruction extends Instruction {
    ToggleVInstruction() { super(1); }
    @Override void eval(VMState vms) throws AgiException {
        vms.flags[vms.variables[vms.getRelativeToIp(1)&0xff]&0xff] ^= true;
        super.eval(vms);
    }
}

/** draw will draw an animated object...
 */
class DrawInstruction extends Instruction {
    DrawInstruction() {
        super(1);
    }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        if(which >= vms.animatedObjects.length)
            throw new AgiException("AnimatedObject " + which + " does not exist.");
        // don't draw EGO
        if(which > 0) {
            vms.animatedObjects[which].drawInto(vms, vms.resLoader);
        }
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the view for an object. {@code set.view(%o4, 5)}.
 */
class SetViewInstruction extends Instruction {
    SetViewInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int vnum = vms.getRelativeToIp(2) &0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.view");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setView(vnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the view for an object to a variable value. {@code set.view(%o4, %v5)}.
 */
class SetViewVInstruction extends Instruction {
    SetViewVInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int vnum = vms.variables[vms.getRelativeToIp(2) & 0xff] & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.view.v");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setView(vnum);
        super.eval(vms); // skip to next instruction
    }
}


/**
 * Sets the loop for an object. {@code set.loop(%o4, 5)}.
 */
class SetLoopInstruction extends Instruction {
    SetLoopInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int lnum = vms.getRelativeToIp(2) & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.loop");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setLoop(lnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the loop for an object to a variable value. {@code set.loop.v(%o4, %v5)}.
 */
class SetLoopVInstruction extends Instruction {
    SetLoopVInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int lnum = vms.variables[vms.getRelativeToIp(2) & 0xff] & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.loop.v");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setLoop(lnum);
        super.eval(vms); // skip to next instruction
    }
}


/**
 * Sets the cell for an object. {@code set.cell(%o4, 5)}.
 */
class SetCellInstruction extends Instruction {
    SetCellInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int cnum = vms.getRelativeToIp(2) & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.cell");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setCell(cnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the cell for an object to a variable value. {@code set.cell.v(%o4, %v5)}.
 */
class SetCellVInstruction extends Instruction {
    SetCellVInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int cnum = vms.variables[vms.getRelativeToIp(2) & 0xff] & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.cell.v");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setCell(cnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the priority for an object. {@code set.priority(%o4, 5)}.
 */
class SetPriInstruction extends Instruction {
    SetPriInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int pnum = vms.getRelativeToIp(2) & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.pri");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setPriority(pnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the priority for an object to a variable value. {@code set.priority.v(%o4, %v5)}.
 */
class SetPriVInstruction extends Instruction {
    SetPriVInstruction() { super(2); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int pnum = vms.variables[vms.getRelativeToIp(2) & 0xff] & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.pri.v");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setPriority(pnum);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the position for an object. {@code position(%o4, 21, 134)}.
 */
class PositionInstruction extends Instruction {
    PositionInstruction() { super(3); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int px = vms.getRelativeToIp(2) & 0xff;
        final int py = vms.getRelativeToIp(3) & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for set.cell");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setPosition(px,py);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Sets the position of an object to a variable value. {@code position.v(%o4, %v5, %v6)}.
 */
class PositionVInstruction extends Instruction {
    PositionVInstruction() { super(3); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1);
        final int px = vms.variables[vms.getRelativeToIp(2) & 0xff] & 0xff;
        final int py = vms.variables[vms.getRelativeToIp(3) & 0xff] & 0xff;
        if(which < 0 || which > vms.animatedObjects.length) {
            throw new AgiException("Bad object for position.v");
        }
        vms.animatedObjects[which] = vms.animatedObjects[which].setPosition(px, py);
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Adds a view cell to the picture, without using an animated slot.
 * {@code add.to.pic(view, loop, cell, x, y, pri, boxpri)}.
 */
class AddToPicInstruction extends Instruction {
    AddToPicInstruction() { super(7); }
    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1)&0xff;
        final int loopNum = vms.getRelativeToIp(2)&0xff;
        final int cellNum = vms.getRelativeToIp(3)&0xff;
        final int bx = vms.getRelativeToIp(4)&0xff;
        final int by = vms.getRelativeToIp(5)&0xff;
        final int pri = vms.getRelativeToIp(6)&0xff;
        System.err.printf(" - Asked to add.to.pic View %d Loop %d cell %d at %d,%c with Priority %d\n",
                which, loopNum, cellNum, bx, by, pri);
        if(which == 0 || (bx == 0 && by == 0)) return; // don't draw EGO or at 0,0 ... probably an error

        /* for this, we don't care about arg 7, as I think that's about control lines */
        final var v = vms.resLoader.loadView(which);
        if(loopNum < v.loops().size() && cellNum < v.loops().get(loopNum).cells().size()) {
            vms.drawInto(v.loops().get(loopNum).cells().get(cellNum),bx, by, pri);
        } else {
            System.err.println("add.to.pic asked for out-of-bounds view-loop-cell.");
        }
        super.eval(vms);
    }
}

/**
 * Adds a view cell to the picture, without using an animated slot.
 * {@code add.to.pic(view, loop, cell, x, y, pri, boxpri)}.
 */
class AddToPicVInstruction extends Instruction {
    AddToPicVInstruction() { super(7); }
    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.variables[vms.getRelativeToIp(1)&0xff]&0xff;
        final int loopNum = vms.variables[vms.getRelativeToIp(2)&0xff]&0xff;
        final int cellNum = vms.variables[vms.getRelativeToIp(3)&0xff]&0xff;
        final int bx = vms.variables[vms.getRelativeToIp(4)&0xff]&0xff;
        final int by = vms.variables[vms.getRelativeToIp(5)&0xff]&0xff;
        final int pri = vms.variables[vms.getRelativeToIp(6)&0xff]&0xff;
        System.err.printf(" - Asked to add.to.pic.v View %d Loop %d cell %d at %d,%c with Priority %d\n",
                which, loopNum, cellNum, bx, by, pri);
        if(which == 0 || (bx == 0 && by == 0)) return; // don't draw EGO or at 0,0 ... probably an error

        /* for this, we don't care about 7, as I think that's about control lines */
        final var v = vms.resLoader.loadView(which);
        if(loopNum < v.loops().size() && cellNum < v.loops().get(loopNum).cells().size()) {
            vms.drawInto(v.loops().get(loopNum).cells().get(cellNum),bx, by, pri);
        } else {
            System.err.println("add.to.pic asked for out-of-bounds view-loop-cell.");
        }
        super.eval(vms);
    }
}

class RandomInstruction extends Instruction {
    RandomInstruction() { super(3); }
    @Override void eval(VMState vms) throws AgiException {
        final int lower = vms.getRelativeToIp(1)&0xff;
        final int upper = vms.getRelativeToIp(2)&0xff;
        vms.variables[vms.getRelativeToIp(3)&0xff] = (byte)(vms.rng.nextInt(lower, upper+1));
        super.eval(vms);
    }
}

/**
 * Calls a nested logic script. {@code call(103)}.
 */
class CallInstruction extends Instruction {
    CallInstruction() { super(1); }

    @Override void eval(VMState vms) throws AgiException {
        vms.loadAndRun(vms.getRelativeToIp(1)&0xff);
        super.eval(vms);
    }
}

/**
 * Calls a nested logic script designated by a variable. {@code call.v(%v12)}.
 */
class CallVInstruction extends Instruction {
    CallVInstruction() { super(1); }

    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.variables[vms.getRelativeToIp(1) & 0xff] & 0xff;
        // skip it if the variable was zero...
        if(which != 0)  { vms.loadAndRun(which); }
        super.eval(vms);
    }
}

/**
 * Assign a value to a variable. {@code assignn(%v2, 32)}.
 */
class AssignInstruction extends Instruction {
    AssignInstruction() { super(2); }
    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1) & 0xff;
        final byte val = vms.getRelativeToIp(2);
        vms.variables[which] = val;
        super.eval(vms); // skip to next instruction
    }
}

/**
 * Assign a variable's contents to a variable. {@code assignv(%v2, %v3)}.
 */
class AssignVInstruction extends Instruction {
    AssignVInstruction() { super(2); }
    @Override void eval(VMState vms) throws AgiException {
        final int which = vms.getRelativeToIp(1) & 0xff;
        final byte val = vms.variables[vms.getRelativeToIp(2) & 0xff];
        vms.variables[which] = val;
        super.eval(vms); // skip to next instruction
    }
}

/**
 * GOTO instruction is 3 bytes... a 16-bit signed little-endian offset.
 */
class GotoInstruction extends Instruction {
    GotoInstruction() { super(2); /* len 3 */ }

    @Override void eval(VMState vms) throws AgiException {
        // signed 16 bit le number...
        int tgt = (short) ((vms.getRelativeToIp(1)&0xff) | ((vms.getRelativeToIp(2)&0xff) << 8));
        vms.ip += tgt;
        super.eval(vms); // jump 3 to complete the jump
    }
}

class IfAndInstruction extends Instruction {
    IfAndInstruction() { super(0); /* dummy value... variable length */ }

    /**
     * read a test
     * @param vms the VM state.
     * @return the boolean result of the test
     * @throws AgiException if an unexpected byte is encountered
     */
    private boolean readTestSection(VMState vms) throws AgiException {
        final int curVal = vms.getRelativeToIp(0)&0xff;
        return switch(curVal) {
            case 0x01 -> { // equaln(%v1, 20)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.getRelativeToIp(2);
                vms.ip += 3;
                yield v1 == v2;
            }
            case 0x02 -> { // equalv(%v1, %v2)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.variables[vms.getRelativeToIp(2)&0xff];
                vms.ip += 3;
                yield v1 == v2;
            }
            case 0x03 -> { // lessn(%v1, 20)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.getRelativeToIp(2);
                vms.ip += 3;
                yield v1 < v2;
            }
            case 0x04 -> { // lessv(%v1, %v2)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.variables[vms.getRelativeToIp(2)&0xff];
                vms.ip += 3;
                yield v1 < v2;
            }
            case 0x05 -> { // greatern(%v1, 20)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.getRelativeToIp(2);
                vms.ip += 3;
                yield v1 > v2;
            }
            case 0x06 -> { // greaterv(%v1, %v2)
                final byte v1 = vms.variables[vms.getRelativeToIp(1)&0xff];
                final byte v2 = vms.variables[vms.getRelativeToIp(2)&0xff];
                vms.ip += 3;
                yield v1 > v2;
            }
            case 0x07 -> { // isset(%f20)
                final boolean v1 = vms.flags[vms.getRelativeToIp(1)&0xff];
                vms.ip += 2;
                yield v1;
            }
            case 0x08 -> { // issetv(%v1)
                final boolean v1 = vms.flags[vms.variables[vms.getRelativeToIp(1)&0xff]&0xff];
                vms.ip += 2;
                yield v1;
            }
            case 0x09, 0x0C -> {  // 0x09 has(%i12), 0x0B controller(%c2)
                vms.ip += 2;
                yield false;
            }
            case 0x0A -> { // 0x0A obj.in.room(%i3,%v21)
                final int obj = vms.getRelativeToIp(1)&0xff;
                final int rmno = vms.variables[vms.getRelativeToIp(2)&0xff]&0xff;
                final var objs = vms.resLoader.getInitialGameObjects();
                vms.ip += 3;
                yield obj < objs.size() && objs.get(obj).startingRoom() == rmno;
            }
            case 0x0B, 0x10, 0x11, 0x12 -> {
                //0x0B posn(%o12, 1,2,3,4) 0x10 obj.in.box 0x11 center.posn 0x12 right.posn
                vms.ip += 6;
                yield false;
            }
            case 0x0D -> { // have.key() needs to be true to avoid infinite busy-loops in scripts
                ++vms.ip;
                yield true;
            }
            case 0x0E -> { // SAID  0x0E C (2*C-bytes)
                vms.ip += (vms.getRelativeToIp(1)&0xff)*2 + 2;
                yield false;
            }
            case 0x0F -> {   // 0x0F compare.strings(%s1, %s2)
                vms.ip += 3;
                yield false;
            }
            case 0xFC -> {
                boolean answer = false;
                ++vms.ip;
                while( (vms.getRelativeToIp(0)&0xff) != 0xFC ) {
                    answer = readTestSection(vms) || answer;
                }
                ++vms.ip; /* get past the closing 0xFC */
                yield answer;
            }
            case 0xFD -> { // NOT
                ++vms.ip;
                yield !readTestSection(vms);
            }
            default -> throw new AgiException("Unknown test command " + curVal);
        };
    }

    @Override void eval(VMState vms) throws AgiException {
        ++vms.ip; // get past the 0xff
        boolean result = true;
        // while we aren't on 0xff (aka byte -1)
        while(vms.getRelativeToIp(0) != -1) {
            result = readTestSection(vms) && result;
        }
        ++vms.ip; // get past the closing 0xff
        // now we should have a 16bit LE relative jump to get to the ELSE
        if(result) {
            // it's true so just adjust past the jump target...
            vms.ip += 2;
        } else {
            // it's false, so we need to jump to the ELSE
            // 16 bit le number...
            int target = ((vms.getRelativeToIp(0)&0xff) | ((vms.getRelativeToIp(1)&0xff) << 8));
            vms.ip += target + 2;
        }
    }
}
