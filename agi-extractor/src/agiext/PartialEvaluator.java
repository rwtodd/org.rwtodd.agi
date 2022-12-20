package agiext;

import org.rwtodd.agires.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Represent the state of a partial evaluation.
 */
class VMState {
    /** Instruction pointer */
    int ip;

    /** flags */
    boolean[] flags;

    /** variables */
    byte[] variables;

    final AgiResourceLoader resLoader;

    final AgiPic agiPic;

    byte[] byteCode;

    Instruction[] instructionSet;

    /* let's track the state of the animated objects here */
    final AnimatedObject[] animatedObjects;

    /** random state for the random(x,x,x) instruction */
    final Random rng;

    /** a consumer of images (used for outputting time-lapse frames */
    final Consumer<AgiPic.Image> observer;

    VMState(AgiResourceLoader resLoader, AgiPic pic, Consumer<AgiPic.Image> observer) {
        this.resLoader = resLoader;
        agiPic = pic;
        flags = new boolean[256]; Arrays.fill(flags,false);
        flags[5] = true; /* we are in a new room */
        variables = new byte[256]; Arrays.fill(variables, (byte)0);
        ip = 0;
        byteCode = null;
        instructionSet = generateInstructionSet(resLoader.getMetaData().getVersion());

        // create empty animated objects
        animatedObjects = new AnimatedObject[resLoader.getMaxAnimatedObjects()];
        for(int i = 0; i < animatedObjects.length; ++i) {
            animatedObjects[i] = new AnimatedObject();
        }

        rng = new Random();

        // if we have an ovserver, set it..
        this.observer = observer;
    }

    static Instruction[] generateInstructionSet(double version) {
        Instruction[] ans = Arrays.copyOf(baseInstructionSet, 256);
        // a few codes have different numbers of arguments based on the AGI version...
        ans[134] = new Instruction((version < 2.090) ? 0 : 1); // quit
        ans[151] = new Instruction((version < 2.401) ? 3 : 4); //print.at
        ans[152] = new Instruction((version < 2.401) ? 3 : 4); //print.at.v
        ans[176] = new Instruction((version == 3.002086) ? 1 : 0); // unknown176
        // now set up the IF-AND and GOTO instructions...
        ans[254] = new GotoInstruction(); // goto...
        ans[255] = new IfAndInstruction(); // IF-AND...
        return ans;
    }

