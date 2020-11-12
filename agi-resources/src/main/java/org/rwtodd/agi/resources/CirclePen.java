package org.rwtodd.agi.resources;

/**
 * A PicPen that draws circles.
 * @author rwtodd
 */
class CirclePen extends PicPen {

    private static final int[][] SKIPS = new int[][] {
        {0},  // width = 1
        {0,0,0}, // width = 2
        {1,0,0,0,1}, // width = 3
        {1,1,0,0,0,1,1}, // width= 4
        {2,1,0,0,0,0,0,1,2}, //width = 5
        {2,1,1,1,0,0,0,1,1,1,2}, // width = 6
        {2,1,1,1,0,0,0,0,0,1,1,1,2}, //width = 7
        {3,2,1,1,1,0,0,0,0,0,1,1,1,2,3} // width = 8
    };
    private static final int[][] PLOTS = new int[][] {
        {1},   // width = 1
        {2,2,2}, //width=2
        {1,3,3,3,1}, //width=3
        {2,2,4,4,4,2,2},  //*width=4
        {1,3,5,5,5,5,5,3,1}, //width=5
        {2,4,4,4,6,6,6,4,4,4,2}, //width=6
        {3,5,5,5,7,7,7,7,7,5,5,5,3}, //width=7
        {2,4,6,6,6,8,8,8,8,8,6,6,6,4,2}, //width=8
    };
    
   
    @Override
    protected int pixelsToSkip(int rownum) {
        return SKIPS[size][rownum];
    }

    @Override
    protected int pixelstoPlot(int rownum) {
        return PLOTS[size][rownum];
    }
}
