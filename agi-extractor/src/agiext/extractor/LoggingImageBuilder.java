package agiext.extractor;

import java.nio.file.Paths;
import org.rwtodd.agires.BufferedImagePicBuilder;

/**
 *
 * @author rwtodd
 */
public class LoggingImageBuilder extends BufferedImagePicBuilder {

    private final int picNum;
    private int step;
    private boolean inPlot;
    private int pointCount;
    
    public LoggingImageBuilder(int num) {
        super();
        picNum = num;
        step = 1;
        pointCount = 0;
        inPlot = false;
    }

    @Override
    public void startPicture(int sizeX, int sizeY, int picColor, int priColor) {
        System.err.printf("Start: %d %d\n", sizeX, sizeY);
        step = 0;
        inPlot = false;
        super.startPicture(sizeX, sizeY, picColor, priColor);
    }

    @Override
    public void line(final int x1, final int y1,
            final int x2, final int y2,
            final int picColor, final int priColor) {
        System.err.printf("Step %d: Line: (%d %d) (%d %d) (pic:%d/pri:%d)\n", step, x1, y1, x2, y2, picColor, priColor);
        inPlot = true;
        super.line(x1, y1, x2, y2, picColor, priColor);
        inPlot = false;
        makeImg();
    }

    @Override
    public void fill(int x, int y, int picColor, int priColor) {
        System.err.printf("Step %d: Fill %d %d (pic:%d/pri:%d)\n", step, x, y, picColor, priColor);
        inPlot = true;
        super.fill(x, y, picColor, priColor);
        inPlot = false;
        makeImg();
    }

    @Override
    public void plotPoint(int x, int y, int picColor, int priColor) {
        if(!inPlot) {
            System.err.printf("Step %d: Plot Point %d %d  (pic:%d/pri:%d)\n", step, x, y, picColor, priColor);
            if(++pointCount >= 10) makeImg(); // only plot every 10 points or so...
        }
        super.plotPoint(x, y, picColor, priColor);
    }

    private void makeImg() {
        try {
            this.writeImageToGIF(Paths.get(String.format("pic_%03d_step_%05d.gif", picNum, step)), 3);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            ++step;
            pointCount = 0;
        }
    }

    @Override
    public void endPicture() {
        System.err.println("end of picture.");
        super.endPicture();
    }

}
