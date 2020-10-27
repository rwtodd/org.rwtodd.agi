/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.extractor;

import java.nio.file.Paths;
import org.rwtodd.agi.resources.BufferedImagePicHandler;

/**
 *
 * @author rwtodd
 */
public class LoggingImageHandler extends BufferedImagePicHandler {

    private int step;
    private boolean inPlot;
    public LoggingImageHandler() {
        super();
        step = 0;
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
        System.err.printf("Line: (%d %d) (%d %d) (pic:%d/pri:%d)\n", x1, y1, x2, y2, picColor, priColor);
        inPlot = true;
        super.line(x1, y1, x2, y2, picColor, priColor);
        inPlot = false;
        makeImg();
    }

    @Override
    public void fill(int x, int y, int picColor, int priColor) {
        System.err.printf("Fill %d %d (pic:%d/pri:%d)\n", x, y, picColor, priColor);
        inPlot = true;
        super.fill(x, y, picColor, priColor);
        inPlot = false;
        makeImg();
    }

    @Override
    public void plotPoint(int x, int y, int picColor, int priColor) {
        if(!inPlot) {
            System.err.printf("Plot Point %d %d  (pic:%d/pri:%d)\n", x, y, picColor, priColor);
        }
        super.plotPoint(x, y, picColor, priColor);
    }

    private void makeImg() {
        ++step;
        try {
            this.writeImageToGIF(Paths.get(String.format("step_%05d.gif", step)), 3);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void endPicture() {
        System.err.println("end of picture.");
        super.endPicture();
    }

}