    static final Instruction[] baseInstructionSet = new Instruction[]{
            // reminder about the argument types...
            //  ARG_UNK = 0;
            //  ARG_NUM = 1;
            //  ARG_OBJ = 2;
            //  ARG_CTL = 3;
            //  ARG_VAR = 4;
            //  ARG_INV = 5;
            //  ARG_TOK = 6;
            //  ARG_FLG = 7;
            //  ARG_STR = 8;
            //  ARG_MSG = 9;
            //  ARG_WRD = 10;
            new ReturnInstruction(),
            new IncrementInstruction(), // new Instruction( 1), // "increment", , 0x4
            new DecrementInstruction(), // new Instruction( 1), // "decrement", , 0x4
            new AssignInstruction(), // new Instruction( 2), // "assignn", , 0x14
            new AssignVInstruction(), // new Instruction( 2), // "assignv", , 0x44
            new Instruction( 2), // 5 // "addn", , 0x14
            new Instruction( 2), // "addv", , 0x44
            new Instruction( 2), // "subn", , 0x14
            new Instruction( 2), // "subv", , 0x44
            new Instruction( 2), // "lindirectv", , 0x44
            new Instruction( 2), // 10 // "rindirect", , 0x44
            new Instruction( 2), // "lindirectn", , 0x14
            new SetInstruction(), // "set", , 0x7
            new ResetInstruction(), // new Instruction( 1), // "reset", , 0x7
            new ToggleInstruction(), // new Instruction( 1), // "toggle", , 0x7
            new SetVInstruction(), // new Instruction( 1), // 15 // "set.v", , 0x4
            new ResetVInstruction(), // new Instruction( 1), // "reset.v", , 0x4
            new ToggleVInstruction(), // new Instruction( 1), // "toggle.v", , 0x4
            new Instruction( 1), // "new.room", , 0x1
            new Instruction( 1), // "new.room.v", , 0x4
            new Instruction( 1), // 20 // "load.logics", , 0x1
            new Instruction( 1), // "load.logics.v", , 0x4
            new CallInstruction(), // new Instruction( 1), // "call", , 0x1
            new CallVInstruction(), // new Instruction( 1), // "call.v", , 0x4
            new Instruction( 1), // "load.pic", , 0x4
            new Instruction( 1), // 25 // "draw.pic", , 0x4
            new Instruction( 0), // "show.pic", , 0x0
            new Instruction( 1), // "discard.pic", , 0x4
            new Instruction( 1), // "overlay.pic", , 0x4
            new Instruction( 0), // "show.pri.screen", , 0x0
            new Instruction( 1), // 30 // "load.view", , 0x1
            new Instruction( 1), // "load.view.v", , 0x4
            new Instruction( 1), // "discard.view", , 0x1
            new Instruction( 1), // "animate.obj", , 0x2
            new Instruction( 0), // "unanimate.all", , 0x0
            new DrawInstruction(), // 35 // "draw", , 0x2
            new Instruction( 1), // "erase", , 0x2
            new PositionInstruction(), // new Instruction( 3), // "position", , 0x112
            new PositionVInstruction(), // new Instruction( 3), // "position.v", , 0x442
            new Instruction( 3), // "get.posn", , 0x442
            new Instruction( 3), // 40 // "reposition", , 0x442
            new SetViewInstruction(), // new Instruction( 2), // "set.view", , 0x12
            new SetViewVInstruction(), // new Instruction( 2), // "set.view.v", , 0x42
            new SetLoopInstruction(), // new Instruction( 2), // "set.loop", , 0x12
            new SetLoopVInstruction(), // new Instruction( 2), // "set.loop.v", , 0x42
            new Instruction( 1), // 45 // "fix.loop", , 0x2
            new Instruction( 1), // "release.loop", , 0x2
            new SetCellInstruction(), // new Instruction( 2), // "set.cel", , 0x12
            new SetCellVInstruction(), // new Instruction( 2), // "set.cel.v", , 0x42
            new Instruction( 2), // "last.cel", , 0x42
            new Instruction( 2), // 50 // "current.cel", , 0x42
            new Instruction( 2), // "current.loop", , 0x42
            new Instruction( 2), // "current.view", , 0x42
            new Instruction( 2), // "number.of.loops", , 0x42
            new SetPriInstruction(), // new Instruction( 2), // "set.priority", , 0x12
            new SetPriVInstruction(), // new Instruction( 2), // 55 // "set.priority.v", , 0x42
            new Instruction( 1), // "release.priority", , 0x2
            new Instruction( 2), // "get.priority", , 0x42
            new Instruction( 1), // "stop.update", , 0x2
            new Instruction( 1), // "start.update", , 0x2
            new Instruction( 1), // 60 // "force.update", , 0x2
            new Instruction( 1), // "ignore.horizon", , 0x2
            new Instruction( 1), // "observe.horizon", , 0x2
            new Instruction( 1), // "set.horizon", , 0x1
            new Instruction( 1), // "object.on.water", , 0x2
            new Instruction( 1), // 65 // "object.on.land", , 0x2
            new Instruction( 1), // "object.on.anything", , 0x2
            new Instruction( 1), // "ignore.objs", , 0x2
            new Instruction( 1), // "observe.objs", , 0x2
            new Instruction( 3), // "distance", , 0x422
            new Instruction( 1), // 70 // "stop.cycling", , 0x2
            new Instruction( 1), // "start.cycling", , 0x2
            new Instruction( 1), // "normal.cycle", , 0x2
            new Instruction( 2), // "end.of.loop", , 0x72
            new Instruction( 1), // "reverse.cycle", , 0x2
            new Instruction( 2), // 75 // "reverse.loop", , 0x72
            new Instruction( 2), // "cycle.time", , 0x42
            new Instruction( 1), // "stop.motion", , 0x2
            new Instruction( 1), // "start.motion", , 0x2
            new Instruction( 2), // "step.size", , 0x42
            new Instruction( 2), // 80 // "step.time", , 0x42
            new Instruction( 5), // "move.obj", , 0x71112
            new Instruction( 5), // "move.obj.v", , 0x71442
            new Instruction( 3), // "follow.ego", , 0x712
            new Instruction( 1), // "wander", , 0x2
            new Instruction( 1), // 85 // "normal.motion", , 0x2
            new Instruction( 2), // "set.dir", , 0x42
            new Instruction( 2), // "get.dir", , 0x42
            new Instruction( 1), // "ignore.blocks", , 0x2
            new Instruction( 1), // "observe.blocks", , 0x2
            new Instruction( 4), // 90 // "block", , 0x1111
            new Instruction( 0), // "unblock", , 0x0
            new Instruction( 1), // "get", , 0x5
            new Instruction( 1), // "get.v", , 0x4
            new Instruction( 1), // "drop", , 0x5
            new Instruction( 2), // 95 // "put", , 0x45
            new Instruction( 2), // "put.v", , 0x44
            new Instruction( 2), // "get.room.v", , 0x44
            new Instruction( 1), // "load.sound", , 0x1
            new Instruction( 2), // "sound", , 0x71
            new Instruction( 0), // 100 // "stop.sound", , 0x0
            new Instruction( 1), // "print", , 0x9
            new Instruction( 1), // "print.v", , 0x4
            new Instruction( 3), // "display", , 0x911
            new Instruction( 3), // "display.v", , 0x444
            new Instruction( 3), // 105 // "clear.lines", , 0x111
            new Instruction( 0), // "text.screen", , 0x0
            new Instruction( 0), // "graphics", , 0x0
            new Instruction( 1), // "set.cursor.char", , 0x9
            new Instruction( 2), // "set.text.attribute", , 0x11
            new Instruction( 1), // 110 // "shake.screen", , 0x1
            new Instruction( 3), // "configure.screen", , 0x111
            new Instruction( 0), // "status.line.on", , 0x0
            new Instruction( 0), // "status.line.off", , 0x0
            new Instruction( 2), // "set.string", , 0x98
            new Instruction( 5), // 115 // "get.string", , 0x11198
            new Instruction( 2), // "word.to.string", , 0x8A
            new Instruction( 1), // "parse", , 0x8
            new Instruction( 2), // "get.num", , 0x49
            new Instruction( 0), // "prevent.input", , 0x0
            new Instruction( 0), // 120 // "accept.input", , 0x0
            new Instruction( 3), // "set.key", , 0x311
            new AddToPicInstruction(), // new Instruction( 7), // "add.to.pic", , 0x1111111
            new AddToPicVInstruction(), // new Instruction( 7), // "add.to.pic.v", , 0x4444444
            new Instruction( 0), // "status", , 0x0
            new Instruction( 0), // 125 // "save.game", , 0x0
            new Instruction( 0), // "restore.game", , 0x0
            new Instruction( 0), // "init.disk", , 0x0
            new Instruction( 0), // "restart.game", , 0x0
            new Instruction( 1), // "show.obj", , 0x1
            new RandomInstruction(), // new Instruction( 3), // 130 // "random", , 0x411
            new Instruction( 0), // "program.control", , 0x0
            new Instruction( 0), // "player.control", , 0x0
            new Instruction( 1), // "obj.status.v", , 0x4
            new Instruction( 255), // "quit", , 0x1
            new Instruction( 0), // 135 // "show.mem", , 0x0
            new Instruction( 0), // "pause", , 0x0
            new Instruction( 0), // "echo.line", , 0x0
            new Instruction( 0), // "cancel.line", , 0x0
            new Instruction( 0), // "init.joy", , 0x0
            new Instruction( 0), // 140 // "toggle.monitor", , 0x0
            new Instruction( 0), // "version", , 0x0
            new Instruction( 1), // "script.size", , 0x1
            new Instruction( 1), // "set.game.id", , 0x9
            new Instruction( 1), // "log", , 0x9
            new Instruction( 0), // 145 // "set.scan.start", , 0x0
            new Instruction( 0), // "reset.scan.start", , 0x0
            new Instruction( 3), // "reposition.to", , 0x112
            new Instruction( 3), // "reposition.to.v", , 0x442
            new Instruction( 0), // "trace.on", , 0x0
            new Instruction( 3), // 150 // "trace.info", , 0x111
            new Instruction( 255), // "print.at", , 0x1119
            new Instruction( 255), // "print.at.v", , 0x4449
            new Instruction( 1), // "discard.view.v", , 0x4
            new Instruction( 5), // "clear.text.rect", , 0x11111
            new Instruction( 2), // 155 // "set.upper.left", , 0x00
            new Instruction( 1), // "set.menu", , 0x9
            new Instruction( 2), // "set.menu.item", , 0x39
            new Instruction( 0), // "submit.menu", , 0x0
            new Instruction( 1), // "enable.item", , 0x3
            new Instruction( 1), // 160 // "disable.item", , 0x3
            new Instruction( 0), // "menu.input", , 0x0
            new Instruction( 1), // "show.obj.v", , 0x4
            new Instruction( 0), // "open.dialogue", , 0x0
            new Instruction( 0), // "close.dialogue", , 0x0
            new Instruction( 2), // 165 // "mul.n", , 0x14
            new Instruction( 2), // "mul.v", , 0x44
            new Instruction( 2), // "div.n", , 0x14
            new Instruction( 2), // "div.v", , 0x44
            new Instruction( 0), // "close.window", , 0x0
            new Instruction( 1), // 170 // "unknown170", , 0x0
            new Instruction( 0), // "unknown171", , 0x0
            new Instruction( 0), // "unknown172", , 0x0
            new Instruction( 0), // "unknown173", , 0x0
            new Instruction( 1), // "unknown174", , 0x0
            new Instruction( 1), // 175 // "unknown175", , 0x0
            new Instruction( 255), // 176 // "unknown176", , 0x0
            new Instruction( 1), // "unknown177", , 0x0
            new Instruction( 0), // "unknown178", , 0x0
            new Instruction( 4), // "unknown179", , 0x0000
            new Instruction( 2), // 180 // "unknown180", , 0x00
            new Instruction( 0)    // "unknown181", , 0x0
    };

