package filter;

import java.awt.image.BufferedImage;

public class LinearContrastFilter implements Filter {

    @Override
    public void apply(BufferedImage image) {
        int value, newValue, R, G, B, newR, newG, newB;
        int maxR = 0, maxG = 0, maxB = 0;
        int minR = 255, minG = 255, minB = 255;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                minR = Math.min(R, minR);
                maxR = Math.max(R, maxR);
                minG = Math.min(G, minG);
                maxG = Math.max(G, maxG);
                minB = Math.min(B, minB);
                maxB = Math.max(B, maxB);
            }
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                newR = Math.round(255f / (maxR - minR) * (R - minR));
                newG = Math.round(255f / (maxG - minG) * (G - minG));
                newB = Math.round(255f / (maxB - minB) * (B - minB));
                newValue = 0xFF000000 | (newR << 16) | (newG << 8) | newB;
                image.setRGB(x, y, newValue);
            }
        }
    }

}
