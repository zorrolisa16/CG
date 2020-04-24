
import java.awt.Color;
import java.awt.Graphics;

public class Window implements Figure {

    int x1;
    int y1;
    int x2;
    int y2;
    Color col;

    public Window(int x1, int y1, int x2, int y2, Color col) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.col = col;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(col);

        g.drawLine(x1, y1, x1, y2);
        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x2, y1, x2, y2);
        g.drawLine(x1, y2, x2, y2);
    }
}
