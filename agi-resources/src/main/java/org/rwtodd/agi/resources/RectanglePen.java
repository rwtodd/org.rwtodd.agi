package org.rwtodd.agi.resources;

/**
 * A rectangular pen.
 * @author rwtodd
 */
public class RectanglePen extends PicPen {

    public RectanglePen(int sz) {
        super(sz);
    }
    
    @Override
    protected int pixelsToSkip(int rownum) { return 0; }

    @Override
    protected int pixelstoPlot(int rownum) { return size+1; }
    
}
