package org.rwtodd.agi.disassembler;

import org.rwtodd.agi.resources.AGIException;

/**
 * A decoder for AGI Logic bytecode instructions.
 *
 * @author rwtodd
 */
public class InstructionDecoder {

    protected static final Instruction[] tests
            = new Instruction[]{
                new BasicInstruction("UNKNOWN", 0, 0x0), // 0
                new BasicInstruction("equaln", 2, 0x14), // 1
                new BasicInstruction("equalv", 2, 0x44),
                new BasicInstruction("lessn", 2, 0x14),
                new BasicInstruction("lessv", 2, 0x44),
                new BasicInstruction("greatern", 2, 0x14), // 5
                new BasicInstruction("greaterv", 2, 0x44), // 6
                new BasicInstruction("isset", 1, 0x7),
                new BasicInstruction("issetv", 1, 0x4),
                new BasicInstruction("has", 1, 0x5),
                new BasicInstruction("obj.in.room", 2, 0x45),
                new BasicInstruction("posn", 5, 0x11112), // 11
                new BasicInstruction("controller", 1, 0x3),
                new BasicInstruction("have.key", 0, 0x0),
                new BasicInstruction("said", 0, 0x0), // special arg-handling for "said" 14
                new BasicInstruction("compare.strings", 2, 0x88),
                new BasicInstruction("obj.in.box", 5, 0x11112), // 16
                new BasicInstruction("center.posn", 5, 0x11112),
                new BasicInstruction("right.posn", 5, 0x11112)
            };

