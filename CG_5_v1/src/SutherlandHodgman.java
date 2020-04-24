
import java.util.ArrayList;
import java.util.List;

public class SutherlandHodgman {

    Window window;

    List<Integer> xcoords = new ArrayList<>();
    List<Integer> ycoords = new ArrayList<>();

    public BresenhamPolygon cutPolygon(BresenhamPolygon bp, Window window) {
        List<BresenhamLine> lines = new ArrayList<>();
        for (BresenhamLine line : bp.lines) {
            lines.add(new BresenhamLine(line.x1, line.y1, line.x2, line.y2, line.col));
        }
        this.window = window;
        List<BresenhamLine> cutLines = cutLines(lines);
        BresenhamPolygon newbp = new BresenhamPolygon(cutLines);

        return newbp;
    }

    public List<BresenhamLine> cutLines(List<BresenhamLine> lines) {
        List<BresenhamLine> cutLines = new ArrayList<>();
        BresenhamLine l;
        int x;
        int y;
        for (BresenhamLine line : lines) {
            if (code(line.x1, line.y1) == 0 && code(line.x2, line.y2) == 0) {
                xcoords.add(line.x1);
                ycoords.add(line.y1);
            } else if (code(line.x1, line.y1) == 0 && code(line.x2, line.y2) != 0) {
                xcoords.add(line.x1);
                ycoords.add(line.y1);
                x = line.x1;
                y = line.y1;
                l = algorithmCut(line);
                if (l.x1 == x && l.y1 == y) {
                    xcoords.add(l.x2);
                    ycoords.add(l.y2);
                } else {
                    xcoords.add(l.x1);
                    ycoords.add(l.y1);
                }
            } else if (code(line.x1, line.y1) != 0 && code(line.x2, line.y2) != 0) {
                continue;
            } else if (code(line.x1, line.y1) != 0 && code(line.x2, line.y2) == 0) {
                x = line.x2;
                y = line.y2;
                l = algorithmCut(line);
                if (l.x1 == x && l.y1 == y) {
                    xcoords.add(l.x2);
                    ycoords.add(l.y2);
                } else {
                    xcoords.add(l.x1);
                    ycoords.add(l.y1);
                }
            }
        }

        int size = xcoords.size();
        for (int i = 0; i < size - 1; i++) {
            cutLines.add(new BresenhamLine(xcoords.get(i), ycoords.get(i), xcoords.get(i + 1), ycoords.get(i + 1), lines.get(0).col));
        }
        cutLines.add(new BresenhamLine(xcoords.get(size - 1), ycoords.get(size - 1), xcoords.get(0), ycoords.get(0), lines.get(0).col));

        return cutLines;
    }

    public BresenhamLine algorithmCut(BresenhamLine line) {

        int xmin = window.x1;
        int ymin = window.y1;
        int xmax = window.x2;
        int ymax = window.y2;

        int tmp;
        if (code(line.x1, line.y1) == 0) {
            tmp = line.x1;
            line.x1 = line.x2;
            line.x2 = tmp;
            tmp = line.y1;
            line.y1 = line.y2;
            line.y2 = tmp;
        }

        if ((code(line.x1, line.y1) & 8) != 0) {
            line.x1 = line.x1 + (line.x2 - line.x1) * (ymax - line.y1) / (line.y2 - line.y1);
            line.y1 = ymax;
        } else if ((code(line.x1, line.y1) & 4) != 0) {
            line.x1 = line.x1 + (line.x2 - line.x1) * (ymin - line.y1) / (line.y2 - line.y1);
            line.y1 = ymin;
        } else if ((code(line.x1, line.y1) & 2) != 0) {
            line.y1 = line.y1 + (line.y2 - line.y1) * (xmax - line.x1) / (line.x2 - line.x1);
            line.x1 = xmax;
        } else if ((code(line.x1, line.y1) & 1) != 0) {
            line.y1 = line.y1 + (line.y2 - line.y1) * (xmin - line.x1) / (line.x2 - line.x1);
            line.x1 = xmin;
        }

        if (!(code(line.x1, line.y1) == 0 && code(line.x2, line.y2) == 0)) {
            return algorithmCut(line);
        } else {
            return line;
        }
    }

    public int code(int x, int y) {

        int xmin = window.x1;
        int ymin = window.y1;
        int xmax = window.x2;
        int ymax = window.y2;

        return (((y > ymax) ? 1 : 0) << 3
                | ((y < ymin) ? 1 : 0) << 2
                | ((x > xmax) ? 1 : 0) << 1
                | ((x < xmin) ? 1 : 0));
    }

}
