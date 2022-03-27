package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyPanel extends JPanel {
    private int x0 = 250;
    private int y0 = 250;
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = (Graphics2D) img.getGraphics();
    
    public MyPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        this.graphics.setStroke(new BasicStroke(2));
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 0, 500, 500, null);
    }
    
    public void draw(int dx, int dy) {
        if (this.x0 + dx > 500 || this.y0 + dy > 500 || this.x0 + dx < 0 || this.y0 + dy < 0) {
            return;
        }

        this.graphics.setColor(Color.WHITE);
        this.graphics.drawLine(this.x0, this.y0, this.x0 + dx, this.y0 + dy);
        this.x0 += dx;
        this.y0 += dy;
        repaint();
    }
}