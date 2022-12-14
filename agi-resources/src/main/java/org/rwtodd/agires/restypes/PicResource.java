package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.AgiPic;
import org.rwtodd.agires.GameMetaData;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Represents the AGI Picture (background). It does minimal interpretation and
 * validation of the resource, and works with a client-provided Handler to build
 * the representation of the picture needed by the client.
 *
 * @author Richard TOdd
 */
public class PicResource {

    class Plotter {
        void clear() {
            // AGI images always clear to white, with priority 4 everywhere
            Arrays.fill(picture.pixels(),(byte)15);
            Arrays.fill(priority.pixels(),(byte)4);
        }

        //0xF4: Draw a Y corner.
        //0xF5: Draw an X corner.
        //0xF7: Relative line (short lines).
        //0xF6: Absolute line (long lines).
        void line(int x1, int y1, int x2, int y2) {
            int height = y2 - y1, width = x2 - x1;
            int addY = 1, addX = 1;
            if (height < 0) {
                addY = -1;
                height = -height;
            }
            if (width < 0) {
                addX = -1;
                width = -width;
            }

            int i = width, threshold = width, errX = 0, errY = width / 2;
            if (height > width) {
                i = height;
                threshold = height;
                errX = height / 2;
                errY = 0;
            }
            int x = x1, y = y1;
            plotPoint(x, y);
            while (i-- > 0) {
                errY += height;
                if (errY >= threshold) {
                    errY -= threshold;
                    y += addY;
                }
                errX += width;
                if (errX >= threshold) {
                    errX -= threshold;
                    x += addX;
                }
                plotPoint(x, y);
            }
        }

        //0xF8: Fill.
        void fill(int x, int y) {
            record Point(int x, int y) {
                int getIndex() { return y * AgiPic.AGI_PIC_WIDTH + x; }
            };

            byte[] rasterCheck;
            byte searchingFor;
            if ((picColor != 15) && (picColor != -1)) {
                rasterCheck = picture.pixels();
                searchingFor = 15;
            } else if ((picColor == -1) && (priColor != -1) && (priColor != 4)) {
                rasterCheck = priority.pixels();
                searchingFor = 4;
            } else {
                return;  // nothing to do!
            }
            final var maxWid = AgiPic.AGI_PIC_WIDTH - 1;
            final var maxHt = AgiPic.AGI_PIC_HEIGHT - 1;

            final var queue = new ArrayDeque<Point>();
            Point p = new Point(x, y);

            while (p != null) {
                final int baseIndex = p.getIndex();
                if (rasterCheck[baseIndex] == searchingFor) {
                    plotPoint(p.x(), p.y());
                    if ((p.x() > 0) && (rasterCheck[baseIndex - 1] == searchingFor)) {
                        queue.add(new Point(p.x() - 1, p.y()));
                    }
                    if ((p.y() > 0) && (rasterCheck[baseIndex - AgiPic.AGI_PIC_WIDTH] == searchingFor)) {
                        queue.add(new Point(p.x(), p.y() - 1));
                    }
                    if ((p.x() < maxWid) && (rasterCheck[baseIndex + 1] == searchingFor)) {
                        queue.add(new Point(p.x() + 1, p.y()));
                    }
                    if ((p.y() < maxHt) && (rasterCheck[baseIndex + AgiPic.AGI_PIC_WIDTH] == searchingFor)) {
                        queue.add(new Point(p.x(), p.y() + 1));
                    }
                }
                p = queue.pollLast();
            }
        }

        //0xF9: Change pen size and style.
        //0xFA: Plot with pen.
        void plotPoint(int x, int y) {
            final int idx = y*AgiPic.AGI_PIC_WIDTH + x;
            if(picColor != -1) {
                picture.pixels()[idx] = picColor;
            }
            if(priColor != -1) {
                priority.pixels()[idx] = priColor;
            }
        }

    }

    /* the resource data bytes */
    private byte[] data;

    private AgiPic.Image picture;
    private AgiPic.Image priority;
    private final Plotter plotter;

    /* the current drawing colors, or -1 if not drawing */
    private byte picColor, priColor;

