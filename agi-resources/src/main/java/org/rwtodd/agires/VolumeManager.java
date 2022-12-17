package org.rwtodd.agires;

import java.io.Closeable;
import java.io.IOException;

/**
 * This is an interface for managing the stack of open volume files, ensuring
 * we only keep one file-handle per volume file at a time.
 *
 * @author Richard Todd
 */
public interface VolumeManager extends Closeable {

    /**
     * Create a default {@code VolumeManager} based on the provided
     * game metadata.  This also helps encapsulate our implementation choices,
     * as the client does not have a direct dependency on a particular
     * implementation.
     *
     * @param meta the game metadata
     * @return an appropriate default volume managaer
     */
    static VolumeManager createDefault(GameMetaData meta) {
      if (meta.isBeforeV3()) {
            return new V2VolumeManager(meta.getGamePath());
        } else {
            return new V3VolumeManager(meta.getGamePath(), meta.getPrefix());
        }
    }

    /**
     * Gets the raw data for the resource at a {@link DirEntry}.
     * @param de the directory entry for the wanted resource.  Any LZW decompression
     * is done here, and the version 3 PIC compression is undone at this level as well.
     *
     * @return the raw bytes of the resource.
     * @throws AgiException if there is a problem retrieving the bytes
     * @throws ResourceNotPresentException if the {@code de} param points to a non-existent resource.
     */
    byte[] getResource(DirEntry de) throws AgiException, ResourceNotPresentException;

    /**
     * Get the raw data of the {@code WORDS.TOK} file.
     *
     * @return the bytes of the file.
     * @throws IOException if there was a problem reading the file.
     */
    byte[] getWordsData() throws IOException;

    /**
     * Get the raw data of the {@code OBJECT} file.
     *
     * @return the raw bytes of the {@code OBJECT} file.
     * @throws IOException if there was a problem reading the file.
     */
    byte[] getObjectsData() throws IOException;
}