    private void runScript() throws AgiException {
        while(ip >= 0 && ip < byteCode.length) {
            Instruction ins = instructionSet[byteCode[ip]&0xff];
            if(ins == null) {
                throw new AgiException("Unknown instruction " + byteCode[ip] + " while partially evaluating.");
            }
            final int oldIp = ip;
            ins.eval(this);
            if(ip == oldIp) {
                throw new AgiException("Infinite loop at IP " + ip);
            }
        }
    }

    void loadAndRun(int resNumber) {
        try {
            System.err.println("load and run " + resNumber); // FIXME
            var log = resLoader.loadLogic(resNumber);
            /* save IP/byteCode state ... */
            /* if byteCode is null, this is the first room, and we should set %v0 to the number */
            if(byteCode == null) {
                variables[0] = (byte)resNumber;
            }
            byte[] savedCodes = byteCode;
            int savedIp = ip;
            byteCode = log.getBytecodes();
            ip = 0;
            runScript();
            /* and restore after */
            ip = savedIp;
            byteCode = savedCodes;
        } catch(ResourceNotPresentException rnp) {
            /* maybe this pic doesn't have a script... it happens */
            System.err.println("Script or view not present when processing this pic " + resNumber);
        } catch(Exception e) {
            /* we won't bother trying to recover from exceptions when trying to partial eval a script. */
            System.err.println("Exception while partially-evaluating logic script: " + e.getMessage());
        }
    }

