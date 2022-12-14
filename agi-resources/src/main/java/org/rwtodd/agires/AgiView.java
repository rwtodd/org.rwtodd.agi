package org.rwtodd.agires;

import java.util.List;
import java.util.Optional;

public record AgiView(Optional<String> description, List<Loop> loops) {

    public record Loop(List<Cell> cells) {
        // factory method
        public void addCell(int width, int height, byte transparentColor, byte[] pixels) {
            cells.add(new FwdCell(width,height, transparentColor, pixels));
        }
        public void addCell(AgiView av, int loop, int cell) {
            cells.add(new MirroredCell(av, loop, cell));
        }

        // return the max height across the loop's cells...
        public int getMaxHeight() {
            return cells.stream().mapToInt(Cell::getHeight).max().orElse(0);
        }
    };

    public sealed interface Cell permits FwdCell, MirroredCell {
        int getWidth();
        int getHeight();
        byte getTransparentColor();
        byte[] getPixels();
    }
}

final class FwdCell implements AgiView.Cell {
    private final int width,height;
    private final byte transparentColor;
    private final byte[] pixels;

    FwdCell(int width, int height, byte transparentColor, byte[] pixels) {
        this.width = width;
        this.height = height;
        this.transparentColor = transparentColor;
        this.pixels = pixels;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public byte getTransparentColor() {
        return transparentColor;
    }

    @Override
    public byte[] getPixels() {
        return pixels;
    }
}

final class MirroredCell implements AgiView.Cell {
    private final AgiView mirrorView;
    private final int mirrorLoop;
    private final int mirrorCell;

    MirroredCell(AgiView av, int loop, int cell) {
        this.mirrorView = av;
        this.mirrorLoop = loop;
        this.mirrorCell = cell;
    }

    private AgiView.Cell getCell() {
        return mirrorView.loops().get(mirrorLoop).cells().get(mirrorCell);
    }

    @Override
    public int getWidth() {
        return getCell().getWidth();
    }

    @Override
    public int getHeight() {
        return getCell().getHeight();
    }

    @Override
    public byte getTransparentColor() {
        return getCell().getTransparentColor();
    }

    @Override
    public byte[] getPixels() {
        final var c = getCell();
        final var width = c.getWidth();
        final var oldPix = c.getPixels();
        final var newPix = new byte[oldPix.length];
        for(int y = 0; y < c.getHeight(); ++y) {
            for(int x = 0; x < width; ++x) {
                newPix[x + y*width] = oldPix[(y+1)*width - x - 1];
            }
        }
        return newPix;
    }
}