    protected static final Instruction[] actions
            = new Instruction[]{
                new BasicInstruction("return", 0, 0x0), // 0
                new BasicInstruction("increment", 1, 0x4),
                new BasicInstruction("decrement", 1, 0x4),
                new BasicInstruction("assignn", 2, 0x14),
                new BasicInstruction("assignv", 2, 0x44),
                new BasicInstruction("addn", 2, 0x14), // 5
                new BasicInstruction("addv", 2, 0x44),
                new BasicInstruction("subn", 2, 0x14),
                new BasicInstruction("subv", 2, 0x44),
                new BasicInstruction("lindirectv", 2, 0x44),
                new BasicInstruction("rindirect", 2, 0x44), // 10
                new BasicInstruction("lindirectn", 2, 0x14),
                new BasicInstruction("set", 1, 0x7),
                new BasicInstruction("reset", 1, 0x7),
                new BasicInstruction("toggle", 1, 0x7),
                new BasicInstruction("set.v", 1, 0x4), // 15
                new BasicInstruction("reset.v", 1, 0x4),
                new BasicInstruction("toggle.v", 1, 0x4),
                new BasicInstruction("new.room", 1, 0x1),
                new BasicInstruction("new.room.v", 1, 0x4),
                new BasicInstruction("load.logics", 1, 0x1), // 20
                new BasicInstruction("load.logics.v", 1, 0x4),
                new BasicInstruction("call", 1, 0x1),
                new BasicInstruction("call.v", 1, 0x4),
                new BasicInstruction("load.pic", 1, 0x4),
                new BasicInstruction("draw.pic", 1, 0x4), // 25
                new BasicInstruction("show.pic", 0, 0x0),
                new BasicInstruction("discard.pic", 1, 0x4),
                new BasicInstruction("overlay.pic", 1, 0x4),
                new BasicInstruction("show.pri.screen", 0, 0x0),
                new BasicInstruction("load.view", 1, 0x1), // 30
                new BasicInstruction("load.view.v", 1, 0x4),
                new BasicInstruction("discard.view", 1, 0x1),
                new BasicInstruction("animate.obj", 1, 0x2),
                new BasicInstruction("unanimate.all", 0, 0x0),
                new BasicInstruction("draw", 1, 0x2), // 35
                new BasicInstruction("erase", 1, 0x2),
                new BasicInstruction("position", 3, 0x112),
                new BasicInstruction("position.v", 3, 0x442),
                new BasicInstruction("get.posn", 3, 0x442),
                new BasicInstruction("reposition", 3, 0x442), // 40
                new BasicInstruction("set.view", 2, 0x12),
                new BasicInstruction("set.view.v", 2, 0x42),
                new BasicInstruction("set.loop", 2, 0x12),
                new BasicInstruction("set.loop.v", 2, 0x42),
                new BasicInstruction("fix.loop", 1, 0x2), // 45
                new BasicInstruction("release.loop", 1, 0x2),
                new BasicInstruction("set.cel", 2, 0x12),
                new BasicInstruction("set.cel.v", 2, 0x42),
                new BasicInstruction("last.cel", 2, 0x42),
                new BasicInstruction("current.cel", 2, 0x42), // 50
                new BasicInstruction("current.loop", 2, 0x42),
                new BasicInstruction("current.view", 2, 0x42),
                new BasicInstruction("number.of.loops", 2, 0x42),
                new BasicInstruction("set.priority", 2, 0x12),
                new BasicInstruction("set.priority.v", 2, 0x42), // 55
                new BasicInstruction("release.priority", 1, 0x2),
                new BasicInstruction("get.priority", 2, 0x42),
                new BasicInstruction("stop.update", 1, 0x2),
                new BasicInstruction("start.update", 1, 0x2),
                new BasicInstruction("force.update", 1, 0x2), // 60
                new BasicInstruction("ignore.horizon", 1, 0x2),
                new BasicInstruction("observe.horizon", 1, 0x2),
                new BasicInstruction("set.horizon", 1, 0x1),
                new BasicInstruction("object.on.water", 1, 0x2),
                new BasicInstruction("object.on.land", 1, 0x2), // 65
                new BasicInstruction("object.on.anything", 1, 0x2),
                new BasicInstruction("ignore.objs", 1, 0x2),
                new BasicInstruction("observe.objs", 1, 0x2),
                new BasicInstruction("distance", 3, 0x422),
                new BasicInstruction("stop.cycling", 1, 0x2), // 70
                new BasicInstruction("start.cycling", 1, 0x2),
                new BasicInstruction("normal.cycle", 1, 0x2),
                new BasicInstruction("end.of.loop", 2, 0x72),
                new BasicInstruction("reverse.cycle", 1, 0x2),
                new BasicInstruction("reverse.loop", 2, 0x72), // 75
                new BasicInstruction("cycle.time", 2, 0x42),
                new BasicInstruction("stop.motion", 1, 0x2),
                new BasicInstruction("start.motion", 1, 0x2),
                new BasicInstruction("step.size", 2, 0x42),
                new BasicInstruction("step.time", 2, 0x42), // 80
                new BasicInstruction("move.obj", 5, 0x71112),
                new BasicInstruction("move.obj.v", 5, 0x71442),
                new BasicInstruction("follow.ego", 3, 0x712),
                new BasicInstruction("wander", 1, 0x2),
                new BasicInstruction("normal.motion", 1, 0x2), // 85
                new BasicInstruction("set.dir", 2, 0x42),
                new BasicInstruction("get.dir", 2, 0x42),
                new BasicInstruction("ignore.blocks", 1, 0x2),
                new BasicInstruction("observe.blocks", 1, 0x2),
                new BasicInstruction("block", 4, 0x1111), // 90
                new BasicInstruction("unblock", 0, 0x0),
                new BasicInstruction("get", 1, 0x5),
                new BasicInstruction("get.v", 1, 0x4),
                new BasicInstruction("drop", 1, 0x5),
                new BasicInstruction("put", 2, 0x45), // 95
                new BasicInstruction("put.v", 2, 0x44),
                new BasicInstruction("get.room.v", 2, 0x44),
                new BasicInstruction("load.sound", 1, 0x1),
                new BasicInstruction("sound", 2, 0x71),
                new BasicInstruction("stop.sound", 0, 0x0), // 100
                new BasicInstruction("print", 1, 0x9),
                new BasicInstruction("print.v", 1, 0x4),
                new BasicInstruction("display", 3, 0x911),
                new BasicInstruction("display.v", 3, 0x444),
                new BasicInstruction("clear.lines", 3, 0x111), // 105
                new BasicInstruction("text.screen", 0, 0x0),
                new BasicInstruction("graphics", 0, 0x0),
                new BasicInstruction("set.cursor.char", 1, 0x9),
                new BasicInstruction("set.text.attribute", 2, 0x11),
                new BasicInstruction("shake.screen", 1, 0x1), // 110
                new BasicInstruction("configure.screen", 3, 0x111),
                new BasicInstruction("status.line.on", 0, 0x0),
                new BasicInstruction("status.line.off", 0, 0x0),
                new BasicInstruction("set.string", 2, 0x98),
                new BasicInstruction("get.string", 5, 0x11198), // 115
                new BasicInstruction("word.to.string", 2, 0x8A),
                new BasicInstruction("parse", 1, 0x8),
                new BasicInstruction("get.num", 2, 0x49),
                new BasicInstruction("prevent.input", 0, 0x0),
                new BasicInstruction("accept.input", 0, 0x0), // 120
                new BasicInstruction("set.key", 3, 0x311),
                new BasicInstruction("add.to.pic", 7, 0x1111111),
                new BasicInstruction("add.to.pic.v", 7, 0x4444444),
                new BasicInstruction("status", 0, 0x0),
                new BasicInstruction("save.game", 0, 0x0), // 125
                new BasicInstruction("restore.game", 0, 0x0),
                new BasicInstruction("init.disk", 0, 0x0),
                new BasicInstruction("restart.game", 0, 0x0),
                new BasicInstruction("show.obj", 1, 0x1),
                new BasicInstruction("random", 3, 0x411), // 130
                new BasicInstruction("program.control", 0, 0x0),
                new BasicInstruction("player.control", 0, 0x0),
                new BasicInstruction("obj.status.v", 1, 0x4),
                new BasicInstruction("quit", 255, 0x1),
                new BasicInstruction("show.mem", 0, 0x0), // 135
                new BasicInstruction("pause", 0, 0x0),
                new BasicInstruction("echo.line", 0, 0x0),
                new BasicInstruction("cancel.line", 0, 0x0),
                new BasicInstruction("init.joy", 0, 0x0),
                new BasicInstruction("toggle.monitor", 0, 0x0), // 140
                new BasicInstruction("version", 0, 0x0),
                new BasicInstruction("script.size", 1, 0x1),
                new BasicInstruction("set.game.id", 1, 0x9),
                new BasicInstruction("log", 1, 0x9),
                new BasicInstruction("set.scan.start", 0, 0x0), // 145
                new BasicInstruction("reset.scan.start", 0, 0x0),
                new BasicInstruction("reposition.to", 3, 0x112),
                new BasicInstruction("reposition.to.v", 3, 0x442),
                new BasicInstruction("trace.on", 0, 0x0),
                new BasicInstruction("trace.info", 3, 0x111), // 150
                new BasicInstruction("print.at", 255, 0x1119),
                new BasicInstruction("print.at.v", 255, 0x4449),
                new BasicInstruction("discard.view.v", 1, 0x4),
                new BasicInstruction("clear.text.rect", 5, 0x11111),
                new BasicInstruction("set.upper.left", 2, 0x00), // 155
                new BasicInstruction("set.menu", 1, 0x9),
                new BasicInstruction("set.menu.item", 2, 0x39),
                new BasicInstruction("submit.menu", 0, 0x0),
                new BasicInstruction("enable.item", 1, 0x3),
                new BasicInstruction("disable.item", 1, 0x3), // 160
                new BasicInstruction("menu.input", 0, 0x0),
                new BasicInstruction("show.obj.v", 1, 0x4),
                new BasicInstruction("open.dialogue", 0, 0x0),
                new BasicInstruction("close.dialogue", 0, 0x0),
                new BasicInstruction("mul.n", 2, 0x14), // 165
                new BasicInstruction("mul.v", 2, 0x44),
                new BasicInstruction("div.n", 2, 0x14),
                new BasicInstruction("div.v", 2, 0x44),
                new BasicInstruction("close.window", 0, 0x0),
                new BasicInstruction("unknown170", 1, 0x0), // 170
                new BasicInstruction("unknown171", 0, 0x0),
                new BasicInstruction("unknown172", 0, 0x0),
                new BasicInstruction("unknown173", 0, 0x0),
                new BasicInstruction("unknown174", 1, 0x0),
                new BasicInstruction("unknown175", 1, 0x0), // 175
                new BasicInstruction("unknown176", 255, 0x0), // 176
                new BasicInstruction("unknown177", 1, 0x0),
                new BasicInstruction("unknown178", 0, 0x0),
                new BasicInstruction("unknown179", 4, 0x0000),
                new BasicInstruction("unknown180", 2, 0x00), // 180
                new BasicInstruction("unknown181", 0, 0x0)
            };

