package org.rwtodd.agi.resources;

/**
 * A PicPen that draws circles.
 * @author rwtodd
 */
public class CirclePen extends PicPen {

    private static final int[][] SKIPS = new int[][] {
        {0},
        {0,0,0},
        {1,0,0,0,1},
        {1,1,0,0,0,1,1},
        {2,1,0,0,0,0,0,1,2},
        {2,1,1,1,0,0,0,1,1,1,2},
        {2,1,1,1,0,0,0,0,0,1,1,1,2},
        {3,2,1,1,1,0,0,0,0,0,1,1,1,2,3}
    };
    
    private static final int[][] PLOTS = new int[][] {
        {1},
        {2,2,2},
        {1,3,3,3,1},
        {2,2,3,3,3,2,2},
        {1,3,5,5,5,5,5,3,1},
        {2,4,4,4,6,6,6,4,4,4,2},
        {3,5,5,5,7,7,7,7,7,5,5,5,3},
        {2,4,6,6,6,8,8,8,8,8,6,6,6,4,2},
    };
    
    public CirclePen(int sz) {
        super(sz);
    }
    
    @Override
    protected int pixelsToSkip(int rownum) {
        return SKIPS[size][rownum];
    }

    @Override
    protected int pixelstoPlot(int rownum) {
        return PLOTS[size][rownum];
    }
}
