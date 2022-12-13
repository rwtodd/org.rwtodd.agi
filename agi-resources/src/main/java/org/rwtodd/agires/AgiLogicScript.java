package org.rwtodd.agires;

import org.rwtodd.agires.disassembler.InstructionDecoder;

import java.io.PrintWriter;

public class AgiLogicScript {
    private final AgiResourceLoader resLoader;
    private final String[] messages;
    private final byte[] bytecodes;

    public AgiLogicScript(AgiResourceLoader resLoader, String[] messages, byte[] byteCodes) {
        this.resLoader = resLoader;
        this.messages = messages;
        this.bytecodes = byteCodes;
    }

    public byte[] getBytecodes() { return bytecodes; }

    /**
     *
     * @param num 1-based index into messages (%m1 = index 0 and so forth)
     * @return the message text
     */
    public String getMessage(int num) {
        return (num > 0 && num <= messages.length) ? messages[num-1] : "";
    }

    public int getMessageCount() { return messages.length; }

    public void disassembleInto(PrintWriter pw) throws AgiException {
        pw.print("[ MESSAGES: ~~~~~~~~~~\n");
        for (int msg = 1; msg <= getMessageCount(); ++msg) {
            pw.printf("%d: %s\n", msg, messages[msg-1]);
        }
        pw.print("\n[ SCRIPT: ~~~~~~~~~~~~~~\n");
        final var idecode = new InstructionDecoder(resLoader.getMetaData().getVersion());
        final var ins = idecode.decode(bytecodes,0, bytecodes.length);
        ins.printTo(pw, this, resLoader,0, "");
    }
}
