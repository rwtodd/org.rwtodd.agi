package org.rwtodd.agires;

public record AgiPic(Image picture, Image priority) {
    public static final int AGI_PIC_WIDTH = 160;
    public static final int AGI_PIC_HEIGHT = 168;

    public record Image(byte[] pixels) {
        public Image() {
            this(new byte[AgiPic.AGI_PIC_WIDTH * AgiPic.AGI_PIC_HEIGHT]);
        }

        public Image {
            if (pixels.length != AgiPic.AGI_PIC_HEIGHT * AgiPic.AGI_PIC_WIDTH)
                throw new IllegalArgumentException("AgiPic.Image pixels of bad length " + pixels.length);
        }

        public int getWidth() { return AgiPic.AGI_PIC_WIDTH; }
        public int getHeight() { return AgiPic.AGI_PIC_HEIGHT; }
    }

    /**
     * Get the priority at an index into the pixel buffer.
     * @param idx the index into the pixel buffer
     * @return the priority of the pixel.
     */
    public byte priorityAtIndex(int idx) {
        final var p = priority.pixels();
        byte answer = p[idx];
        if(answer >= 4) return answer;

        // it was a control line, so search for the right priority
        while(answer < 4 && idx < (AGI_PIC_HEIGHT*AGI_PIC_WIDTH)) {
            idx += AGI_PIC_WIDTH;
            answer = p[idx];
        }
        return (answer >= 4) ? answer : (byte)15;
    }

    /**
     * Get the priority at an {@code (x,y)} location in the pixel buffer.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the priority
     */
    public byte priorityAtPixel(int x, int y) {
        return priorityAtIndex(y*AGI_PIC_WIDTH + x);
    }
}
