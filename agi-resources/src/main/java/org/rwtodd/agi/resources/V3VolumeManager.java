package org.rwtodd.agi.resources;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A disk-backed volume manager for V3 AGI game.
 * @author rwtodd
 */
public class V3VolumeManager implements VolumeManager {

    private final FileCache fc;
    private final Path gamePath;
    private final String prefix;

    public V3VolumeManager(final Path gamePath, final String prefix) {
        this.gamePath = gamePath;
        this.prefix = prefix + "VOL.";
        fc = new FileCache(10);
    }

    @Override
    public void close() {
        fc.close();
    }
            
    @Override
    public byte[] getResource(DirEntry de) throws AGIException, ResourceNotPresentException {
        if (!de.isPresent()) {
            throw new ResourceNotPresentException("Asking for resource that doesn't exist!");
        }

        final var volPath = gamePath.resolve(prefix + de.getVolume());
        final var raf = fc.getFile(volPath);
        byte[] resource;
        boolean pic_compressed;
        int reslen;
        synchronized (raf) {
            try {
                final var header = new byte[7];
                raf.seek(de.getOffset());
                raf.readFully(header);
                if ((header[0] != 0x12)
                        || (header[1] != 0x34) 
                        || ((header[2]&0x7f) != de.getVolume())) {
                    throw new AGIException("Bad resource header for " + de);
                }
                pic_compressed = (header[2]&0x80) != 0;
                
                // pull the resource bytes out of the file
                reslen = (header[3]&0xff) | ((header[4]&0xff) << 8);
                final var lzwlen = (header[5]&0xff) | ((header[6]&0xff) << 8);
                resource = new byte[lzwlen];
                raf.readFully(resource);                
            } catch (IOException ioe) {
                throw new AGIException("Can't load resource " + de, ioe);
            }
        }
        // we have the resourcce now, decode it if necessary...
        if(pic_compressed) {
            throw new UnsupportedOperationException("TODO: V3 PIC Compression...");
        }
        if(reslen != resource.length) {
            resource = Util.lzwExpand(resource, reslen);
        }
        return resource;
    }
    
}
