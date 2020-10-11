package org.rwtodd.agi.extractor;

import org.rwtodd.agi.resources.Game;
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
            var parser = new Parser(efp,new HelpParam());
            parser.parse(args);
            if(efp.getValue() == null) parser.requestHelp();
            
            var x = new Game(efp.getValue().toString());
            System.out.println("Hello!");
        } catch (CommandLineException cle) {
            if (!cle.helpWasRequested()) {
                System.err.println(cle.getMessage());
            } else {
                System.err.println("Usage: agi-extractor [options]");
                cle.addHelpText(System.err);
            }
            System.exit(1);
        }
    }
}
