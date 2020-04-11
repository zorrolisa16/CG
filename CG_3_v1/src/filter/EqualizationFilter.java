package filter;

import view.HSV;

import java.awt.image.BufferedImage;

public class EqualizationFilter implements Filter {

    boolean isRGB;

    public EqualizationFilter(boolean isRGB) {
        this.isRGB = isRGB;
    }

    private void applyRGB(BufferedImage image) {
        float[][] histogram = makeHistogramRGB(image);
        float HR = 0, HG = 0, HB = 0;
        for (int i = 0; i < 256; i++) {
            HR += histogram[0][i];
            HG += histogram[1][i];
            HB += histogram[2][i];
        }
        for (int i = 0; i < 256; i++) {
            histogram[0][i] /= HR;
            histogram[1][i] /= HG;
            histogram[2][i] /= HB;
        }
        float[][] Shistogram = new float[3][256];
        Shistogram[0][0] = histogram[0][0];
        Shistogram[1][0] = histogram[1][0];
        Shistogram[2][0] = histogram[2][0];
        for (int i = 1; i < 256; ++i) {
            Shistogram[0][i] = Shistogram[0][i - 1] + histogram[0][i];
            Shistogram[1][i] = Shistogram[1][i - 1] + histogram[1][i];
            Shistogram[2][i] = Shistogram[2][i - 1] + histogram[2][i];
        }
        int value, newValue, R, G, B, newR, newG, newB;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = image.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                newR = Math.round(255 * Shistogram[0][R]);
                newG = Math.round(255 * Shistogram[1][G]);
                newB = Math.round(255 * Shistogram[2][B]);
                newValue = 0xFF000000 | (newR << 16) | (newG << 8) | newB;
                image.setRGB(x, y, newValue);
            }
        }
    }

    private void applyHSV(BufferedImage image) {
        float[][] histogram = makeHistogramHSV(image);
        float HB = 0;
        for (int i = 0; i < 101; ++i) {
            HB += histogram[2][i];
        }
        for (int i = 0; i < 101; ++i) {
            histogram[2][i] /= HB;
        }
        float[] Shistogram = new float[101];
        Shistogram[0] = histogram[2][0];
        for (int i = 1; i < 101; ++i) {
            Shistogram[i] = Shistogram[i - 1] + histogram[2][i];
        }
        float[] value;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                value = HSV.toHSV(image.getRGB(x, y));
                value[2] = Math.round(100 * Shistogram[Math.round(value[2])]);
                image.setRGB(x, y, HSV.toRGB(value));
            }
        }
    }

    @Override
    public void apply(BufferedImage image) {
        if (isRGB) {
            applyRGB(image);
        } else {
            applyHSV(image);
        }
    }

    public static float[][] makeHistogramRGB(BufferedImage source) {
        float[][] histogram = new float[3][256];
        int R, G, B, value;
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                value = source.getRGB(x, y);
                R = (value >> 16) & 0xFF;
                G = (value >> 8) & 0xFF;
                B = value & 0xFF;
                histogram[0][R]++;
                histogram[1][G]++;
                histogram[2][B]++;
            }
        }
        return histogram;
    }

    public static float[][] makeHistogramHSV(BufferedImage source) {
        float[][] histogram = new float[3][];
        histogram[0] = new float[361];
        histogram[1] = new float[101];
        histogram[2] = new float[101];
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                float[] hsv = HSV.toHSV(source.getRGB(x, y));
                histogram[0][Math.round(hsv[0])]++;
                histogram[1][Math.round(hsv[1])]++;
                histogram[2][Math.round(hsv[2])]++;
            }
        }
        return histogram;
    }
}