    /** set the priority of an object based on its base-Y location */
    static int calculatePriorityByPosition(int by) {
        int priority = 3;
        if (by <= 47) {
            priority = 4;
        } else if (by <= 59) {
            priority = 5;
        } else if (by <= 71) {
            priority = 6;
        } else if (by <= 83) {
            priority = 7;
        } else if (by <= 95) {
            priority = 8;
        } else if (by <= 107) {
            priority = 9;
        } else if (by <= 119) {
            priority = 10;
        } else if (by <= 131) {
            priority = 11;
        } else if (by <= 143) {
            priority = 12;
        } else if (by <= 155) {
            priority = 13;
        } else {
            priority = 14;
        }
        return priority;
    }

    /** Utility function to draw a cell into a pic.
     * It is used by AnimatedObject and also the functions that add a view to a pic.
     * @param cell the cell to draw
     * @param bx the base x coordinate
     * @param by the base y coordinate
     * @param priority the priority of the cell
     */
     void drawInto(AgiView.Cell cell, int bx, int by, int priority) {
        final var transparent = cell.getTransparentColor();
        final var cwidth = cell.getWidth();
        final var cheight = cell.getHeight();
        final var cbytes = cell.getPixels();
        final var pbytes = agiPic.picture().pixels();

        System.err.printf(" - Adding %d x %d cell to pic at base (%d, %d).\n", cwidth, cheight, bx, by);

        // if the priority is 0, set it according to by...
         if(priority == 0) {
            priority = calculatePriorityByPosition(by);
         }

        // ok, draw the sucker, tracking indexes for performance...
        int cellIdx = 0;

         // don't let it go off the right side or bottom side....
         if(bx + cell.getWidth() >= AgiPic.AGI_PIC_WIDTH) {
             bx = AgiPic.AGI_PIC_WIDTH - cell.getWidth();
         }
         if(by >= AgiPic.AGI_PIC_HEIGHT) {
             by = AgiPic.AGI_PIC_HEIGHT - 1;
         }

         // don't let the y value go negative... max of it and zero...
        int picBaseIdx = Math.max(by - cheight + 1, 0)*AgiPic.AGI_PIC_WIDTH + bx;

        for(int countY = 0; countY < cheight; ++countY) {
            int picIdx = picBaseIdx;
            for(int countX = 0; countX < cwidth; ++countX) {
                final byte curVal = cbytes[cellIdx];
                if(curVal != transparent && priority >= agiPic.priorityAtIndex(picIdx)) {
                    pbytes[picIdx] = curVal;
                }
                ++cellIdx;
                ++picIdx;
            }
            picBaseIdx += AgiPic.AGI_PIC_WIDTH;
        }
        if(observer != null) observer.accept(agiPic.picture());
    }

