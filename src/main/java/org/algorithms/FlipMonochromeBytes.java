package org.algorithms;

import java.util.Arrays;

/**
 * Given a monochrome bitmap represented as a flat array of bytes with one bit per pixel,
 * flip it horizontally in place.
 * Flip a monochrome bitmap horizontally in place.
 *
 * Sample input:
 * 0011 1010
 * output:
 * 0101 1100
 *
 * Another:
 * 0011 1110 1010
 * 0101 0001 1100
 */
public class FlipMonochromeBytes extends BaseCustomAlgorithm {

    @Override
    public void execute() {
        //        Sample input:
        //        0011 1010
        //        output:
        //        0101 1100
        //
        //        Another:
        //        0011 1110 1010
        //        0101 0001 1100
        byte[] data1 = {0b00111010}; // Example: 0011 1010
        flip(data1, 8, 1);
        System.out.println("First: " + Arrays.toString(data1));

        byte[] data2 = {0b00111110, (byte) 0b10100000}; // Example: 0011 1110 1010
        flip(data2, 12, 1);
        System.out.println("Second: " + Arrays.toString(data2));
    }

    /**
     * @param data   byte array
     * @param width  in pixels
     * @param height in pixels
     */
    private void flip(byte[] data, int width, int height) {
        int bytesPerRow = (width + 7) / 8;
        for (int r = 0; r < height; r++) {
            // flip
            for (int c = 0; c < width / 2; c++) {
                int leftByteIdx = bytesPerRow * r + c / 8;
                int rightByteIdx = bytesPerRow * r + (width - c - 1) / 8;

                int leftBitPos = 7 - (c % 8);
                int rightBitPos = 7 - ((width - c - 1) % 8);

                int leftBit = (data[leftByteIdx] >> leftBitPos) & 1;
                int rightBit = (data[rightByteIdx] >> rightBitPos) & 1;

                // use XOR to flip bits in a byte array
                // XOR in two places effectively makes a swap
                if (leftBit != rightBit) {
                    data[leftByteIdx] ^= (1 << leftBitPos);
                    data[rightByteIdx] ^= (1 << rightBitPos);
                }
            }
        }
    }


    private static byte reverse(byte b) {
        int rev = 0;
        for (int i = 0; i < 8; i++) {
            rev <<= 1;
            rev |= (b & 1);
            b >>= 1;
        }
        return (byte) rev;
    }

    @Override
    protected String describe() {
        return "Given a monochrome bitmap represented as a flat array of bytes with one bit per pixel";
    }
}
