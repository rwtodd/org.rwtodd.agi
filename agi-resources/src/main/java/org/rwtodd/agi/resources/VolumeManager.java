package org.rwtodd.agi.resources;

import java.io.Closeable;

/**
 * This is an interface for managing the stack of open volume files, ensuring
 * we only keep one open volume file at a time.
 * @author rwtodd
 */
public interface VolumeManager extends Closeable {
    byte[] getResource(DirEntry de) throws AGIException, ResourceNotPresentException;
}
