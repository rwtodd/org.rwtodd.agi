package org.rwtodd.agires;

/**
 * A rectangular pen.
 * @author rwtodd
 */
class RectanglePen extends PicPen {

    @Override
    protected int pixelsToSkip(int rownum) { return 0; }

    @Override
    protected int pixelstoPlot(int rownum) { return size+1; }
    
}
