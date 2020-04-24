
import java.util.ArrayList;
import java.util.List;

public class SutherlandCohen {

    Window window;

    public List<BresenhamLine> cutLines(List<BresenhamLine> lines_, Window window) {
        List<BresenhamLine> lines = new ArrayList<>();
        for (BresenhamLine line : lines_) {
            lines.add(new BresenhamLine(line.x1, line.y1, line.x2, line.y2, line.col));
        }
        this.window = window;
        List<BresenhamLine> cutLines = new ArrayList<>();

        for (BresenhamLine line : lines) {
            if (code(line.x1, line.y1) == 0 && code(line.x2, line.y2) == 0) {
                cutLines.add(line);
            } else if ((code(line.x1, line.y1) & code(line.x2, line.y2)) != 0) {
                continue;
            } else if ((code(line.x1, line.y1) == 0 && code(line.x2, line.y2) != 0)
                    || (code(line.x1, line.y1) != 0 && code(line.x2, line.y2) == 0)) {
                cutLines.add(algorithmCut(line));
            } else if ((code(line.x1, line.y1) & code(line.x2, line.y2)) == 0) {
                cutLines.add(algorithmCut(line));
            }
        }

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
