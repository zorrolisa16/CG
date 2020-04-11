package view;

public class HSV {

    public static int toRGB(float[] hsv) {
        float c = hsv[2] / 100 * hsv[1] / 100;
        float x = c * (1 - Math.abs(((hsv[0] / 60) % 2) - 1));
        float m = hsv[2] / 100 - c;
        float r, g, b;
        if (hsv[0] < 60) {
            r = (c + m);
            g = (x + m);
            b = (0 + m);
        } else if (hsv[0] < 120) {
            r = (x + m);
            g = (c + m);
            b = (0 + m);
        } else if (hsv[0] < 180) {
            r = (0 + m);
            g = (c + m);
            b = (x + m);
        } else if (hsv[0] < 240) {
            r = (0 + m);
            g = (x + m);
            b = (c + m);
        } else if (hsv[0] < 300) {
            r = (x + m);
            g = (0 + m);
            b = (c + m);
        } else {
            r = (c + m);
            g = (0 + m);
            b = (x + m);
        }
        return (Math.round(r * 255) << 16) + (Math.round(g * 255) << 8) + Math.round(b * 255);
    }

    public static float[] toHSV(int rgb) {
        float r = ((rgb >> 16) & 0xff) / 255f;
        float g = ((rgb >> 8) & 0xff) / 255f;
        float b = (rgb & 0xff) / 255f;

        float cmax = Math.max(b, Math.max(r, g));
        float cmin = Math.min(Math.min(r, g), b);
        float delta = cmax - cmin;

        float h = 60;
        if (delta == 0) {
            h *= 0;
        } else if (cmax == r) {
            h *= (((g - b) / delta % 6));
        } else if (cmax == g) {
            h *= ((b - r) / delta) + 2;
        } else if (cmax == b) {
            h *= ((r - g) / delta) + 4;
        }
        if (h < 0) {
            h += 360;
        }

        float s = 0;
        if (cmax > 1e-3) {
            s = delta / cmax;
        }

        float[] hsl = new float[3];
        hsl[0] = h;
        hsl[1] = s * 100;
        hsl[2] = cmax * 100;
        return hsl;
    }
}