    protected final double version;
    protected final Instruction code134, code151, code152, code176;

    public InstructionDecoder(double agiVersion) {
        version = agiVersion;

        // a few codes have different numbers of arguments based on the AGI version...
        code134 = new BasicInstruction("quit", (version < 2.090) ? 0 : 1, 0x1);
        code151 = new BasicInstruction("print.at", (version < 2.401) ? 3 : 4, 0x1119);
        code152 = new BasicInstruction("print.at.v", (version < 2.401) ? 3 : 4, 0x4449);
        code176 = new BasicInstruction("unknown176", (version == 3.002086) ? 1 : 0, 0x0);
    }

    protected Instruction lookupTest(int code) throws AGIException {
        try {
            return switch (code) {
                case 14 ->
                    throw new IllegalStateException("Should never look up SAID in lookupTest()!");
                default ->
                    tests[code];
            };
        } catch (Exception e) {
            throw new AGIException("Can't lookup test bytecode: " + code, e);
        }
    }

    protected Instruction lookupAction(int code) throws AGIException {
        try {
            return switch (code) {
                case 134 ->
                    code134;
                case 151 ->
                    code151;
                case 152 ->
                    code152;
                case 176 ->
                    code176;
                default ->
                    actions[code];
            };
        } catch (Exception e) {
            throw new AGIException("Can't lookup action bytecode: " + code, e);
        }
    }

