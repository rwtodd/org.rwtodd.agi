# AGI Resources

This is a library for exploring Sierra AGI adventure game
resources.

The current *java-version* branch is focused on a Java version.  There are
other branches with Go and Clojure versions of the code, with
different capabilities and levels of maturity (elsewhere I have a C# version as well).

# Extractor Features

This repo also has an example program to go with the library: an AGI Resource extractor CLI program. It:

 - Extracts all resource types; seems to work on all V2/V3 AGI games that I have
 - Disassembles logic scripts (and partially decompiles)
 - Extracts graphics scaled and with 4:3 Aspect-Ratio correction (can be turned off)
 - Can save steps as PICs are drawn, to make time-lapse videos (scripts provided to make the videos with `ffmpeg`)
   - Can partially-interpret the associated logic script to guess where to add views to the PIC.  So doorways will have doors, etc.
 - Can extract sound to MIDI or Csound scores (example csound orchestras provided)
 - Extracts views into one PNG-per view, with the loops and cells arranged

# Build and Run Instructions

Look, I know I should probably to add more here, but I'm lazy.  It's a gradle project that requires Java17+.  I personally build with:

    ./gradlew assemble

... and there's a custom task `aE:runcmd` to print out a java command you can run for the extractor. 
It can also be `jlink`'ed or `jpackage`'ed and what not but I haven't bothered to do it yet.

It's a modular (JMPS) project, which can be confusing if you've never dealt with it before.  For example, if you
aren't careful how you run it, the parts of the code that want to load jar resources will fail.  The 'aE:runcmd' task output uses all the modular jars so that resource loading always works.

When you run it, ask for `--help` to see all the command-line switches.  The switches for numbered resources all take lists, like so:

    agi-extract -d ~/gog/kq3 --picviews --pics=2,5,7..13,18 --logics=1..10

or to just get them all:

    agi-extract -d ~/gog/kq3 --picviews --pics='*' --logics='*'


# Maven Central

I haven't yet, but plan to clean up the javadoc on the agires jar and put it on maven/sonatype so it's easy to import into other projects.  Maybe an actual
AGI interpreter one day,


