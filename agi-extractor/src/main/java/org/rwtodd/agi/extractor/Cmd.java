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
import javax.sound.midi.InvalidMidiDataException;
import org.rwtodd.agi.disassembler.LogicResourceScript;
import org.rwtodd.agi.resources.BufferedImagePicBuilder;
import org.rwtodd.agi.resources.BufferedImageViewBuilder;

/**
 * Runs the show.
 *
 * @author: Richard Todd
 */
public class Cmd {
    
    public static void main(String[] args) {
        //args = new String[] { "-dh:\\game\\kings-quest-3", "--views" }; // TEST
        try {
            final var efp = new ExistingFileParam("dir", 'd', "directory", "The game directory");
            final var doCSound = new FlagParam("csound", ' ', "Write csound scores for all SOUND resources");
            final var doMidi = new FlagParam("midi", ' ', "Write MIDI scores for all SOUND resources");
            final var doWords = new FlagParam("words", ' ', "Write out the WORDS.TOK resources");
            final var doObjects = new FlagParam("objects", ' ', "Write out the OBJECTS resources");
            final var doPics = new FlagParam("pics", ' ', "Write GIFs of the PIC resources");
            final var imgScale = new IntParam("scale", ' ', "FACTOR", "How much to scale images up", 3);
            final var doOne = new IntParam("resource", 'r', "NUMBER", "Just extract the given resource", null);
            final var doLogics = new FlagParam("logics", ' ', "Write LOGIC script resources");
            final var picStepsFlag = new FlagParam("picsteps", ' ', "Write intermediate PIC images as PICs are drawn");
            final var doViews = new FlagParam("views", ' ', "Write GIFs of the VIEW resources");
            
            WordDictionary wordDictionary = null; // may or may not load the words...
            ObjectDictionary objectDictionary = null;    // may or may not load the objects....

            var parser = new Parser(efp, doCSound, doMidi, doWords, doObjects, doPics,
                    imgScale, picStepsFlag, doLogics, doOne, doViews, new HelpParam());
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
                    if (doOne.getValue() == null) {
                        runCSoundExtractor(resloader);
                    } else {
                        runOneCSound(doOne.getValue(), resloader);
                    }
                }
                
                if (doMidi.getValue()) {
                    if (doOne.getValue() == null) {
                        runMidiExtractor(resloader);
                    } else {
                        runOneMidi(doOne.getValue(), resloader);
                    }
                }
                
                if (doWords.getValue() || doLogics.getValue()) {
                    try {
                        final var wdh = new WordDictionaryBuilder();
                        resloader.loadWords().build(wdh);
                        wordDictionary = wdh;
                    } catch (AGIException agi) {
                        describeException("Error loading words: ", agi);
                    }
                }
                
                if (doWords.getValue()) {
                    runWordDescription(wordDictionary);
                }
                
                if (doObjects.getValue() || doLogics.getValue()) {
                    try {
                        final var odh = new ObjectDictionaryBuilder();
                        resloader.loadObjects().build(odh);
                        objectDictionary = odh;
                    } catch (AGIException agi) {
                        describeException("Error loading objects: ", agi);
                    }
                }
                
                if (doObjects.getValue()) {
                    runObjectsDescription(objectDictionary);
                }
                
                if (doPics.getValue()) {
                    if (doOne.getValue() == null) {
                        runPics(resloader, imgScale.getValue(), picStepsFlag.getValue());
                    } else {
                        runOnePic(doOne.getValue(), resloader, imgScale.getValue(), picStepsFlag.getValue());
                    }
                }
                
                if (doLogics.getValue()) {
                    final var disassembler = new LogicResourceScript(metadata.getVersion(), wordDictionary, objectDictionary);
                    if (doOne.getValue() == null) {
                        runLogics(resloader, disassembler);
                    } else {
                        runOneLogic(doOne.getValue(), resloader, disassembler);
                    }
                }
                
                if (doViews.getValue()) {
                    if (doOne.getValue() == null) {
                        runViews(resloader, imgScale.getValue());
                    } else {
                        runOneView(doOne.getValue(), resloader, imgScale.getValue());
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
    
    private static void runOneCSound(final int num, final ResourceLoader resloader) {
        try {
            System.out.println("[CSOUND] Loading sound " + num);
            final var res = resloader.loadSound(num);
            try (final var handler = new CSoundBuilder(Paths.get(String.format("sound_%03d.sco", num)))) {
                res.build(handler);
            }
        } catch (IOException ioe) {
            System.err.println("IO Error during sound " + num + " " + ioe);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("Sound " + num + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runCSoundExtractor(final ResourceLoader resloader) {
        // now dump all the sounds...
        for (int i = 0; i < resloader.getSoundCount(); ++i) {
            runOneCSound(i, resloader);
        }
    }
    
    private static void runOneMidi(final int num, final ResourceLoader resloader) {
        try {
            System.out.println("[MIDI] Loading sound " + num);
            final var res = resloader.loadSound(num);
            try (final var builder = new MidiBuilder(Paths.get(String.format("sound_%03d.mid", num)))) {
                res.build(builder);
                builder.getErrorState()
                        .ifPresent(err -> System.err.println("Bad MIDI message during sound " + num + " " + err));
            }
        } catch (InvalidMidiDataException imde) {
            System.err.println("MIDI Error during sound " + num + " " + imde);
        } catch (IOException ioe) {
            System.err.println("IO Error during sound " + num + " " + ioe);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("Sound " + num + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runMidiExtractor(final ResourceLoader resloader) {
        // now dump all the sounds...
        for (int i = 0; i < resloader.getSoundCount(); ++i) {
            runOneMidi(i, resloader);
        }
    }
    
    private static void describeException(String msg, Exception ex) {
        System.err.println(msg + " " + ex.getMessage());
        if (ex.getCause() != null) {
            System.err.println("Cause: " + ex.getCause().getMessage());
        }
    }
    
    private static void runObjectsDescription(final ObjectDictionary objectDictionary) {
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
    
    private static void runWordDescription(final WordDictionary wordDictionary) {
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
    
    private static void runOnePic(final int number, final ResourceLoader resloader, int scaleFactor, boolean showSteps) {
        try {
            System.out.println("Loading PIC " + number);
            final var res = resloader.loadPic(number);
            final var handler = (showSteps)
                    ? new LoggingImageBuilder(number)
                    : new BufferedImagePicBuilder();
            res.build(handler);
            handler.writeImageToGIF(Paths.get(String.format("pic_%03d.gif", number)), scaleFactor);
            handler.writePriorityToGIF(Paths.get(String.format("prio_%03d.gif", number)), scaleFactor);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runPics(final ResourceLoader resloader, int scaleFactor, boolean showSteps) {
        // now dump all the pics...
        for (int i = 0; i < resloader.getPicCount(); ++i) {
            runOnePic(i, resloader, scaleFactor, showSteps);
        }
    }
    
    private static void runOneLogic(final int number, final ResourceLoader resloader, LogicResourceScript disassembler) {
        try {
            System.out.println("Loading LOGIC " + number);
            final var res = resloader.loadLogic(number);
            final var outPath = Paths.get(String.format("logic_%04d.txt", number));
            
            try (final var pw = new PrintWriter(Files.newBufferedWriter(outPath))) {
                pw.printf("Logic Script %d\n", number);
                pw.print("MESSAGES: ~~~~~~~~~~\n");
                for (int msg = 0; msg < res.getMessageCount(); ++msg) {
                    pw.printf("%d: %s\n", msg, res.getMessage(msg));
                }
                pw.print("\nSCRIPT: ~~~~~~~~~~~~~~\n");
                disassembler.setResource(res);
                disassembler.getInstructionDecoder()
                        .decode(disassembler, 0, disassembler.getRawLength())
                        .printTo(pw, disassembler, 0, "");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("LOGIC " + number + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runLogics(final ResourceLoader resloader, LogicResourceScript disassembler) {
        // now dump all the lgoics...
        for (int i = 0; i < resloader.getLogicCount(); ++i) {
            runOneLogic(i, resloader, disassembler);
        }
    }
    
    private static void runOneView(final int number, final ResourceLoader resloader, int scaleFactor) {
        try {
            System.out.println("Loading VIEW " + number);
            final var res = resloader.loadView(number);
            final var builder = new BufferedImageViewBuilder();
            res.build(builder);
            if (builder.getDescription().isPresent()) {
                Files.writeString(Paths.get(String.format("view_%03d_desc.txt", number)), builder.getDescription().get());
            }
            for (int loop = 0; loop < builder.getLoopCount(); ++loop) {
                for (int cell = 0; cell < builder.getCellCount(loop); ++cell) {
                    final var outpath = Paths.get(String.format("view_%03d_loop_%03d_cell_%03d.gif", number, loop, cell));
                    builder.writeToDisk(outpath, loop, cell, scaleFactor);
                }
            }
        } catch (IOException ioe) {
            System.err.println("VIEW " + number + " error writing IO. " + ioe);
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AGIException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runViews(final ResourceLoader resloader, int scaleFactor) {
        // now dump all the pics...
        for (int i = 0; i < resloader.getViewCount(); ++i) {
            runOneView(i, resloader, scaleFactor);
        }
    }
}