    /* parse a goto statement 0xFE */
    protected Instruction decodeGotoStatement(final LogicScript script, int start, int end) throws AGIException {
        try {
            // signed 16 bit le number...
            int tgt = (short) (script.getRawByte(start) | (script.getRawByte(start + 1) << 8));
            return new GotoInstruction(tgt);
        } catch (Exception e) {
            throw new AGIException("Error decoding 0xFE GOTO", e);
        }
    }

    protected Instruction decodeSimpleTest(int byteCode, final LogicScript script, int start, int end) throws AGIException {
        return switch (byteCode) {
            case 14 -> {
                final int count = script.getRawByte(start++);
                final var wordGroups = new int[count];
                for (int i = 0; i < count; ++i) {
                    wordGroups[i] = script.getRawByte(start) | (script.getRawByte(start + 1) << 8);
                    start += 2;
                }
                if (start > end) {
                    throw new AGIException("Out of room when reading SAID test! loc=" + start);
                }
                yield new SaidInstruction(wordGroups);
            }
            case 0xfc ->
                new OrInstruction(decodeTestStream(byteCode, script, start, end));
            case 0xfd -> {
                final var nxtByte = script.getRawByte(start++);
                yield new NotInstruction(decodeSimpleTest(nxtByte, script, start, end));
            }
            default ->
                lookupTest(byteCode);
        };
    }

    protected CompoundInstruction decodeTestStream(int delim, final LogicScript script, int start, int end) throws AGIException {
        final var tests = new CompoundInstruction();
        while (start < end) {
            final var bc = script.getRawByte(start++);
            if (bc == delim) {
                break;
            } else {
                final var decoded = decodeSimpleTest(bc, script, start, end);
                tests.add(decoded);
                start += decoded.getLength() - 1;
            }
        }
        return tests;
    }

    /* parse an IF structure 0xFF */
    protected Instruction decodeIfStatement(final LogicScript script, int start, int end) throws AGIException {
        final var tst = decodeTestStream(0xff, script, start, end);
        start += tst.getLength() + 1;
        final var thenLen = script.getRawByte(start) | (script.getRawByte(start + 1) << 8);
        var thenEnd = start + thenLen + 2;
        if (thenEnd > end) {
            tst.add(
                    new CommentInstruction(
                            String.format("End of THEN is after END... %04X vs %04X... shortening it...", thenEnd, end)));
            thenEnd = end;  // TEST RWT...
//            throw new AGIException(
//                    String.format(
//                            "Out of space for THEN portion of IF-THEN jump-loc=%d end=%d thenEnd=%d",
//                            start, end, thenEnd));
        }
        start += 2;

        // now check of the ELSE jump...
        var elseEnd = -1;
        if ((thenLen > 3) && (script.getRawByte(thenEnd - 3) == 0xfe)) {
            int elseLen = (short) (script.getRawByte(thenEnd - 2) | (script.getRawByte(thenEnd - 1) << 8));
            elseEnd = thenEnd + elseLen;
            if ((elseLen > 0) && (elseEnd <= end)) {
                thenEnd -= 3;  // take the jump out of the THEN section
            } else {
                elseEnd = -1; // can't do else after all.. either it jumps back, or we don't have space for ELSE
            }
        }

        final var thenIns = decode(script, start, thenEnd);
        final var elseIns = (elseEnd > 0)
                ? decode(script, thenEnd + 3, elseEnd)
                : null;

        return new IfAndInstruction(tst, thenIns, elseIns);
    }

    protected Instruction decodeOne(final LogicScript script, int start, int end) throws AGIException {
        final var bc = script.getRawByte(start);
        return switch (bc) {
            case 0xFE ->
                decodeGotoStatement(script, start + 1, end);
            case 0xFF ->
                decodeIfStatement(script, start + 1, end);
            default ->
                lookupAction(bc);
        };
    }

    public Instruction decode(final LogicScript script, int start, int end) throws AGIException {
        final var disassembled = new CompoundInstruction();
        try {
            while (start < end) {
                final var ins = decodeOne(script, start, end);
                disassembled.add(ins);
                start += ins.getLength();
            }
        } catch (AGIException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AGIException("Error decoding instructions", e);
        }
        return disassembled;
    }
}
