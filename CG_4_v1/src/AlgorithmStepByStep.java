
import java.awt.Color;
import java.awt.Graphics;

public class AlgorithmStepByStep implements Figure {

    int x1;
    int y1;
    int x2;
    int y2;
    Color col;

    public AlgorithmStepByStep(int x1, int y1, int x2, int y2, Color col) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.col = col;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(col);

        float A = y1 - y2;
        float B = x2 - x1;
        float C = x1 * y2 - x2 * y1;
        float k = -A / B;
        float b = -C / B;
        int delta_x = Math.abs(x2 - x1);
        int delta_y = Math.abs(y2 - y1);
        int L = Math.max(delta_x, delta_y);

        float x = Math.min(x1, x2);
        for (int i = 0; i < L; i++) {
            x += (float) delta_x / L;
            int _x = Math.round(x);
            int _y = Math.round(k * x + b);
            g.drawLine(_x, _y, _x, _y);
        }
    }
}
