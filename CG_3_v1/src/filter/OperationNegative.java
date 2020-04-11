package filter;

import java.awt.image.BufferedImage;

public class OperationNegative implements Filter {

    @Override
    public void apply(BufferedImage image) {
        int value, newValue, R, G, B, newR, newG, newB;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                newR = 255 - R;
                newG = 255 - G;
                newB = 255 - B;
                newValue = 0xFF000000 | (newR << 16) | (newG << 8) | newB;
                image.setRGB(x, y, newValue);
            }
        }
    }

}
