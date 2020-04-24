
import java.awt.Graphics;
import java.util.List;

public class BresenhamPolygon implements Figure {

    List<BresenhamLine> lines;

    public BresenhamPolygon(List<BresenhamLine> lines) {
        this.lines = lines;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(lines.get(0).col);

        int size = lines.size();

        int[] xcoords = new int[size];
        int[] ycoords = new int[size];

        for (int i = 0; i < size; i++) {
            xcoords[i] = (lines.get(i).x1);
            ycoords[i] = (lines.get(i).y1);
        }

        g.fillPolygon(xcoords, ycoords, size);
    }
}
