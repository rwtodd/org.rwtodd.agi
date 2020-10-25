package org.rwtodd.agi.resources;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * A PicResource.Handler that draws BufferedImages.
 *
 * @author rwtodd
 */
public class BufferedImagePicHandler implements PicResource.Handler {

    private BufferedImage pictureImage;
    private WritableRaster picRaster;
    private BufferedImage priorityImage;
    private WritableRaster priRaster;
    private final byte[] palette;

    public BufferedImagePicHandler(final byte[] palette) {
        pictureImage = null;
        priorityImage = null;
        this.palette = palette;
    }

    public BufferedImagePicHandler() {
        this(PicResource.agiPalette);
    }

    public BufferedImage getPictureImage() {
        return pictureImage;
    }

    public BufferedImage getPriorityImage() {
        return priorityImage;
    }

    @Override
    public void startPicture(int sizeX, int sizeY, int picColor, int priColor) {
        final var cm = new IndexColorModel(4, 16, palette, 0, true);
        pictureImage = new BufferedImage(
                sizeX,
                sizeY,
                BufferedImage.TYPE_BYTE_BINARY,
                cm);
        picRaster = pictureImage.getRaster();
        clearImage(picRaster, (byte) 15);
        priorityImage = new BufferedImage(
                sizeX,
                sizeY,
                BufferedImage.TYPE_BYTE_BINARY,
                cm);
        priRaster = priorityImage.getRaster();
        clearImage(priRaster, (byte) 4);
    }

    /**
     * Clears the given bufferedImage to the selected color.
     *
     * @param bi the buffered image
     * @param colorIndex the index of the color to use for clearing
     */
    private static void clearImage(final WritableRaster raster, byte colorIndex) {
        final var line = new byte[raster.getWidth()];
        Arrays.fill(line, colorIndex);
        final var maxY = raster.getHeight();
        for (int y = 0; y < maxY; ++y) {
            raster.setDataElements(0, y, line.length, 1, line);
        }
    }

    /**
     * this is a silly rounding method, but it is necessary.
     *
     * @param num the number to round
     * @param dir the direction we are heading
     * @return the rounded number.
     */
    private static int roundLikeAGI(float num, float dir) {
        final int floored = (int) num;
        final float dist = num - (int) num;
        if (dir < 0) {
            return (dist <= 0.501) ? floored : (floored + 1);
        }
        return (dist < 0.499) ? floored : (floored + 1);
    }

    @Override
    public void line(final int x1, final int y1,
            final int x2, final int y2,
            final int picColor, final int priColor) {
        final int height = y2 - y1, width = x2 - x1;

        if (Math.abs(width) > Math.abs(height)) {
            float y = y1;
            final float addY = (float) height / Math.abs(width);
            final int addX = Integer.signum(width);
            for (int x = x1; x != x2; x += addX, y += addY) {
                plotPoint(x, roundLikeAGI(y, addY), picColor, priColor);
            }
        } else {
            float x = x1;
            final float addX = (height == 0) ? 0.0f : ((float) width / Math.abs(height));
            final int addY = Integer.signum(height);
            for (int y = y1; y != y2; y += addY, x += addX) {
                plotPoint(roundLikeAGI(x, addX), y, picColor, priColor);
            }
        }
        plotPoint(x2, y2, picColor, priColor);
    }

    private static class Point {
        final int px;
        final int py;
        Point(int x, int y) { px = x; py = y; }
    }
    
    @Override
    public void fill(int x, int y, int picColor, int priColor) {
        Raster rasterCheck;
        int searchingFor;
        if((picColor != 15) && (picColor != -1)) {
            rasterCheck = picRaster;
            searchingFor = 15;
        } else if((picColor == -1) && (priColor != -1) && (priColor != 4)) {
            rasterCheck = priRaster;
            searchingFor = 4;
        } else {
            return;  // nothing to do!
        }
        final var maxWid = rasterCheck.getWidth() - 1;
        final var maxHt = rasterCheck.getHeight() - 1;
        
        final var queue = new ArrayDeque<Point>();
        Point p = new Point(x,y);

        while(p != null) {
            if(rasterCheck.getSample(p.px, p.py, 0) == searchingFor) {
                plotPoint(p.px,p.py, picColor, priColor);
                if((p.px > 0)&&(rasterCheck.getSample(p.px-1, p.py, 0) == searchingFor)) 
                    queue.add(new Point(p.px-1,p.py));
                if((p.py > 0) && (rasterCheck.getSample(p.px, p.py-1, 0) == searchingFor))
                    queue.add(new Point(p.px,p.py-1));
                if((p.px < maxWid) && (rasterCheck.getSample(p.px+1, p.py, 0) == searchingFor))
                    queue.add(new Point(p.px+1,p.py));
                if((p.py < maxHt) && (rasterCheck.getSample(p.px, p.py+1, 0) == searchingFor))
                    queue.add(new Point(p.px,p.py+1));
            }
            p = queue.pollLast();
        }
    }

    @Override
    public void plotPoint(int x, int y, int picColor, int priColor) {
        if (picColor >= 0) {
            picRaster.setSample(x, y, 0, picColor);
        }
        if (priColor >= 0) {
            priRaster.setSample(x, y, 0, priColor);
        }
    }

    @Override
    public void endPicture() {
        picRaster = null;
        priRaster = null;
    }

}
