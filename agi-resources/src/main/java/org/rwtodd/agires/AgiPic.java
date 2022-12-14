package org.rwtodd.agires;

public record AgiPic(Image picture, Image priority) {
    public static final int AGI_PIC_WIDTH = 160;
    public static final int AGI_PIC_HEIGHT = 168;

    public record Image(byte[] pixels) {
        public Image() {
            this(new byte[AgiPic.AGI_PIC_WIDTH * AgiPic.AGI_PIC_HEIGHT]);
        }
        public Image(byte[] pixels)  {
            if(pixels.length != AgiPic.AGI_PIC_HEIGHT*AgiPic.AGI_PIC_WIDTH)
                throw new IllegalArgumentException("AgiPic.Image pixels of bad length " + pixels.length);
            this.pixels = pixels;
        }

        public int getWidth() { return AgiPic.AGI_PIC_WIDTH; }
        public int getHeight() { return AgiPic.AGI_PIC_HEIGHT; }
    }
}
