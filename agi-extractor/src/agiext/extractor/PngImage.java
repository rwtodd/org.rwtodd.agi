package agiext.extractor;

import org.rwtodd.agires.AgiResourceLoader;
import org.rwtodd.agires.AgiView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.nio.file.Path;

public abstract class PngImage {

    public static BufferedImage imageFromView(AgiResourceLoader resLoader, AgiView view) {
        // get the size of an image that puts all loops in a vertical list, with all
        // cells running horizontal.
        final int totWidth = view.loops().stream().mapToInt(l -> {
                    return l.cells().stream().mapToInt(AgiView.Cell::getWidth).sum();
                }).max().orElse(0);

        final int totHeight = view.loops().stream().mapToInt(AgiView.Loop::getMaxHeight).sum() +
                // add 1 pixel of vertical space between the loops
                view.loops().size() - 1;

        final var bi = new BufferedImage(totWidth, totHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        final var g = bi.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0,0, totWidth, totHeight);
        g.dispose();

        // now fill the image with the cells...
        // this is horribly inefficient... but cells are small, right?
        final int[] palette = resLoader.getPalette();
        int cellX = 0, loopY = 0;
        for(final var loop: view.loops()) {
            final int thisLoopHeight = loop.getMaxHeight();
            cellX = 0;
            for(final var cell: loop.cells()) {
                final int cellY = loopY + thisLoopHeight - cell.getHeight();
                final byte[] pix = cell.getPixels();
                int pixIdx = 0;
                for(int cy = 0; cy < cell.getHeight(); ++cy) {
                    for (int cx = 0; cx < cell.getWidth(); ++cx) {
                        bi.setRGB(cellX + cx, cellY + cy, palette[pix[pixIdx]]);
                        ++pixIdx;
                    }
                }
                cellX += cell.getWidth();
            }
            loopY += thisLoopHeight + 1;
        }

        return bi;
    }

    public static BufferedImage scaleUp(BufferedImage base, int scaleFactor, boolean correctAR) {
        int widScale = 2 * scaleFactor;  // *2 because of 160 width originals
        double htScale = (correctAR ? (6.0/5.0) : 1.0) * scaleFactor; // square pixels for 4:3 aspect ratio...
        final int scaledWidth = base.getWidth() * widScale;
        final int scaledHeight = (int)(base.getHeight() * htScale);
        final var scaledImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(widScale, htScale);
        AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        scaleOp.filter(base, scaledImg);
        return scaledImg;
    }

    public static void writeImage(Path imgPath, BufferedImage bi) throws IOException {
        javax.imageio.ImageIO.write(
                bi, "PNG",
                imgPath.toFile());
    }

}
