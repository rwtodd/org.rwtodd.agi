package org.rwtodd.agires;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;

/**
 * Avoids constantly opening and closing files...
 *
 * @author rwtodd
 */
class FileCache implements Closeable {

    private static class TimedFile {

        RandomAccessFile file;
        Instant timeStamp;
    }

    private final Map<Path, TimedFile> openFiles;
    private Instant lastCleanup;
    private final int timeOutSecs;
    private int requestCounter;

    FileCache(int timeOutSecs) {
        openFiles = new HashMap<>();
        this.timeOutSecs = timeOutSecs;
        requestCounter = 0;
        lastCleanup = Instant.now();
    }

    /**
     * Lookup a file in the cache, or open it if it doesn't exist yet.
     *
     * @param p the path to the file.
     * @return the opened file.
     * @throws AGIException if there is a problem getting the file.
     */
    synchronized RandomAccessFile getFile(final Path p) throws AGIException {
        var f = openFiles.get(p);
        try {
            if (f == null) {
                f = new TimedFile();
                f.file = new RandomAccessFile(p.toFile(), "r");
                openFiles.put(p, f);
            }
            f.timeStamp = Instant.now();
        } catch (FileNotFoundException fnf) {
            throw new AGIException("Could not find file " + p, fnf);
        }
        maybeCleanup();
        return f.file;
    }

    @Override
    synchronized public void close() {
        final var iter = openFiles.entrySet().iterator();
        while (iter.hasNext()) {
            final var tf = iter.next().getValue();
            try {
                tf.file.close();
            } catch (Exception e) {
                /* do nothing in this case... */
            }
            iter.remove();
        }
    }

    /**
     * Clean up unused files periodically.
     */
    private void maybeCleanup() {
        if (++requestCounter < 10) {
            return; // don't bother doing this every time.
        }
        requestCounter = 0;
        final var now = Instant.now();
        final var benchmark = now.minusSeconds(timeOutSecs);
        if (lastCleanup.isBefore(benchmark)) {
            lastCleanup = now;
            final var iter = openFiles.entrySet().iterator();
            while (iter.hasNext()) {
                final var tf = iter.next().getValue();
                if (tf.timeStamp.isBefore(benchmark)) {
                    synchronized (tf.file) {
                        try {
                            tf.file.close();
                        } catch (Exception e) {
                            /* do nothing in this case */
                        }
                        iter.remove();
                    }
                }
            }
        }
    }
}
