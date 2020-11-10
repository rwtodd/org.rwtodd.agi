package org.rwtodd.agi.resources;

import java.util.Arrays;

/**
 * Represents an AGI View resource, and the capability to stream the contents of the
 * view to a client-supplied {@code Builder} object.
 * @author rwtodd
 */
public class ViewResource {
    
    /**
     * the AGI palette in RGBA order, 8-bit components.
     */
    private static byte[] agiPalette = new byte[]{
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff,
        (byte) 0x00, (byte) 0x00, (byte) 0xaa, (byte) 0xff,
        (byte) 0x00, (byte) 0xaa, (byte) 0x00, (byte) 0xff,
        (byte) 0x00, (byte) 0xaa, (byte) 0xaa, (byte) 0xff,
        (byte) 0xaa, (byte) 0x00, (byte) 0x00, (byte) 0xff,
        (byte) 0xaa, (byte) 0x00, (byte) 0xaa, (byte) 0xff,
        (byte) 0xaa, (byte) 0x55, (byte) 0x00, (byte) 0xff,
        (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xff,
        (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xff,
        (byte) 0x55, (byte) 0x55, (byte) 0xff, (byte) 0xff,
        (byte) 0x55, (byte) 0xff, (byte) 0x55, (byte) 0xff,
        (byte) 0x55, (byte) 0xff, (byte) 0xff, (byte) 0xff,
        (byte) 0xff, (byte) 0x55, (byte) 0x55, (byte) 0xff,
        (byte) 0xff, (byte) 0x55, (byte) 0xff, (byte) 0xff,
        (byte) 0xff, (byte) 0xff, (byte) 0x55, (byte) 0xff,
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff
    };

    
    /**
     * Create an AGI palette with the designated color turned transparent.
     * @param transparentColor the index of the transparent color
     * @return the derived palette data
     */
    protected byte[] createCellPalette(int transparentColor) {
        final var derived = Arrays.copyOf(agiPalette, agiPalette.length);
        derived[transparentColor*4+3] = 0x00;
        return derived;
    }
    
    public static interface Builder {
        void viewStart(int viewNum, int numLoops, String desc);
        void viewEnd();
               
        void loopStart(int loopNum, int numCells);
        void loopEnd();
        
        void cellStart(int cellNum, int w, int h, byte[] palette);
        void cellEnd();
        void mirroredCell(int cellNum, int fwdLoop);
        
        void setPixel(int x, int y, int color);
    }
    
    protected final byte[] data;
    protected final int viewNum;
    
    public ViewResource(int viewNumber, final byte[] src) {
        viewNum = viewNumber;
        data = src;
    }
    
    public void build(final Builder b) throws AGIException {
        final int loopCount = data[2]&0xff;
        final int descLoc = (data[3]&0xff)|((data[4]&0xff)<<8);
        int loopDirectoryIdx = 5;
        
        final String desc = (descLoc > 0)?Util.asciizString(data, descLoc):null;
        
        b.viewStart(viewNum, loopCount, desc);
        
        // now parse each loop
        for(int loop = 0; loop < loopCount; ++loop) {
            final int loopOffset = (data[loopDirectoryIdx]&0xff)|((data[loopDirectoryIdx+1]&0xff)<<8);
            loopDirectoryIdx += 2;
            final int cellCount = data[loopOffset]&0xff;
            int cellDirectoryIdx = loopOffset+1;
            b.loopStart(loop, cellCount);
            
            // now parse each cell in each loop...
            for(int cell = 0; cell < cellCount; ++cell) {
                int idx = loopOffset + ((data[cellDirectoryIdx]&0xff)|((data[cellDirectoryIdx+1]&0xff)<<8));
                cellDirectoryIdx += 2;
                final int cellWidth = data[idx++]&0xff;
                final int cellHeight = data[idx++]&0xff;
                final int transparentColor = data[idx]&0x0f;
                final int mirroring = (data[idx++]&0xf0)>>4;
                
                if(((mirroring & 0x08) == 0x08) && ((mirroring & 0x07) != loop)) {
                    // we have a "mirrored" cell... just tell the builder about it.
                    b.mirroredCell(cell, mirroring&0x07);
                } else {
                    // we have a "foward" cell... decode it
                    b.cellStart(cell, cellWidth, cellHeight, createCellPalette(transparentColor));
                
                    for(int y = 0; y < cellHeight; ++y) {
                        int x = 0;
                        while(true) {
                            final int nextB = data[idx++]&0xff;
                            int count = nextB&0x0f;
                            final int color = nextB>>4;
                            if(nextB == 0) {
                                // fill the rest of the line in with transparent...
                                for(;x < cellWidth;++x) b.setPixel(x, y, transparentColor);
                                break;
                            } else {
                                while(count-- > 0) {
                                    if(x == cellWidth) break;
                                    b.setPixel(x++, y, color);
                                }
                            }
                        }
                    }
                
                    b.cellEnd();
                }
            }
            
            b.loopEnd();
        }
        
        b.viewEnd();
    }
}
