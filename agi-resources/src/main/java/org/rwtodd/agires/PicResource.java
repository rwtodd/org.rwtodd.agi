package org.rwtodd.agires;

/**
 * Represents the AGI Picture (background). It does minimal interpretation and
 * validation of the resource, and works with a client-provided Handler to build
 * the representation of the picture needed by the client.
 *
 * @author rwtodd
 */
public class PicResource {

    /**
     * the AGI palette in RGBA order, 8-bit components.
     */
    public static byte[] agiPalette = new byte[]{
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

    static final java.awt.Dimension PIC_DIMENSIONS = new java.awt.Dimension(160, 168);

    public static interface Builder {

        void startPicture(int sizeX, int sizeY, int picColor, int priColor);

        //0xF0: Change Picture color and enable Picture draw.
        //0xF1: Disable Picture draw.
        //0xF2: Change priority color and enable priority draw.
        //0xF3: Disable priority draw.
        //0xF4: Draw a Y corner.
        //0xF5: Draw an X corner.
        //0xF7: Relative line (short lines).
        //0xF6: Absolute line (long lines).
        void line(int x1, int y1, int x2, int y2, int picColor, int priColor);

        //0xF8: Fill.
        void fill(int x, int y, int picColor, int priColor);

        //0xF9: Change pen size and style.
        //0xFA: Plot with pen.
        void plotPoint(int x, int y, int picColor, int priColor);

        // 0xFF
        void endPicture();

    }

    private final byte[] data;
    /* the resource data bytes */

    private final PicPen rectanglePen; /* the pen we will use for rectangles */
    private final PicPen circlePen; /* the pen we will use for drawing circles */
    private PicPen currentPen; /* will point to either circlePen or rectanglePen */

    private final PenPattern splatterPattern; /* the splatter pattern we will use */
    private PenPattern currentPattern; /* the current pattern (either solid or splatter */

    PicResource(final byte[] src, final PicPen rectPen, final PicPen circPen) {
        data = src;
        rectanglePen = rectPen;
        circlePen = circPen;
        currentPen = rectanglePen;
        currentPen.setSize(0);
        splatterPattern = new SplatterPattern();
        currentPattern = SolidPenPattern.INSTANCE;
    }

    public void build(final Builder b) throws AgiException {
        try {
            int picColor = -1; // -1 means nothing
            int priColor = -1; // -1 means nothing

            b.startPicture(PIC_DIMENSIONS.width, PIC_DIMENSIONS.height, 15, 4);

            int idx = 0;
            while (idx < data.length) {
                switch (data[idx++] & 0xff) {
                    case 0xf0 ->
                        picColor = (data[idx++] & 0xff);
                    case 0xf1 ->
                        picColor = -1;
                    case 0xf2 ->
                        priColor = (data[idx++] & 0xff);
                    case 0xf3 ->
                        priColor = -1;
                    case 0xf4 ->
                        idx = drawCorners(b, picColor, priColor, true, idx);
                    case 0xf5 ->
                        idx = drawCorners(b, picColor, priColor, false, idx);
                    case 0xf6 ->
                        idx = drawLines(b, picColor, priColor, idx);
                    case 0xf7 ->
                        idx = drawRelativeLines(b, picColor, priColor, idx);
                    case 0xf8 ->
                        idx = drawFill(b, picColor, priColor, idx);
                    case 0xf9 ->
                        idx = getPen(idx);
                    case 0xfa ->
                        idx = drawPen(b, picColor, priColor, idx);
                    case 0xff -> {
                        if (idx != data.length) {
                            throw new AgiException("Extraneous data after end of PIC!");
                        }
                        b.endPicture();
                    }
                    default ->
                        throw new AgiException("Malformed PIC resource -- saw " + Byte.toString(data[idx - 1]));

                }
            }
        } catch (AgiException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AgiException("Error while parsing PIC", e);
        }
    }

    private int drawCorners(final Builder b, int picColor, int priColor, boolean changeY, int idx) {
        // first, we get the start coords...
        int x = data[idx++] & 0xff;
        if (x >= 0xf0) {
            return idx - 1;
        }
        int y = data[idx++] & 0xff;
        if (y >= 0xf0) {
            return idx - 1;
        }
        x = clipX(x);
        y = clipY(y);

        int x2 = x;
        int y2 = y;
        boolean drewLine = false;

        // now, read corners and update....
        int nextCoord = data[idx] & 0xff;
        while (nextCoord < 0xf0) {
            if (changeY) {
                y2 = clipY(nextCoord);
            } else {
                x2 = clipX(nextCoord);
            }
            b.line(x, y, x2, y2, picColor, priColor);
            drewLine = true;
            changeY = !changeY;
            x = x2;
            y = y2;
            nextCoord = data[++idx] & 0xff;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            b.plotPoint(x, y, picColor, priColor);
        }
        return idx;
    }

    private int drawLines(Builder b, int picColor, int priColor, int idx) {
        // first, we get the start coords...
        int x = data[idx++] & 0xff;
        if (x >= 0xf0) {
            return idx - 1;
        }
        x = clipX(x);

        int y = data[idx++] & 0xff;
        if (y >= 0xf0) {
            return idx - 1;
        }
        boolean drewLine = false;
        y = clipY(y);

        // now, read endpoints and draw....
        while (true) {
            int x2 = data[idx++] & 0xff;
            if (x2 >= 0xf0) {
                --idx;
                break;
            }
            x2 = clipX(x2);
            int y2 = data[idx++] & 0xff;
            if (y2 >= 0xf0) {
                --idx;
                break;
            }
            y2 = clipY(y2);
            b.line(x, y, x2, y2, picColor, priColor);
            drewLine = true;
            x = x2;
            y = y2;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            b.plotPoint(x, y, picColor, priColor);
        }
        return idx;
    }

    private int drawRelativeLines(Builder b, int picColor, int priColor, int idx) {
        // first, we get the start coords...
        boolean drewLine = false;
        int x = data[idx++] & 0xff;
        if (x >= 0xf0) {
            return idx - 1;
        }
        x = clipX(x);

        int y = data[idx++] & 0xff;
        if (y >= 0xf0) {
            return idx - 1;
        }
        y = clipY(y);

        // now, read relative moves
        int relmove = data[idx] & 0xff;
        while (relmove < 0xf0) {
            final int x2 = clipX(x + (((relmove & 0x80) == 0x80) ? -1 : 1) * ((relmove >> 4) & 0x7));
            final int y2 = clipY(y + (((relmove & 0x08) == 0x08) ? -1 : 1) * (relmove & 0x7));
            b.line(x, y, x2, y2, picColor, priColor);
            relmove = data[++idx] & 0xff;
            drewLine = true;
            x = x2;
            y = y2;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            b.plotPoint(x, y, picColor, priColor);
        }
        return idx;
    }

    private int drawFill(Builder b, int picColor, int priColor, int idx) {
        // read points and fill...
        while (true) {
            final int x = data[idx++] & 0xff;
            if (x >= 0xf0) {
                --idx;
                break;
            }
            final int y = data[idx++] & 0xff;
            if (y >= 0xf0) {
                --idx;
                break;
            }
            b.fill(clipX(x), clipY(y), picColor, priColor);
        }
        return idx;
    }

    private int getPen(int idx) {
        final int arg = data[idx++] & 0xff;
        final int size = arg & 0x7;

        currentPen = ((arg & 0x10) == 0) ? circlePen : rectanglePen;
        currentPen.setSize(size);
        currentPattern = ((arg & 0x20) == 0) ? SolidPenPattern.INSTANCE : splatterPattern;
        return idx;
    }

    private int drawPen(Builder b, int picColor, int priColor, int idx) {
        // read points and fill...
        while (true) {
            if (currentPattern.takesArgument()) {
                final int pattNumber = data[idx++] & 0xff;
                if (pattNumber >= 0xf0) {
                    --idx;
                    break;
                }
                currentPattern.setPattern(pattNumber);
            }
            final int x = data[idx++] & 0xff;
            if (x >= 0xf0) {
                --idx;
                break;
            }
            final int y = data[idx++] & 0xff;
            if (y >= 0xf0) {
                --idx;
                break;
            }
            currentPen.drawAt(b, x, y, picColor, priColor, currentPattern);
        }
        return idx;
    }

    private int clipX(int x) {
        return (x < 0) ? 0 : ((x >= PIC_DIMENSIONS.width) ? PIC_DIMENSIONS.width - 1 : x);
    }

    private int clipY(int y) {
        return (y < 0) ? 0 : ((y >= PIC_DIMENSIONS.height) ? PIC_DIMENSIONS.height - 1 : y);
    }
}
