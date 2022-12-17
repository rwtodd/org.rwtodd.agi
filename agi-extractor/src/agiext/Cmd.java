package agiext;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import org.rwtodd.agires.*;
import org.rwtodd.args.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.sound.midi.InvalidMidiDataException;

/**
 * Just like an IntListParam, except it also accepts '*' or 'all'
 * to designate 0..255.
 */
class IntListOrAll extends IntListParam {
    IntListOrAll(Iterable<String> names, String helpstr) {
        super(names, null, helpstr);
    }

    @Override
    protected IntStream convertArg(String param, String arg) throws Exception {
        if(arg.equalsIgnoreCase("all") || arg.equals("*")) {
            return IntStream.range(0,256);
        } else {
            return super.convertArg(param, arg);
        }
    }
}

/**
 * Runs the show.
 *
 * @author Richard Todd
 */
public class Cmd {

    private static void usage(Parser p, String msg) {
        System.err.println("usage: agi-extract [optons]\n");
        p.printHelpText(System.err);
        if(msg != null) {
            System.err.println();
            System.err.println(msg);
        }
        System.exit(1);
    }

    public static void main(String[] args) {
        final var gameDir = new ExistingDirectoryParam(List.of("dir", "d"), Path.of("."), "<Directory>The game directory (defualt: current dir)");
        final var doCSound = new IntListOrAll(List.of("csound"), "<Resource List>Write csound scores for SOUND resources");
        final var exampleOrcs = new FlagParam(List.of("orchestras"), "output example CSound orchestra files for use with csound output");
        final var doMidi = new IntListOrAll(List.of("midi"), "<Resource List>Write MIDI scores for SOUND resources");
        final var doWords = new FlagParam(List.of("words"), "Write out the WORDS.TOK resources");
        final var doObjects = new FlagParam(List.of("objects"), "Write out the OBJECTS resources");
        final var doPics = new IntListOrAll(List.of("pics"), "<Resource List>Write PNGs of the PIC resources");
        final var imgScale = new IntParam(List.of("scale"), 3, "<Factor>How much to scale images up (default 3)");
        final var noAspectRatio = new FlagParam(List.of("noAR"), "Don't correct the aspect ratio for modern square pixels");
        final var picStepsFlag = new FlagParam(List.of("picsteps"), "Write intermediate PNG images as PICs are drawn");
        final var doLogics = new IntListOrAll(List.of("logics"), "<Resource List>Write disassembled LOGIC script resources");
        final var doViews = new IntListOrAll(List.of("views"), "<Resource List>Write GIFs of the VIEW resources");
        final var help = new FlagParam(List.of("help"), "Print this help text.");
        final var parser = new Parser(gameDir, doCSound, exampleOrcs, doMidi, doWords, doObjects, doPics,
                imgScale, noAspectRatio, picStepsFlag, doLogics, doViews, help);

        try {
            final var extras = parser.parse(args);

            if(help.getValue()) { usage(parser, null); }
            else if(extras.size() > 0) { usage(parser, "Error: extra command-line argument!"); }

            final var metadata = new OnDiskMetaData(gameDir.getValue());
            try (final var resLoader = new AgiResourceLoader(metadata)) {
                System.out.printf("Game is version %s\n", metadata.getVersionString());
                if (metadata.isV3()) {
                    System.out.printf("The game prefix is %s\n", metadata.getPrefix());
                }

                System.out.printf("There are %d logics.\n", resLoader.getLogicCount());
                System.out.printf("There are %d pics.\n", resLoader.getPicCount());
                System.out.printf("There are %d views.\n", resLoader.getViewCount());
                System.out.printf("There are %d sounds.\n", resLoader.getSoundCount());
                System.out.printf("There are %d maximum animated objects.\n", resLoader.getMaxAnimatedObjects());

                runCSoundExtractor(resLoader, doCSound.getValue());
                if(exampleOrcs.getValue())  {
                    Stream.of("agi-pcspeaker.orc","agi-tandy.orc","agi-pwm.orc","agi-synth.orc")
                            .forEach(Cmd::copyOutOrchestraFile);
                }
                runMidiExtractor(resLoader, doMidi.getValue());
                if (doWords.getValue()) runWordDescription(resLoader.getDictionary());
                if (doObjects.getValue())  runObjectsDescription(resLoader.getInitialGameObjects());
                runPics(resLoader, doPics.getValue(), imgScale.getValue(), !noAspectRatio.getValue(), picStepsFlag.getValue());
                runViews(resLoader, doViews.getValue(), imgScale.getValue(), !noAspectRatio.getValue());
                runLogics(resLoader, doLogics.getValue());
            }
        } catch (ArgParserException ape) {
            usage(parser, ape.getMessage());
        } catch (AgiException agi) {
            describeException("Error!", agi);
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            System.exit(1);
        }
    }

