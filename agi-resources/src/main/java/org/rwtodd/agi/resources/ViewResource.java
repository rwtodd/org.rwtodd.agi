package org.rwtodd.agi.resources;

/**
 * Represents an AGI View resource, and the capability to stream the contents of the
 * view to a client-supplied {@code Builder} object.
 * @author rwtodd
 */
public class ViewResource {
    
    public static interface Builder {
        
        void viewStart(int numLoops, String desc);
        void viewEnd();
               
        void loopStart(int loopNum, int numCells);
        void loopEnd();
        
        void cellStart(int cellNum, int w, int h, int transparentColor);
        void cellEnd();
        
        void setPixel(int x, int y, int color);
    }
    
    private final byte[] data;
    
    public ViewResource(final byte[] src) {
        data = src;
    }
    
    public void build(final Builder b) throws AGIException {
        
    }
}
