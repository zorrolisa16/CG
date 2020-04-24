
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PaintPanel extends JPanel {

    List<Figure> figures;
    int scale;

    public PaintPanel() {
        this.scale = 1;
        setScale(scale);
        figures = new ArrayList<>();
    }

    public final void setScale(int scale) {
        this.scale = scale;
        this.repaint();
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
        this.repaint();
    }
    
    public void cleanPanel(){
        figures = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform atrans = AffineTransform.getScaleInstance(scale, scale);
        if (atrans != null) {
            g2d.setTransform(atrans);
        }
        figures.forEach((f) -> {
            f.paint(g);
        });
    }
}
