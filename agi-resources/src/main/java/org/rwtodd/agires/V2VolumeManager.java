package org.rwtodd.agires;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A disk-backed volume manager for V1 and V2 AGI game.
 *
 * @author Richard Todd
 */
class V2VolumeManager implements VolumeManager {
    private final FileCache fc;
    private final Path gamePath;

    public V2VolumeManager(Path gamePath) {
        this.gamePath = gamePath;
        fc = new FileCache(10);
    }

    @Override
    public void close() {
        fc.close();
    }
            
    @Override
    public byte[] getResource(DirEntry de) throws AgiException, ResourceNotPresentException {
        if (!de.isPresent()) {
            throw new ResourceNotPresentException("Asking for resource that doesn't exist!");
        }

        final var volPath = gamePath.resolve("VOL." + de.getVolume());
        final var raf = fc.getFile(volPath);
        synchronized (raf) {
            try {
                final var header = new byte[5];
                raf.seek(de.getOffset());
                raf.readFully(header);
                if ((header[0] != 0x12)
                        || (header[1] != 0x34) 
                        || (header[2] != de.getVolume())) {
                    throw new AgiException("Bad resource header for " + de);
                }

                // second, pull the resource bytes out of the file
                final var reslen = (header[3]&0xff) | ((header[4]&0xff) << 8);
                final var resource = new byte[reslen];
                raf.readFully(resource);
                return resource;
            } catch (IOException ioe) {
                throw new AgiException("Can't load resource " + de, ioe);
            }
        }
    }

    @Override public byte[] getWordsData() throws IOException {
        return Files.readAllBytes(gamePath.resolve("WORDS.TOK"));
    }
    @Override public byte[] getObjectsData() throws IOException {
        return Files.readAllBytes(gamePath.resolve("OBJECT"));
    }
}
