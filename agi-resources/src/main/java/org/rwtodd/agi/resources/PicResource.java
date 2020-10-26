package org.rwtodd.agi.resources;

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

    public static interface Handler {

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
        void fill(int x, int y,  int picColor, int priColor);

        //0xF9: Change pen size and style.
        //0xFA: Plot with pen.
        void plotPoint(int x, int y, int picColor, int priColor);

        // 0xFF
        void endPicture();

    }

    private final byte[] data;

    public PicResource(final byte[] src) {
        data = src;
    }

    public void streamToHandler(final Handler h) throws AGIException {
        try {
            int picColor = -1; // -1 means nothing
            int priColor = -1; // -1 means nothing

            h.startPicture(160, 168 /* NOT 200! */, 15, 4);

            int idx = 0;
            while (idx < data.length) {
                switch (data[idx++] & 0xff) {
                    case 0xf0 ->
                        picColor = (data[idx++]&0xff);
                    case 0xf1 ->
                        picColor = -1;
                    case 0xf2 ->
                        priColor = (data[idx++]&0xff);
                    case 0xf3 ->
                        priColor = -1;
                    case 0xf4 ->
                        idx = drawCorners(h, picColor, priColor, true, idx);
                    case 0xf5 ->
                        idx = drawCorners(h, picColor, priColor, false, idx);
                    case 0xf6 ->
                        idx = drawLines(h, picColor, priColor, idx);
                    case 0xf7 ->
                        idx = drawRelativeLines(h, picColor, priColor, idx);
                    case 0xf8 ->
                        idx = drawFill(h, picColor, priColor, idx);
                    case 0xf9 ->
                        ++idx;  // TODO: actually implement
                    case 0xfa -> {
                        // TODO: actually implement
                        while ((data[idx] & 0xf0) != 0xf0) {
                            ++idx;
                        }
                    }
                    case 0xff -> {
                        if (idx != data.length) {
                            throw new AGIException("Extraneous data after end of PIC!");
                        }
                        h.endPicture();
                    }
                    default ->
                        throw new AGIException("Malformed PIC resource -- saw " + Byte.toString(data[idx - 1]));

                }
            }
        } catch (AGIException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AGIException("Error while parsing PIC", e);
        }
    }

    private int drawCorners(final Handler h, int picColor, int priColor, boolean changeY, int idx) {
        // first, we get the start coords...
        int x = data[idx++] & 0xff;
        if((x & 0xf0) == 0xf0) return idx-1;
        int y = data[idx++] & 0xff;
        if((y & 0xf0) == 0xf0) return idx-1;
        
        int x2 = x;
        int y2 = y;

        h.plotPoint(x, y, picColor, priColor);
        
        // now, read corners and update....
        int nextCoord = data[idx] & 0xff;
        while ((nextCoord & 0xf0) != 0xf0) {
            if (changeY) {
                y2 = nextCoord;
            } else {
                x2 = nextCoord;
            }
            h.line(x, y, x2, y2, picColor, priColor);
            changeY = !changeY;
            x = x2;
            y = y2;
            nextCoord = data[++idx] & 0xff;
        }
        return idx;
    }

    private int drawLines(Handler h, int picColor, int priColor, int idx) {
        // first, we get the start coords...
        int x = data[idx++] & 0xff;
        if((x & 0xf0) == 0xf0) return idx-1;
        int y = data[idx++] & 0xff;
        if((y & 0xf0) == 0xf0) return idx-1;
       
        h.plotPoint(x, y, picColor, priColor);
        
        // now, read endpoints and draw....
        while ((data[idx] & 0xf0) != 0xf0) {
            int x2 = data[idx++] & 0xff;
            int y2 = data[idx++] & 0xff;
            h.line(x, y, x2, y2, picColor, priColor);
            x = x2;
            y = y2;
        }
        return idx;
    }

    private int drawRelativeLines(Handler h, int picColor, int priColor, int idx) {
        // first, we get the start coords...
        int x = data[idx++] & 0xff;
        if((x & 0xf0) == 0xf0) return idx-1;
        int y = data[idx++] & 0xff;
        if((y & 0xf0) == 0xf0) return idx-1;

        h.plotPoint(x, y, picColor, priColor);
        
        // now, read relative moves
        int relmove = data[idx] & 0xff;
        while ((relmove & 0xf0) != 0xf0) {
            int x2 = x + (((relmove & 0x80) == 0x80) ? -1 : 1) * ((relmove >> 4) & 0x7);
            int y2 = y + (((relmove & 0x08) == 0x08) ? -1 : 1) * (relmove & 0x7);
            h.line(x, y, x2, y2, picColor, priColor);
            relmove = data[++idx] & 0xff;
            x = x2;
            y = y2;
        }
        return idx;
    }

    private int drawFill(Handler h, int picColor, int priColor, int idx) {
        // read points and fill...
        while ((data[idx] & 0xf0) != 0xf0) {
            int x = data[idx++] & 0xff;
            int y = data[idx++] & 0xff;
            h.fill(x, y, picColor, priColor);
        }
        return idx;
    }
}
