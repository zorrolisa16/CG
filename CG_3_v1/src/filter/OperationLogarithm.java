package filter;

import java.awt.image.BufferedImage;

public class OperationLogarithm implements Filter {

    @Override
    public void apply(BufferedImage image) {
        int value, newValue, R, G, B, newR, newG, newB;
        int maxR = 0, maxG = 0, maxB = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                maxR = Math.max(R, maxR);
                maxG = Math.max(G, maxG);
                maxB = Math.max(B, maxB);
            }
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                newR = (int) Math.round(255f * Math.log(1 + R) / Math.log(1 + maxR));
                newG = (int) Math.round(255f * Math.log(1 + G) / Math.log(1 + maxG));
                newB = (int) Math.round(255f * Math.log(1 + B) / Math.log(1 + maxB));
                if (newR < 0) {
                    newR = 0;
                }
                if (newG < 0) {
                    newG = 0;
                }
                if (newB < 0) {
                    newB = 0;
                }
                if (newR > 255) {
                    newR = 255;
                }
                if (newG > 255) {
                    newG = 255;
                }
                if (newB > 255) {
                    newB = 255;
                }
                newValue = 0xFF000000 | (newR << 16) | (newG << 8) | newB;
                image.setRGB(x, y, newValue);
            }
        }
    }

}
