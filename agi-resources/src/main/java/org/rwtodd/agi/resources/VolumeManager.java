package org.rwtodd.agi.resources;

/**
 * This is an interface for managing the stack of open volume files, ensuring
 * we only keep one open volume file at a time.
 * @author rwtodd
 */
public interface VolumeManager {
    byte[] getResource(DirEntry de) throws AGIException;
}
