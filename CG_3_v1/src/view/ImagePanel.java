package view;

import filter.Filter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private final Stack<BufferedImage> stack;
    private float scale = 1;
    int h, w;

    private final GroupLayout jPanel1Layout;

    public ImagePanel() {
        jPanel1Layout = new javax.swing.GroupLayout(this);
        stack = new Stack<>();
        this.setLayout(jPanel1Layout);
    }

    public void setImage(BufferedImage image) {
        stack.clear();
        this.image = image;
        stack.push(image);
        setScale(1);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setScale(float scale) {
        if (image == null) {
            return;
        }
        this.scale = scale;
        h = Math.round(scale * image.getWidth());
        w = Math.round(scale * image.getHeight());
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, h, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, w, Short.MAX_VALUE)
        );
        this.repaint();
    }

    public boolean undo() {
        image = stack.pop();
        repaint();
        return stack.size() != 1;
    }

    public void applyFilter(Filter filter) {
        if (image == null) {
            return;
        }
        stack.push(new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null));
        filter.apply(image);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, h, w, null);
        }
    }

}
