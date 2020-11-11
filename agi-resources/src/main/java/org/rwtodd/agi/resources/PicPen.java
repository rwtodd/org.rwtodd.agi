package org.rwtodd.agi.resources;

/**
 *
 * @author rwtodd
 */
public abstract class PicPen {

    /* the size class of the pen (0 to 7), as given in a PIC resource. */
    protected final int size;

    /**
     * Create a pen of a given size
     *
     * @param sz the size class (0 to 7)
     */
    protected PicPen(final int sz) {
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
    protected abstract int pixelstoPlot(int rownum);

    /**
     * Draw the pen to the handler centered at x,y.
     *
     * @param h the handler
     * @param x x position of the pen
     * @param y y position of the pen
     * @param picColor the picture color to use for the pen
     * @param priColor the priority color to use for the pen
     * @param pattern the pattern of pixels to use with the pen
     */
    public void drawAt(final PicResource.Builder h,
            int x, int y,
            int picColor, int priColor,
            final PenPattern pattern) {
        // step 1, set the top, left so that the plot fits on screen.  This is what
        // scummvm does.
        final int numRows = getHeight();
        final int numColumns = getWidth();

        int left = x - horizontalOffset();
        if (left < 0) {
            left = 0;
        } else if ((left + numColumns) >= PicResource.PIC_DIMENSIONS.width) {
            left = PicResource.PIC_DIMENSIONS.width - numColumns;
        }

        int top = y - verticalOffset();
        if (top < 0) {
            top = 0;
        }
        if ((top + numRows) >= PicResource.PIC_DIMENSIONS.height) {
            top = PicResource.PIC_DIMENSIONS.height - numRows;
        }

        // step 2, go through the shape, drawing it.
        final var iterator = pattern.iterator();
        for (int row = 0; row < numRows; ++row) {
            final int skipped = pixelsToSkip(row), count = skipped + pixelstoPlot(row);
            for (int column = skipped; column < count; ++column) {
                if (iterator.hasNext() && iterator.next()) {
                    h.plotPoint(left + column, top + row, picColor, priColor);
                }
            }
        }
    }
}
