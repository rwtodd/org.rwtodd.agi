/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.disassembler;

import java.io.PrintWriter;
import org.rwtodd.agi.disassembler.LogicScript;

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
    public void printTo(PrintWriter pw, LogicScript script, int baseLocation, final String indentation) {
        final var extraInfo = new StringBuilder();
        final var extraIndent = indentation.isBlank() ? indentation : (" ".repeat(indentation.length()));
        pw.printf("%04X: %ssaid(", baseLocation, indentation);
        int count = 0;
        for (final int w : words) {
            pw.printf("%s%%w%d", (count++ == 0) ? "" : ", ", w);
            extraInfo.append(
                    String.format(
                            "      %s ;; WORD %%w%d: ",
                            extraIndent,
                            w));
            for(final String groupMember: script.getWordGroup(w)) {
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
