package org.rwtodd.agi.extractor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.rwtodd.agi.resources.AGIException;
import org.rwtodd.agi.resources.OnDiskMetaData;
import org.rwtodd.agi.resources.ResourceDirectory;
import org.rwtodd.agi.resources.ResourceLoader;
import org.rwtodd.agi.resources.ResourceNotPresentException;
import org.rwtodd.agi.resources.VolumeManager;
import org.rwtodd.args.*;
import java.util.Map.Entry;
import org.rwtodd.agi.resources.BufferedImagePicHandler;

/**
 * Runs the show.
 *
 * @author: Richard Todd
 */
public class Cmd {

    public static void main(String[] args) {
//        args = new String[] { "-dh:\\game\\kings-quest-4-agi", "--pics", "-r2" };
        try {
            final var efp = new ExistingFileParam("dir", 'd', "directory", "Which directory to be in.");
            final var doCSound = new FlagParam("csound", ' ', "write csound scores for all sounds.");
            final var doWords = new FlagParam("words", ' ', "write out the WORDS.TOK resources");
            final var doObjects = new FlagParam("objects", ' ', "write out the OBJECTS resources");
            final var doPics = new FlagParam("pics", ' ', "write GIFs of the PIC resources");
            final var picScale = new IntParam("picscale", ' ', "FACTOR", "How much to scale the image up", 3);
            final var doOne = new IntParam("resource", 'r', "NUMBER", "Just extract the given resource", null);
            final var doLogics = new FlagParam("logics", ' ', "write logic script resources");
            final var verboseFlag = new FlagParam("verbose", 'v', "Write debug messages where applicable.");

            WordDictionaryHandler wordDictionary = null; // may or may not load the words...
            ObjectDictionaryHandler objectDictionary = null;    // may or may not load the objects....

            var parser = new Parser(efp, doCSound, doWords, doObjects, doPics,
                    picScale, doLogics, doOne, verboseFlag, new HelpParam());
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
                System.out.printf("There are %d logics.\n", resloader.getLogicCount());
                System.out.printf("There are %d pics.\n", resloader.getPicCount());
                System.out.printf("There are %d views.\n", resloader.getViewCount());
                System.out.printf("There are %d sounds.\n", resloader.getSoundCount());

                if (doCSound.getValue()) {
                    runCSoundExtractor(resloader);
                }

                // there will eventually be other reasons (like LOGIC) to load words
                if (doWords.getValue()) {
                    try {
                        wordDictionary = new WordDictionaryHandler();
                        resloader.loadWords().streamToHandler(wordDictionary);
                    } catch (AGIException agi) {
                        describeException("Error loading words: ", agi);
                    }
                }

                if (doWords.getValue()) {
                    runWordDescription(wordDictionary);
                }

                // there will eventually be other reasons (like LOGIC) to load objects
                if (doObjects.getValue()) {
                    try {
                        objectDictionary = new ObjectDictionaryHandler();
                        resloader.loadObjects().streamToHandler(objectDictionary);
                    } catch (AGIException agi) {
                        describeException("Error loading objects: ", agi);
                    }
                }

                if (doObjects.getValue()) {
                    runObjectsDescription(objectDictionary);
                }

                if (doPics.getValue()) {
                    if (doOne.getValue() == null) {
                        runPics(resloader, picScale.getValue(), verboseFlag.getValue());
                    } else {
                        runOnePic(doOne.getValue(), resloader, picScale.getValue(), verboseFlag.getValue());
                    }
                }

                if (doLogics.getValue()) {
                    if (doOne.getValue() == null) {
                        runLogics(resloader, verboseFlag.getValue());
                    } else {
                        runOneLogic(doOne.getValue(), resloader, verboseFlag.getValue());
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
            describeException("Error!", agi);
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }

    private static void runCSoundExtractor(final ResourceLoader resloader) {
        // now dump all the sounds...
        for (int i = 0; i < resloader.getSoundCount(); ++i) {
            try {
                System.out.println("Loading sound " + i);
                final var res = resloader.loadSound(i);
                try (final var handler = new CSoundHandler(Paths.get(String.format("sound_%03d.sco", i)))) {
                    res.streamToHandler(handler);
                }
            } catch (IOException ioe) {
                System.err.println("IO Error during sound " + i + " " + ioe);
            } catch (ResourceNotPresentException rnp) {
                System.out.println("Sound " + i + " isn't in the resources.");
            } catch (AGIException ae) {
                describeException("ERROR:", ae);
            }
        }
    }

    private static void describeException(String msg, Exception ex) {
        System.err.println(msg + " " + ex.getMessage());
        if (ex.getCause() != null) {
            System.err.println("Cause: " + ex.getCause().getMessage());
        }
    }

    private static void runObjectsDescription(final ObjectDictionaryHandler objectDictionary) {
        try (
                final var olist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("object_list.csv"), StandardCharsets.UTF_8))) {
            olist.print("\"NUMBER\",\"OBJECT\",\"STARTING ROOM\"\n");
            final var entries = objectDictionary.getObjects();
            for (int i = 0; i < entries.size(); ++i) {
                final var obj = entries.get(i);
                olist.printf("\"%d\",\"%s\",\"%d\"\n", i, obj.getName(), obj.getStartingRoom());
            }
        } catch (IOException ioe) {
            describeException("Error writing object list", ioe);
        }
    }

    private static void runWordDescription(final WordDictionaryHandler wordDictionary) {
        try (
                final var wlist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("word_list.csv"), StandardCharsets.UTF_8))) {

            wlist.print("\"WORD\",\"GROUP_NO\"\n");
            wordDictionary.wordStream()
                    .sorted(Entry.comparingByKey())
                    .forEachOrdered(word
                            -> wlist.printf("\"%s\",\"%d\"\n", word.getKey(), word.getValue())
                    );
        } catch (IOException ioe) {
            describeException("Error writing word list", ioe);
        }

