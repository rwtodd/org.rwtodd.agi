package org.rwtodd.agires;

import org.rwtodd.agires.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A disk-backed volume manager for V3 AGI game.
 *
 * @author Richard Todd
 */
class V3VolumeManager implements VolumeManager {
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
    public byte[] getResource(DirEntry de) throws AgiException, ResourceNotPresentException {
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
                        || ((header[2] & 0x7f) != de.getVolume())) {
                    throw new AgiException("Bad resource header for " + de);
                }
                pic_compressed = (header[2] & 0x80) != 0;

                // pull the resource bytes out of the file
                reslen = (header[3] & 0xff) | ((header[4] & 0xff) << 8);
                final var lzwlen = (header[5] & 0xff) | ((header[6] & 0xff) << 8);
                resource = new byte[lzwlen];
                raf.readFully(resource);
            } catch (IOException ioe) {
                throw new AgiException("Can't load resource " + de, ioe);
            }
        }
        // we have the resourcce now, decode it if necessary...
        if (reslen != resource.length) {
            if (pic_compressed) {
                resource = picExpand(resource, reslen);
            } else {
                resource = Util.lzwExpand(resource, reslen);
            }
        }
        return resource;
    }

    private static byte[] picExpand(final byte[] src, final int reslen) throws AgiException {
        final var expanded = new byte[reslen];

        final int lastSrcIdx = src.length;
        boolean unaligned = false; // we are reading half-bytes
        boolean nextHalf = false; // read only 4 bits nets
        int srcIdx = 0;
        for (int idx = 0; idx < reslen; ++idx) {
            // first, make sure we didn't overrun
            if (srcIdx >= lastSrcIdx) {
                throw new AgiException("Out of src bytes while expanding V3 PIC.");
            }

            if (nextHalf) {
                if (unaligned) {
                    // we are unaligned, and we only need 4 bits...
                    expanded[idx] = (byte) (src[srcIdx++] & 0x0f);
                } else {
                    expanded[idx] = (byte) ((src[srcIdx] & 0xf0) >> 4);
                }
                unaligned = !unaligned; // we changed our alignment by reading half a byte
                nextHalf = false; // won't read two half-bytes in a row.
            } else {
                // we need a full 8 bits...
                byte nxtByte;
                if (unaligned) {
                    // we need a full byte
                    int composedByte = (src[srcIdx++] & 0x0f) << 4;
                    if (srcIdx >= lastSrcIdx) {
                        throw new AgiException("Out of src bytes while expanding V3 PIC.");
                    }
                    composedByte |= ((src[srcIdx] & 0xf0) >> 4);
                    nxtByte = (byte) composedByte;
                } else {
                    // when aligned just take a byte
                    nxtByte = src[srcIdx++];
                }
                expanded[idx] = nxtByte;
                nextHalf = switch (nxtByte & 0xff) {
                    case 0xf0, 0xf2 ->
                        true;
                    default ->
                        false;
                };
            }

        }

        // check to see if we read the whole source
        if (srcIdx < (lastSrcIdx - 1)) {
            throw new AgiException("Extra source bytes when expanding PIC!");
        }

        return expanded;
    }

    @Override public byte[] getWordsData() throws IOException {
        return Files.readAllBytes(gamePath.resolve("WORDS.TOK"));
    }
    @Override public byte[] getObjectsData() throws IOException {
        return Files.readAllBytes(gamePath.resolve("OBJECT"));
    }
}