    private static void copyOutOrchestraFile(String fname) {
        try {
            System.out.printf("[CSOUND] Copying example orchestra %s\n", fname);
            try(final var pwm = Cmd.class.getResourceAsStream(fname)) {
                if(pwm == null) throw new IOException("Can't find resource!");
                Files.copy(pwm, Paths.get(fname), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch(IOException ioe) {
            System.err.println("Error trying to copy example orchestra file: " + ioe.getMessage());
        }
    }

    private static void runOneCSound(final int num, final AgiResourceLoader resLoader) {
        try {
            System.out.println("[CSOUND] Loading sound " + num);
            CSoundBuilder.writeScore(Paths.get(String.format("sound_%03d.sco", num)), num,resLoader.loadSound(num));
        } catch (IOException ioe) {
            System.err.println("IO Error during sound " + num + " " + ioe.getMessage());
        } catch (ResourceNotPresentException rnp) {
            System.out.println("Sound " + num + " isn't in the resources.");
        } catch (AgiException ae) {
            describeException("Sound " + num + " ERROR:", ae);
        } catch(Exception e) {
            System.err.println("Error during sound " + num + " " + e.getMessage());
        }
    }
    
    private static void runCSoundExtractor(final AgiResourceLoader resloader, IntStream which) {
        if(which == null) return;

        final int sc = resloader.getSoundCount();
        which.filter(idx -> idx < sc).forEach(idx -> runOneCSound(idx, resloader));
    }
    
    private static void runOneMidi(final int num, final AgiResourceLoader resLoader) {
        try {
            System.out.println("[MIDI] Loading sound " + num);
            MidiBuilder.writeMidiFile(Paths.get(String.format("sound_%03d.mid",num)), num, resLoader.loadSound(num));
        } catch (InvalidMidiDataException imde) {
            System.err.println("MIDI Error during sound " + num + " " + imde.getMessage());
        } catch (IOException ioe) {
            System.err.println("IO Error during sound " + num + " " + ioe.getMessage());
        } catch (ResourceNotPresentException rnp) {
            System.out.println("Sound " + num + " isn't in the resources.");
        } catch (AgiException ae) {
            describeException("Sound " + num + " ERROR:", ae);
        } catch(Exception e) {
            System.err.println("Error during sound " + num + " " + e.getMessage());
        }
    }
    
    private static void runMidiExtractor(final AgiResourceLoader resloader, IntStream which) {
        if(which == null) return;

        final int sc = resloader.getSoundCount();
        which.filter(idx -> idx < sc).forEach(idx -> runOneMidi(idx, resloader));
    }
    
    private static void describeException(String msg, Exception ex) {
        System.err.println(msg + " " + ex.getMessage());
        if (ex.getCause() != null) {
            System.err.println("Cause: " + ex.getCause().getMessage());
        }
    }
    
    private static void runObjectsDescription(final List<AgiObject> objectList) {
        try (
                final var olist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("object_list.csv"), StandardCharsets.UTF_8))) {
            olist.print("\"NUMBER\",\"OBJECT\",\"STARTING ROOM\"\n");
            for (int i = 0; i < objectList.size(); ++i) {
                final var obj = objectList.get(i);
                olist.printf("\"%d\",\"%s\",\"%d\"\n", i, obj.name(), obj.startingRoom());
            }
        } catch (IOException ioe) {
            describeException("Error writing object list", ioe);
        }
    }
    
    private static void runWordDescription(final AgiDictionary wordDictionary) {
        try (final var wlist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("word_list.csv"), StandardCharsets.UTF_8))) {
            wlist.print("\"WORD\",\"GROUP_NO\"\n");
            wordDictionary.allWords().stream().sorted().forEachOrdered( (word) -> {
                wlist.printf("\"%s\",\"%d\"\n", word, wordDictionary.wordToId(word));
            });
        } catch (IOException ioe) {
            describeException("Error writing word list", ioe);
        }
        
