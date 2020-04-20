
import java.awt.Color;
import java.awt.Graphics;

public class AlgorithmBresenhamCircle implements Figure {

    int x;
    int y;
    int r;
    Color col;

    public AlgorithmBresenhamCircle(int x, int y, int r, Color col) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.col = col;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(col);

        int sx = 0;
        int sy = r;
        int d = 3 - 2 * r;
        while (sx <= sy) {
            g.drawLine(x + sx, y - sy, x + sx, y - sy);
            g.drawLine(x + sx, y + sy, x + sx, y + sy);
            g.drawLine(x - sx, y - sy, x - sx, y - sy);
            g.drawLine(x - sx, y + sy, x - sx, y + sy);
            g.drawLine(x + sy, y + sx, x + sy, y + sx);
            g.drawLine(x - sy, y + sx, x - sy, y + sx);
            g.drawLine(x + sy, y - sx, x + sy, y - sx);
            g.drawLine(x - sy, y - sx, x - sy, y - sx);
            if (d >= 0) {
                d = d + 4 * (sx - sy) + 10;
                sx += 1;
                sy -= 1;
            } else {
                d = d + 4 * sx + 6;
                sx += 1;
            }
        }
    }
}
