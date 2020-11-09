package org.rwtodd.agi.disassembler;

import java.io.PrintWriter;

/**
 * Instructions that are simple operations.
 *
 * @author rwtodd
 */
public class BasicInstruction implements Instruction {

    private final String name;
    private final int length;
    private final int args;

    protected static final int ARG_UNK = 0;
    protected static final int ARG_NUM = 1;
    protected static final int ARG_OBJ = 2;
    protected static final int ARG_CTL = 3;
    protected static final int ARG_VAR = 4;
    protected static final int ARG_INV = 5;
    protected static final int ARG_TOK = 6;
    protected static final int ARG_FLG = 7;
    protected static final int ARG_STR = 8;
    protected static final int ARG_MSG = 9;
    protected static final int ARG_WRD = 10;

    protected static final String[] argPrefixes = new String[]{
        "", "", "%o", "%c", "%v", "%i", "%t", "%f", "%s", "%m", "%w"
    };

    protected int getArgType(int which) {
        return (args >> (which * 8)) & 0x0f;
    }

    protected int getNumArgs() {
        return length - 1;
    }

    protected BasicInstruction(final String n, final int numArgs, final int a) {
        name = n;
        length = numArgs + 1;
        args = a;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void printTo(final PrintWriter pw, final LogicScript script, int baseLocation, final String indentation) {
        final var extraInfo = new StringBuilder();
        final var extraIndent = indentation.isBlank() ? indentation : (" ".repeat(indentation.length()));
        pw.printf("%04X: %s%s", baseLocation, indentation, name);
        ++baseLocation;
        final var numArgs = getNumArgs();
        if (numArgs > 0) {
            pw.print('(');
            for (int a = 0; a < getNumArgs(); ++a, ++baseLocation) {
                final var atype = getArgType(a);
                final var aval = script.getRawByte(baseLocation);
                if (a != 0) {
                    pw.print(", ");
                }
                pw.printf("%s%d", argPrefixes[atype], aval);
                String description = null;
                switch (atype) {
                    case ARG_MSG:
                        extraInfo.append(
                                String.format(
                                        "      %s ;; MSG %%m%d: %s\n",
                                        extraIndent,
                                        aval,
                                        script.getScriptMessage(aval)));
                        break;
                    case ARG_FLG:
                        description = script.getFlagDescription(aval);
                        if (description != null) {
                            extraInfo.append(
                                    String.format(
                                            "      %s ;; FLAG %%f%d: %s\n",
                                            extraIndent,
                                            aval,
                                            description));
                        }
                        break;
                    case ARG_VAR:
                        description = script.getVariableDescription(aval);
                        if (description != null) {
                            extraInfo.append(
                                    String.format(
                                            "      %s ;; VAR %%v%d: %s\n",
                                            extraIndent,
                                            aval,
                                            description));
                        }
                        break;
                    case ARG_INV:
                        description = script.getObject(aval).getName();
                        if (description != null) {
                            extraInfo.append(
                                    String.format(
                                            "      %s ;; INVENTORY %%i%d: %s\n",
                                            extraIndent,
                                            aval,
                                            description));
                        }
                        break;
                }
            }
            pw.print(')');
        }
        pw.print('\n');
        if (!extraInfo.isEmpty()) {
            pw.print(extraInfo);
        }
    }

}
