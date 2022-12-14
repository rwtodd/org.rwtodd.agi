package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiPic;
import org.rwtodd.agires.GameMetaData;

/**
 * The base class for all the various types of PIC resource pens.
 * @author Richard Todd
 */
sealed abstract class PicPen permits RectanglePen, CirclePen {
    /* the size class of the pen (0 to 7), as given in a PIC resource. */
    protected int size = 0;

    /**
     * Set the pen size.
     *
     * @param sz the size class (0 to 7)
     */
    public void setSize(final int sz) {
        if((sz < 0) || (sz > 7)) {
            throw new IllegalArgumentException("Bad size class for PIC resource pen!");
        }
        size = sz;
    }

    /**
     * Gets the width of the pen in pixels.
     *
     * @return the width
     */
    public int getWidth() {
        return size + 1;
    }

    /**
     * Gets the height of the pen in pixels.
     *
     * @return the height
     */
    public int getHeight() {
        return size * 2 + 1;
    }

    /**
     * The "center" of the plot is top+vericalOffset().
     *
     * @return the vertical offset
     */
    public int verticalOffset() {
        return size;
    }

    /**
     * the "center" of the plot is left + horizontalOffset().
     *
     * @return the horizontal offset
     */
    public int horizontalOffset() {
        return (size + 1) / 2;
    }

    /**
     * Determines how many initial pixels to skip in this row.
     *
     * @param rownum the row in question
     * @return how many initial pixels to skip
     */
    protected abstract int pixelsToSkip(int rownum);

    /**
     * Determines how many pixels to plot in this row
     *
     * @param rownum the row in question
     * @return how many pixels to plot
     */
    protected abstract int pixelsToPlot(int rownum);

    /**
     * Draw the pen to the handler centered at x,y.
     *
     * @param p the plotter
     * @param x x position of the pen
     * @param y y position of the pen
     * @param pattern the pattern of pixels to use with the pen
     */
    public void drawAt(final PicResource.Plotter p,
            int x, int y,
            final PenPattern pattern) {
        final int numRows = getHeight();
        final int numColumns = getWidth();

        // step 1, set the top, left so that the plot fits on screen.
        int left = x - horizontalOffset();
        if (left < 0) {
            left = 0;
        } else if ((left + numColumns) >= AgiPic.AGI_PIC_WIDTH) {
            left = AgiPic.AGI_PIC_WIDTH - numColumns;
        }

        int top = y - verticalOffset();
        if (top < 0) {
            top = 0;
        }
        if ((top + numRows) >= AgiPic.AGI_PIC_HEIGHT) {
            top = AgiPic.AGI_PIC_HEIGHT - numRows;
        }

        // step 2, go through the shape, drawing it.
        final var iterator = pattern.iterator();
        for (int row = 0; row < numRows; ++row) {
            final int skipped = pixelsToSkip(row), count = skipped + pixelsToPlot(row);
            for (int column = skipped; column < count; ++column) {
                if (iterator.hasNext() && iterator.next()) {
                    p.plotPoint(left + column, top + row);
                }
            }
        }
    }
}

/**
 * A rectangular pen.
 * @author Richard Todd
 */
final class RectanglePen extends PicPen {

    @Override
    protected int pixelsToSkip(int rownum) { return 0; }

    @Override
    protected int pixelsToPlot(int rownum) { return size+1; }

}


/**
 * A PicPen that draws circles.
 * @author rwtodd
 */
sealed class CirclePen extends PicPen permits V3CirclePen {

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
    protected int pixelsToPlot(int rownum) {
        return PLOTS[size][rownum];
    }
}

/**
 * AGI V3 draws slightly different circles of size 1
 *
 * @author rwtodd
 */
final class V3CirclePen extends CirclePen {

    @Override
    protected int pixelsToPlot(int rownum) {
        if((size == 1)&&(rownum!=1)) return 0;
        return super.pixelsToPlot(rownum);
    }
}
