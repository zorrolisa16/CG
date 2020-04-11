package filter;

import java.awt.image.BufferedImage;

public class OperationMultiply implements Filter {

    double alpha = 0;

    public OperationMultiply(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public void apply(BufferedImage image) {
        int value, newValue, R, G, B, newR, newG, newB;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                newR = (int) Math.round(R * alpha);
                newG = (int) Math.round(G * alpha);
                newB = (int) Math.round(B * alpha);
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
