package org.rwtodd.agi.resources;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * A PicResource.Builder that draws BufferedImages.
 *
 * @author rwtodd
 */
public class BufferedImagePicBuilder implements PicResource.Builder {

    private BufferedImage pictureImage;
    private WritableRaster picRaster;
    private BufferedImage priorityImage;
    private WritableRaster priRaster;
    private final byte[] palette;

    public BufferedImagePicBuilder(final byte[] palette) {
        pictureImage = null;
        priorityImage = null;
        this.palette = palette;
    }

    public BufferedImagePicBuilder() {
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

    @Override
    public void line(final int x1, final int y1,
            final int x2, final int y2,
            final int picColor, final int priColor) {
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
        plotPoint(x, y, picColor, priColor);
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
            plotPoint(x, y, picColor, priColor);
        }
    }

    private static class Point {

        final int px;
        final int py;

        Point(int x, int y) {
            px = x;
            py = y;
        }
    }

    @Override
    public void fill(int x, int y, int picColor, int priColor) {
        Raster rasterCheck;
        int searchingFor;
        if ((picColor != 15) && (picColor != -1)) {
            rasterCheck = picRaster;
            searchingFor = 15;
        } else if ((picColor == -1) && (priColor != -1) && (priColor != 4)) {
            rasterCheck = priRaster;
            searchingFor = 4;
        } else {
            return;  // nothing to do!
        }
        final var maxWid = rasterCheck.getWidth() - 1;
        final var maxHt = rasterCheck.getHeight() - 1;

        final var queue = new ArrayDeque<Point>();
        Point p = new Point(x, y);

        while (p != null) {
            if (rasterCheck.getSample(p.px, p.py, 0) == searchingFor) {
                plotPoint(p.px, p.py, picColor, priColor);
                if ((p.px > 0) && (rasterCheck.getSample(p.px - 1, p.py, 0) == searchingFor)) {
                    queue.add(new Point(p.px - 1, p.py));
                }
                if ((p.py > 0) && (rasterCheck.getSample(p.px, p.py - 1, 0) == searchingFor)) {
                    queue.add(new Point(p.px, p.py - 1));
                }
                if ((p.px < maxWid) && (rasterCheck.getSample(p.px + 1, p.py, 0) == searchingFor)) {
                    queue.add(new Point(p.px + 1, p.py));
                }
                if ((p.py < maxHt) && (rasterCheck.getSample(p.px, p.py + 1, 0) == searchingFor)) {
                    queue.add(new Point(p.px, p.py + 1));
                }
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

    private void writeGIF(final Path imgPath, final BufferedImage img, int scaleFactor) throws AGIException {
        try {
            final int scaledWidth = img.getWidth() * 2 * scaleFactor; // *2 because of 160 width originals
            final int scaledHeight = img.getHeight() * 6 / 5 * scaleFactor; // 6/5 aspect ratio correction
            //final var scaledImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            final var scaledImg = new BufferedImage(scaledWidth, scaledHeight, img.getType(), (IndexColorModel) img.getColorModel());
            AffineTransform scaleInstance = AffineTransform.getScaleInstance(2.0 * scaleFactor, scaleFactor * 6.0 / 5.0);
            AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            scaleOp.filter(img, scaledImg);
            javax.imageio.ImageIO.write(
                    scaledImg, "GIF",
                    imgPath.toFile());
        } catch (Exception e) {
            throw new AGIException("Error writing GIF", e);
        }
    }

    public void writeImageToGIF(Path imgPath, int scale) throws AGIException {
        writeGIF(imgPath, pictureImage, scale);
    }

    public void writePriorityToGIF(Path imgPath, int scale) throws AGIException {
        writeGIF(imgPath, priorityImage, scale);
    }
}
