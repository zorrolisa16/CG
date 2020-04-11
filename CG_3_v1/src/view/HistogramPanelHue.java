package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HistogramPanelHue extends JPanel {

    float[] histogram;
    int height = 150;

    void setHistogramHue(float[] histogram) {
        this.histogram = histogram;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (histogram == null) {
            return;
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, histogram.length, height);
        float max = 0;
        for (int i = 0; i < histogram.length; ++i) {
            if (histogram[i] > max) {
                max = histogram[i];
            }
        }
        for (int i = 0; i < histogram.length; ++i) {
            g.setColor(new Color(HSV.toRGB(new float[]{i, 100, 100})));
            g.drawLine(i, height, i, height - Math.round((float) height * histogram[i] / max));
        }
    }
}
