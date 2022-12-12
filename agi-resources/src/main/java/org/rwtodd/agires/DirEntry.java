package org.rwtodd.agires;

/**
 * A DirEntry is a description of where a resource lives in the resource files.
 *
 * @author rwtodd
 */
public class DirEntry {

    private final byte volume;
    private final int offset;

    private DirEntry(byte volume, int offset) {
        this.volume = volume;
        this.offset = offset;
    }
    
    /**
     * Does this entry represent a resource that exists? Resources that are
     * volume 0xF, offset 0xFFFFF are not present.
     *
     * @return true for yes, false for no.
     */
    public boolean isPresent() {
        return (volume != 0x0f) || (offset != 0xfffff);
    }

    @Override
    public String toString() {
        if (isPresent()) {
            return String.format("Volume %d Offset %d", volume, offset);
        }
        return "Not Present";
    }

    /**
     * Get the volume where the resource lives.
     * @return the volume number.
     */
    int getVolume() { return (int)volume; }
    
    /**
     * Get the offset into the volume where the resource lives.
     * @return the offset.
     */
    int getOffset() { return offset; }
    
    /**
     * This is a directory entry representing a resource that does not exist
     * in the volume files.
     */
    public static DirEntry NON_EXISTENT = new DirEntry((byte)0x0f,0xfffff);
    
    /**
     * A factory method for DirEntries. It returns NON_EXISTENT when applicable,
     * instead of creating a new entry.
     * @param volume the volume number
     * @param offset the offect within the volume
     * @return the created DirEntry
     */
    public static DirEntry of(byte volume, int offset) {
        if (volume == 0x0f && offset == 0xfffff) {
            return NON_EXISTENT;
        }
        return new DirEntry(volume,offset);   
    }
}
