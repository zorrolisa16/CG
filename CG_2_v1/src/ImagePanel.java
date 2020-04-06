
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;
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

    public void setImage(File imageFile) throws IOException {
        stack.clear();
        this.image = ImageIO.read(imageFile);
        stack.push(image);
        setScale(1);
    }
    public BufferedImage getImage() {
        return image;
    }
    
//    public Color getColor(Point coords) {
//        if (image == null) {
//            return Color.WHITE;
//        }
//        return new Color(image.getRGB(Math.round(coords.x / scale), Math.round(coords.y / scale)));
//    }
    
    public int getH(){
        return h;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, h, w, null);
        }
    }

}
