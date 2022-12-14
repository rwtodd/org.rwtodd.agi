package org.rwtodd.agires.restypes;

import org.rwtodd.agires.util.Util;
import org.rwtodd.agires.AgiView;
import org.rwtodd.agires.AgiException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents an AGI View resource, and the capability to stream the contents of the
 * view to a client-supplied {@code Builder} object.
 * @author Richard Todd
 */
public abstract class ViewResource {
    public static AgiView build(final byte[] data) throws AgiException {
        final int loopCount = data[2]&0xff;
        final int descLoc = (data[3]&0xff)|((data[4]&0xff)<<8);
        int loopDirectoryIdx = 5;

        final String desc = (descLoc > 0)? Util.asciizString(data, descLoc):null;
        final var view = new AgiView(Optional.ofNullable(desc), new ArrayList<>(loopCount));

        // now parse each loop
        for(int loopNo = 0; loopNo < loopCount; ++loopNo) {
            final int loopOffset = (data[loopDirectoryIdx]&0xff)|((data[loopDirectoryIdx+1]&0xff)<<8);
            loopDirectoryIdx += 2;
            final int cellCount = data[loopOffset]&0xff;
            int cellDirectoryIdx = loopOffset+1;
            final var loop = new AgiView.Loop(new ArrayList<>(cellCount));

            // now parse each cell in each loop...
            for(int cellNo = 0; cellNo < cellCount; ++cellNo) {
                int idx = loopOffset + ((data[cellDirectoryIdx]&0xff)|((data[cellDirectoryIdx+1]&0xff)<<8));
                cellDirectoryIdx += 2;
                final int cellWidth = data[idx++]&0xff;
                final int cellHeight = data[idx++]&0xff;
                final byte transparentColor = (byte)(data[idx]&0x0f);
                final int mirroring = (data[idx++]&0xf0)>>4;
                
                if(((mirroring & 0x08) == 0x08) && ((mirroring & 0x07) != loopNo)) {
                    // we have a "mirrored" cell... just tell the builder about it.
                    loop.addCell(view, mirroring&0x07, cellNo);
                } else {
                    // we have a "foward" cell... decode it
                    final var pixels = new byte[cellWidth*cellHeight];
                    for(int y = 0; y < cellHeight; ++y) {
                        final int base = y*cellWidth;
                        int x = 0;
                        while(true) {
                            final int nextB = data[idx++]&0xff;
                            int count = nextB&0x0f;
                            final byte color = (byte)(nextB>>4);
                            if(nextB == 0) {
                                // fill the rest of the line in with transparent...
                                for(;x < cellWidth;++x) pixels[x + base] = transparentColor;
                                break;
                            } else {
                                while(count-- > 0) {
                                    if(x == cellWidth) break;
                                    pixels[x+base] = color;
                                    ++x;
                                }
                            }
                        }
                    }
                    loop.addCell(cellWidth, cellHeight,transparentColor,pixels);
                }
            }
            view.loops().add(loop);
        }
        return view;
    }
}
