package org.rwtodd.agi.extractor;

import org.rwtodd.agi.resources.AGIException;
import org.rwtodd.agi.resources.DefaultResourceLoaders;
import org.rwtodd.agi.resources.Engine;
import org.rwtodd.args.*;

/**
 * Runs the show.
 *
 * @author: Richard Todd
 */
public class Cmd {

    public static void main(String[] args) {
        try {
            var efp = new ExistingFileParam("dir", 'd', "directory", "Which directory to be in.");
            var parser = new Parser(efp, new HelpParam());
            parser.parse(args);
            if (efp.getValue() == null) {
                parser.requestHelp();
            }
            final var rlf = new DefaultResourceLoaders();
            final var engine = new Engine(efp.getValue(),rlf);
            final var directory = engine.getResourceDirectory();
            System.out.println(engine);
            System.out.printf("There are %d logics.\n", directory.getLogicCount());
            System.out.printf("There are %d pics.\n", directory.getPicCount());
            System.out.printf("There are %d views.\n", directory.getViewCount());
            System.out.printf("There are %d sounds.\n", directory.getSoundCount());
        } catch (CommandLineException cle) {
            if (!cle.helpWasRequested()) {
                System.err.println(cle.getMessage());
            } else {
                System.err.println("Usage: agi-extractor [options]");
                cle.addHelpText(System.err);
            }
            System.exit(1);
        } catch (AGIException agi) {
            System.err.println("Error! " + agi.getMessage());
        }
    }
}