    public byte getRelativeToIp(int delta) throws AgiException {
        if ((ip+delta) >= byteCode.length) throw new AgiException("Instruction tried to read past the end of bytecode!");
        return byteCode[ip + delta];
    }
}

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
            case 0x0D -> { // have.key()
                ++vms.ip;
                yield false;
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

/**
 * Animated Objects have certain state that the VM maintains.
 */
record AnimatedObject(int view, int loop, int cell, int baseX, int baseY, int priority) {

    AnimatedObject() {
        this(-1,0,0,0,0,0);
    }

    // adjust an existing AnimatedObject
    AnimatedObject setView(int view) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setLoop(int loop) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setCell(int cell) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setPosition(int x, int y) { return new AnimatedObject(view, loop, cell, x, y, priority); }

    AnimatedObject setPriority(int priority) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    void drawInto(VMState vms, AgiResourceLoader resLoader) throws AgiException {
        AgiView v = resLoader.loadView(view);
        if(loop >= v.loops().size()) {
            throw new AgiException("Bad loop number to load!");
        }
        final var l = v.loops().get(loop);
        if(cell >= l.cells().size()) {
            throw new AgiException("Bad cell number to load!");
        }
        final var c = l.cells().get(cell);
        vms.drawInto(c, baseX, baseY, priority);
    }
}
