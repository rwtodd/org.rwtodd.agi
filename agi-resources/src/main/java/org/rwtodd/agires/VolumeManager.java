package org.rwtodd.agires;

import java.io.Closeable;
import java.io.IOException;

/**
 * This is an interface for managing the stack of open volume files, ensuring
 * we only keep one file-handle per volume file at a time.
 * @author rwtodd
 */
public interface VolumeManager extends Closeable {
    static VolumeManager createDefault(GameMetaData meta) {
      if (meta.isBeforeV3()) {
            return new V2VolumeManager(meta.getGamePath());
        } else {
            return new V3VolumeManager(meta.getGamePath(), meta.getPrefix());
        }
    }
        
    byte[] getResource(DirEntry de) throws AgiException, ResourceNotPresentException;
    byte[] getWordsData() throws IOException;
    byte[] getObjectsData() throws IOException;
}
