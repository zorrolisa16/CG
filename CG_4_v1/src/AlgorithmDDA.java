
import java.awt.Color;
import java.awt.Graphics;

public class AlgorithmDDA implements Figure {

    int x1;
    int y1;
    int x2;
    int y2;
    Color col;

    public AlgorithmDDA(int x1, int y1, int x2, int y2, Color col) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.col = col;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(col);

        int delta_x = x2 - x1;
        int delta_y = y2 - y1;
        int L = Math.max(Math.abs(delta_x), Math.abs(delta_y)) + 1;
        float x = x1, y = y1;
        
        for (int i = 0; i < L; i++) {
            x += 1. * delta_x / L;
            y += 1. * delta_y / L;
            int _x = Math.round(x);
            int _y = Math.round(y);
            g.drawLine(_x, _y, _x, _y);
        }
    }
}