    private final PicPen rectanglePen; /* the pen we will use for rectangles */
    private final PicPen circlePen; /* the pen we will use for drawing circles */
    private PicPen currentPen; /* will point to either circlePen or rectanglePen */

    private final PenPattern splatterPattern; /* the splatter pattern we will use */
    private PenPattern currentPattern; /* the current pattern (either solid or splatter */

    public PicResource(GameMetaData meta) {
        data = null;
        picture = null;
        priority = null;
        picColor = -1;
        priColor = -1;
        plotter = new Plotter();
        rectanglePen = new RectanglePen();
        circlePen = (meta.isV3()) ? new V3CirclePen() : new CirclePen();
        currentPen = rectanglePen;
        currentPen.setSize(0);
        splatterPattern = new SplatterPattern();
        currentPattern = SolidPenPattern.INSTANCE;
    }

    public AgiPic build(final byte[] src) throws AgiException {
        try {
            // initial conditions...
            data = src;
            picture = new AgiPic.Image();
            priority = new AgiPic.Image();
            plotter.clear();
            picColor = -1; // -1 means nothing
            priColor = -1; // -1 means nothing

            // run through the pic data and draw...
            int idx = 0;
            while (idx < data.length) {
                switch (data[idx++] & 0xff) {
                    case 0xf0 ->
                        picColor = (byte)(data[idx++] & 0x0f);
                    case 0xf1 ->
                        picColor = -1;
                    case 0xf2 ->
                        priColor = (byte)(data[idx++] & 0x0f);
                    case 0xf3 ->
                        priColor = -1;
                    case 0xf4 ->
                        idx = drawCorners(true, idx);
                    case 0xf5 ->
                        idx = drawCorners(false, idx);
                    case 0xf6 ->
                        idx = drawLines(idx);
                    case 0xf7 ->
                        idx = drawRelativeLines(idx);
                    case 0xf8 ->
                        idx = drawFill(idx);
                    case 0xf9 ->
                        idx = getPen(idx);
                    case 0xfa ->
                        idx = drawPen(idx);
                    case 0xff -> {
                        if (idx != data.length) {
                            throw new AgiException("Extraneous data after end of PIC!");
                        }
                    }
                    default ->
                        throw new AgiException("Malformed PIC resource -- saw " + Byte.toString(data[idx - 1]));
                }
            }
            return new AgiPic(picture, priority);
        } catch (AgiException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AgiException("Error while parsing PIC", e);
        } finally {
            // be defensive and release our claim on this memory in case this is ever a long-lived, re-used
            // PicResource.
            picture = null;
            priority = null;
        }
    }

    private int drawCorners(boolean changeY, int idx) {
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
            plotter.line(x, y, x2, y2);
            drewLine = true;
            changeY = !changeY;
            x = x2;
            y = y2;
            nextCoord = data[++idx] & 0xff;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            plotter.plotPoint(x, y);
        }
        return idx;
    }

    private int drawLines(int idx) {
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
            plotter.line(x, y, x2, y2);
            drewLine = true;
            x = x2;
            y = y2;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            plotter.plotPoint(x, y);
        }
        return idx;
    }

    private int drawRelativeLines(int idx) {
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
            plotter.line(x, y, x2, y2);
            relmove = data[++idx] & 0xff;
            drewLine = true;
            x = x2;
            y = y2;
        }

        // if no lines were specified, at least draw the initial point.
        if (!drewLine) {
            plotter.plotPoint(x, y);
        }
        return idx;
    }

    private int drawFill(int idx) {
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
            plotter.fill(clipX(x), clipY(y));
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

    private int drawPen(int idx) {
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
            currentPen.drawAt(plotter, x, y, currentPattern);
        }
        return idx;
    }

    private int clipX(int x) {
        return (x < 0) ? 0 : ((x >= AgiPic.AGI_PIC_WIDTH) ? AgiPic.AGI_PIC_WIDTH - 1 : x);
    }

    private int clipY(int y) {
        return (y < 0) ? 0 : ((y >= AgiPic.AGI_PIC_HEIGHT) ? AgiPic.AGI_PIC_HEIGHT - 1 : y);
    }
}