        try (
                final var glist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("word_groups.csv"), StandardCharsets.UTF_8))) {
            glist.print("\"GROUP_NO\",\"WORDS\"\n");
            wordDictionary.groupStream()
                    .sorted(Entry.comparingByKey())
                    .forEachOrdered(group -> {
                        glist.printf("\"%s\"", group.getKey());
                        group.getValue().forEach(word -> glist.printf(",\"%s\"", word));
                        glist.print("\n");
                    });
        } catch (IOException ioe) {
            describeException("Error writing word list", ioe);
        }
    }

    private static void runOnePic(final int number, final ResourceLoader resloader, int scaleFactor, boolean verbose) {
        try {
            System.out.println("Loading PIC " + number);
            final var res = resloader.loadPic(number);
            final var handler = (verbose)
                    ? new LoggingImageHandler()
                    : new BufferedImagePicHandler();
            res.streamToHandler(handler);
            handler.writeImageToGIF(Paths.get(String.format("pic_%03d.gif", number)), scaleFactor);
            handler.writePriorityToGIF(Paths.get(String.format("prio_%03d.gif", number)), scaleFactor);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }

    private static void runPics(final ResourceLoader resloader, int scaleFactor, boolean verbose) {
        // now dump all the pics...
        for (int i = 0; i < resloader.getPicCount(); ++i) {
            runOnePic(i, resloader, scaleFactor, verbose);
        }
    }

    private static void runOneLogic(final int number, final ResourceLoader resloader, boolean verbose) {
        try {
            System.out.println("Loading LOGIC " + number);
            final var res = resloader.loadLogic(number);
            final var outPath = Paths.get(String.format("logic_%04d.txt", number));

            try (final var writer = Files.newBufferedWriter(outPath); 
                    final var pw = new PrintWriter(writer)) {
                pw.printf("Logic Script %d\n", number);
                pw.print("MESSAGES: ~~~~~~~~~~\n");
                for (int msg = 0; msg < res.getMessageCount(); ++msg) {
                    pw.printf("%d: %s\n", msg, res.getMessage(msg));
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }

    private static void runLogics(final ResourceLoader resloader, boolean verbose) {
        // now dump all the lgoics...
        for (int i = 0; i < resloader.getLogicCount(); ++i) {
            runOneLogic(i, resloader, verbose);
        }
    }
}
