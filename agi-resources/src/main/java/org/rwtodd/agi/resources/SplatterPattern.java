package org.rwtodd.agi.resources;

import java.util.BitSet;
import java.util.Iterator;

/**
 * A PenPattern with AGI splatter code
 *
 * @author rwtodd
 */
public class SplatterPattern implements PenPattern {

    private int startIndex; /* the start index into SPLATTER */

//   the AGI docs say the bit patterns are:
//   (byte) 0x20, (byte) 0x94, (byte) 0x02, (byte) 0x24, (byte) 0x90, (byte) 0x82, (byte) 0xa4, (byte) 0xa2,
//   (byte) 0x82, (byte) 0x09, (byte) 0x0a, (byte) 0x22, (byte) 0x12, (byte) 0x10, (byte) 0x42, (byte) 0x14,
//   (byte) 0x91, (byte) 0x4a, (byte) 0x91, (byte) 0x11, (byte) 0x08, (byte) 0x12, (byte) 0x25, (byte) 0x10,
//   (byte) 0x22, (byte) 0xa8, (byte) 0x14, (byte) 0x24, (byte) 0x00, (byte) 0x50, (byte) 0x24, (byte) 0x04
//   however, the BitSet.valueOf interprets the bits of each byte from bit 0 to bit 7, instead of bit 7 to bit 0.
// So I used the following function:
// byte reverseBits(byte input) {
//        int bit1 = ((input & 0x80)>>7);
//       int bit2 = ((input & 0x40)>>5);
//      int bit3 = ((input & 0x20)>>3);
//     int bit4 = ((input & 0x10)>>1);
//      int bit5 = ((input & 0x08)<<1);
//     int bit6 = ((input & 0x04)<<3);
//       int bit7 = ((input & 0x02)<<5);
//      int bit8 = ((input & 0x01)<<7);
//    return (byte)(bit1|bit2|bit3|bit4|bit5|bit6|bit7|bit8);
//  }    
// to reverse the bits below:
    private static final BitSet SPLATTER = BitSet.valueOf(new byte[]{
        4, 41, 64, 36, 9, 65, 37, 69, 
        65, -112, 80, 68, 72, 8, 66, 40, 
        -119, 82, -119, -120, 16, 72, -92, 8, 
        68, 21, 40, 36, 0, 10, 36, 32
    });

    /* 128 starting values in the above BitSet */
    private static final int[] PATTERN_INDEX = new int[]{
        0x00, 0x18, 0x30, 0xc4, 0xdc, 0x65, 0xeb, 0x48,
        0x60, 0xbd, 0x89, 0x04, 0x0a, 0xf4, 0x7d, 0x6d,
        0x85, 0xb0, 0x8e, 0x95, 0x1f, 0x22, 0x0d, 0xdf,
        0x2a, 0x78, 0xd5, 0x73, 0x1c, 0xb4, 0x40, 0xa1,
        0xb9, 0x3c, 0xca, 0x58, 0x92, 0x34, 0xcc, 0xce,
        0xd7, 0x42, 0x90, 0x0f, 0x8b, 0x7f, 0x32, 0xed,
        0x5c, 0x9d, 0xc8, 0x99, 0xad, 0x4e, 0x56, 0xa6,
        0xf7, 0x68, 0xb7, 0x25, 0x82, 0x37, 0x3a, 0x51,
        0x69, 0x26, 0x38, 0x52, 0x9e, 0x9a, 0x4f, 0xa7,
        0x43, 0x10, 0x80, 0xee, 0x3d, 0x59, 0x35, 0xcf,
        0x79, 0x74, 0xb5, 0xa2, 0xb1, 0x96, 0x23, 0xe0,
        0xbe, 0x05, 0xf5, 0x6e, 0x19, 0xc5, 0x66, 0x49,
        0xf0, 0xd1, 0x54, 0xa9, 0x70, 0x4b, 0xa4, 0xe2,
        0xe6, 0xe5, 0xab, 0xe4, 0xd2, 0xaa, 0x4c, 0xe3,
        0x06, 0x6f, 0xc6, 0x4a, 0x75, 0xa3, 0x97, 0xe1
    };

    private static class It implements Iterator<Boolean> {
        private int idx;
        
        It(int start) {
            idx = start;
        }
        
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Boolean next() {
            ++idx;
            if(idx >= 255) idx = 0;
            return SplatterPattern.SPLATTER.get(idx);
        }
    }

    @Override
    public boolean takesArgument() {
        return true;
    }

    @Override
    public void setPattern(int patternNumber) {
        startIndex = SplatterPattern.PATTERN_INDEX[patternNumber % 128] - 1;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new SplatterPattern.It(startIndex);
    }
    
    public SplatterPattern() {
        startIndex = 0;
    }
}
