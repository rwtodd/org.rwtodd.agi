package org.rwtodd.agi.resources;

/**
 * A DirEntry is a description of where a resource lives in the resource files.
 *
 * @author rwtodd
 */
public class DirEntry {

    private final byte volume;
    private final int offset;

    public DirEntry(byte volume, int offset) {
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
            return String.format("Volumd %d Offset %d", volume, offset);
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
    
}