        try (final var glist = new PrintWriter(
                        Files.newBufferedWriter(Paths.get("word_groups.csv"), StandardCharsets.UTF_8))) {
            glist.print("\"GROUP_NO\",\"WORDS\"\n");
            wordDictionary.allIDs().stream().sorted().forEachOrdered( (group) -> {
                glist.printf("\"%d\"", group);
                wordDictionary.idToWords(group).forEach(word -> glist.printf(",\"%s\"", word));
                glist.print("\n");
            });
        } catch (IOException ioe) {
            describeException("Error writing word list", ioe);
        }
    }
    
    private static void runOnePic(final int number, final AgiResourceLoader resLoader, final int scaleFactor, final boolean correctAR, final boolean showSteps) {
        try {
            System.out.println("[PIC] Loading picture " + number);
            final String baseName = String.format("pic_%03d", number);
            final var observer = !showSteps ? null : new Consumer<AgiPic.Image>() {
                int stepNo = 0;
                @Override
                public void accept(AgiPic.Image image) {
                    final var stepName = String.format("%s_step_%04d.png", baseName, ++stepNo);
                    try {
                        final var scaled = PngImage.scaleUp(PngImage.imageFromPic(resLoader, image), scaleFactor, correctAR);
                        PngImage.writeImage(Paths.get(stepName), scaled);
                    } catch(Exception e) {
                        System.err.println("Error trying to write PIC step-image " + stepName + " " + e.getMessage());
                    }
                }
            };
            final AgiPic pic = resLoader.loadPic(number, observer);
            var scaled = PngImage.scaleUp(PngImage.imageFromPic(resLoader, pic.picture()),scaleFactor,correctAR);
            PngImage.writeImage(Paths.get(baseName+".png"), scaled);
            scaled = PngImage.scaleUp(PngImage.imageFromPic(resLoader, pic.priority()), scaleFactor, correctAR);
            PngImage.writeImage(Paths.get(baseName + "_pri.png"), scaled);
        } catch (IOException ioe) {
            System.err.println("VIEW " + number + " error writing IO. " + ioe.getMessage());
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AgiException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runPics(final AgiResourceLoader resloader, IntStream which, int scaleFactor, boolean correctAR, boolean showSteps) {
        if(which == null) return;
        final int pc = resloader.getPicCount();
        which.filter(idx -> idx < pc).forEach(idx -> runOnePic(idx, resloader, scaleFactor, correctAR, showSteps));
    }
    
    private static void runOneLogic(final int number, final AgiResourceLoader resloader) {
        try {
            System.out.println("[LOGIC] Loading script " + number);
            final var res = resloader.loadLogic(number);
            final var outPath = Paths.get(String.format("logic_%04d.txt", number));
            
            try (final var pw = new PrintWriter(Files.newBufferedWriter(outPath))) {
                pw.printf("[ Logic Script %d\n", number);
                res.disassembleInto(pw);
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (ResourceNotPresentException rnp) {
            System.out.println("LOGIC " + number + " isn't in the resources.");
        } catch (AgiException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runLogics(final AgiResourceLoader resloader, IntStream which) {
        if(which == null) return;
        final int lc = resloader.getLogicCount();
        which.filter(idx -> idx < lc).forEach(idx -> runOneLogic(idx, resloader));
    }
    
    private static void runOneView(final int number, final AgiResourceLoader resLoader, int scaleFactor, boolean correctAR) {
        try {
            System.out.println("[VIEW] Loading view " + number);
            final var view = resLoader.loadView(number);

            if (view.description().isPresent()) {
                Files.writeString(Paths.get(String.format("view_%03d_desc.txt", number)), view.description().get());
            }

            final var img = PngImage.scaleUp(PngImage.imageFromView(resLoader, view), scaleFactor, correctAR);
            PngImage.writeImage(Paths.get(String.format("view_%03d.png", number)), img);
        } catch (IOException ioe) {
            System.err.println("VIEW " + number + " error writing IO. " + ioe.getMessage());
        } catch (ResourceNotPresentException rnp) {
            System.out.println("PIC " + number + " isn't in the resources.");
        } catch (AgiException ae) {
            describeException("ERROR:", ae);
        }
    }
    
    private static void runViews(final AgiResourceLoader resloader, IntStream which, int scaleFactor, boolean correctAR) {
        if(which == null) return;
        final int vc = resloader.getViewCount();
        which.filter(idx -> idx < vc).forEach(idx -> runOneView(idx, resloader, scaleFactor, correctAR));
    }
}
