package org.rwtodd.agires.disassembler;

import org.rwtodd.agires.AgiLogicScript;
import org.rwtodd.agires.AgiResourceLoader;

import java.io.PrintWriter;

/**
 *
 * @author rwtodd
 */
public class SaidInstruction implements Instruction {

    private final int[] words;

    public SaidInstruction(final int[] words) {
        this.words = words;
    }

    @Override
    public int getLength() {
        return words.length * 2 + 2;  // 1 for bytecode, 1 for count, 2 each for words
    }

    @Override
    public void printTo(PrintWriter pw, AgiLogicScript script, AgiResourceLoader resLoader, int baseLocation, final String indentation) {
        final var extraInfo = new StringBuilder();
        final var extraIndent = indentation.isBlank() ? indentation : (" ".repeat(indentation.length()));
        pw.printf("%04X: %ssaid(", baseLocation, indentation);
        int count = 0;
        for (final int w : words) {
            pw.printf("%s%%w%d", (count++ == 0) ? "" : ", ", w);
            extraInfo.append(
                    String.format(
                            "      %s [ WORD %%w%d: ",
                            extraIndent,
                            w));
            for(final String groupMember: resLoader.getDictionary().idToWords(w)) {
                extraInfo.append(String.format("<%s>", groupMember));
            }
            extraInfo.append('\n');
        }
        pw.print(")\n");
        if(!extraInfo.isEmpty()) {
            pw.print(extraInfo);
        }
    }

}
