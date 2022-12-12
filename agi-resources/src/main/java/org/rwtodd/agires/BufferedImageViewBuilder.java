package org.rwtodd.agires;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.nio.file.Path;
import java.util.Optional;

/**
 * A Builder for View resources which stores BufferedImages of every cell.
 *
 * @author rwtodd
 */
public class BufferedImageViewBuilder implements ViewResource.Builder {

    /**
     * Rendered Cells can be Forward or Mirrored.  Mirrored ones are just lazy
     * placeholders for Forward ones, and generate reversed images on the fly.
     */
    private interface RenderedCell {
        public BufferedImage getImage();
    }

    /**
     * A forward image is just a wrapped BufferedImage.
     */
    private static class ForwardImage implements RenderedCell {

        private final BufferedImage img;

        ForwardImage(int w, int h, byte[] palette) {
            final var cm = new IndexColorModel(4, 16, palette, 0, true);
            img = new BufferedImage(
                    w,
                    h,
                    BufferedImage.TYPE_BYTE_BINARY,
                    cm);
        }

        @Override
        public BufferedImage getImage() {
            return img;
        }
    }
    
    /**
     * A mirrored image is lazy index to the forward image.
     */
    private class MirroredImage implements RenderedCell {
        private final int fwdLoop;
        private final int fwdCell;
        
        MirroredImage(int l, int c) { fwdLoop = l; fwdCell = c; }
        
        @Override
        public BufferedImage getImage() {
            final var src = cells[fwdLoop][fwdCell].getImage();
            final var flippedImg = new BufferedImage(src.getWidth(), src.getHeight(), src.getType(), (IndexColorModel) src.getColorModel());
            AffineTransform flipInstance = AffineTransform.getScaleInstance(-1.0, 1.0);
            flipInstance.concatenate(AffineTransform.getTranslateInstance(-src.getWidth(), 0));
            AffineTransformOp flipOp = new AffineTransformOp(flipInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            flipOp.filter(src, flippedImg);
            return flippedImg;
        }
        
    }
    
    protected int viewNumber;
    protected int curLoop;
    protected RenderedCell[][] cells;
    protected WritableRaster raster;
    protected String description;

    /**
     * Get the description, if any, for this view.
     *
     * @return the description string, as an Optional
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public int getLoopCount() {
        return cells.length;
    }

    public int getCellCount(int loop) {
        return (loop < cells.length) ? cells[loop].length : 0;
    }

    public BufferedImage getCell(int loop, int cell) {
        return cells[loop][cell].getImage();
    }

    private void writeGIF(final Path imgPath, final BufferedImage img, int scaleFactor) throws AgiException {
        try {
            final int scaledWidth = img.getWidth() * 2 * scaleFactor; // *2 because of 160 width originals
            final int scaledHeight = img.getHeight() * 6 / 5 * scaleFactor; // 6/5 aspect ratio correction
            final var scaledImg = new BufferedImage(scaledWidth, scaledHeight, img.getType(), (IndexColorModel) img.getColorModel());
            AffineTransform scaleInstance = AffineTransform.getScaleInstance(2.0 * scaleFactor, scaleFactor * 6.0 / 5.0);
            AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            scaleOp.filter(img, scaledImg);
            javax.imageio.ImageIO.write(
                    scaledImg, "GIF",
                    imgPath.toFile());
        } catch (Exception e) {
            throw new AgiException("Error writing GIF", e);
        }
    }

    public void writeToDisk(final Path imgPath, int loop, int cell, int scaleFactor) throws AgiException {
        writeGIF(imgPath, cells[loop][cell].getImage(), scaleFactor);
    }

    @Override
    public void viewStart(int viewnum, int numLoops, String desc) {
        viewNumber = viewnum;
        cells = new RenderedCell[numLoops][];
        description = desc;
        curLoop = -1;
    }

    @Override
    public void viewEnd() {
        /* do nothing */
    }

    @Override
    public void loopStart(final int loopNum, final int numCells) {
        curLoop = loopNum;
        cells[loopNum] = new RenderedCell[numCells];
    }

    @Override
    public void loopEnd() {
        curLoop = -1;
    }

    @Override
    public void mirroredCell(int cellNum, int fwdLoop) {
        cells[curLoop][cellNum] = new MirroredImage(fwdLoop, cellNum);
        raster = null;
    }
    
    @Override
    public void cellStart(int cellNum, int w, int h, byte[] palette) {
        final var c = new ForwardImage(w, h, palette);
        cells[curLoop][cellNum] = c;
        raster = c.getImage().getRaster();
    }

    @Override
    public void cellEnd() {
        raster = null;
    }

    @Override
    public void setPixel(int x, int y, int color) {
        raster.setSample(x, y, 0, color);
    }

}
