package org.rwtodd.agi.extractor;

import java.io.IOException;
import org.rwtodd.agi.resources.AGIException;
import org.rwtodd.agi.resources.OnDiskMetaData;
import org.rwtodd.agi.resources.ResourceDirectory;
import org.rwtodd.agi.resources.ResourceLoader;
import org.rwtodd.agi.resources.ResourceNotPresentException;
import org.rwtodd.agi.resources.VolumeManager;
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

            final var metadata = new OnDiskMetaData(efp.getValue());
            final var resdir = ResourceDirectory.createDefault(metadata);
            final var volmgr = VolumeManager.createDefault(metadata);
            try (final var resloader = ResourceLoader.createDefault(metadata, resdir, volmgr)) {
                System.out.printf("Game is version %s\n", metadata.getVersionString());
                if (metadata.isV3()) {
                    System.out.printf("The game preix is %s\n", metadata.getPrefix());
                }
                System.out.printf("There are %d logics.\n", resdir.getLogicCount());
                System.out.printf("There are %d pics.\n", resdir.getPicCount());
                System.out.printf("There are %d views.\n", resdir.getViewCount());
                System.out.printf("There are %d sounds.\n", resdir.getSoundCount());

                // now dump all the sounds...
                for (int i = 0; i < resloader.getSoundCount(); ++i) {
                    try {
                        System.out.println("Loading sound " + i);
                        resloader.loadSound(i);
                    } catch (ResourceNotPresentException rnp) {
                        System.out.println("Sound " + i + " isn't in the resources.");
                    }
                }
            }
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
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
}
