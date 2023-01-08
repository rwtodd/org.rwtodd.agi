package agiext.evaluator;

import org.rwtodd.agires.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Represent the state of a partial evaluation.
 */
public class VMState {
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

    public VMState(AgiResourceLoader resLoader, AgiPic pic, Consumer<AgiPic.Image> observer) {
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

        // if we have an observer, set it..
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

    /**
     * Loads and runs a LOGIC script to add VIEWS to the PIC.
     * @param resNumber the LOGIC resource number
     */
    public void loadAndRun(int resNumber) {
        try {
            System.err.println(" - Calling script " + resNumber);
            var log = resLoader.loadLogic(resNumber);
            /* save IP/byteCode state ... */
            /* if byteCode is null, this is the first room, and we should set %v0/%v1 to the room numbers */
            if(byteCode == null) {
                variables[0] = (byte)resNumber;
                variables[1] = (byte)(resNumber - 1); /* assume we come from the previous room */
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
        final var rbytes = agiPic.priority().pixels();

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
                    rbytes[picIdx] = (byte)priority;
                }
                ++cellIdx;
                ++picIdx;
            }
            picBaseIdx += AgiPic.AGI_PIC_WIDTH;
        }
        if(observer != null) observer.accept(agiPic.picture());
    }

    /**
     * Gets a byte from the script's bytecode, relative to the current instruction pointer.
     * @param delta the offset, relative to the instruction pointer, where the desired byte resides.
     * @return the requested LOGIC byte
     * @throws AgiException If the requested byte is beyond the end of the LOGIC bytes.
     */
     byte getRelativeToIp(int delta) throws AgiException {
        if ((ip+delta) >= byteCode.length) throw new AgiException("Instruction tried to read past the end of bytecode!");
        return byteCode[ip + delta];
    }
}

