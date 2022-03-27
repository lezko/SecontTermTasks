package Task1;
import javax.swing.*;

public class MyFrame extends JFrame {
    public static void main(String[] args) throws Exception {
        MyFrame frame = new MyFrame();
        Painter.drawPath(frame);
    }

    MyPanel panel;

    public MyFrame() {
        panel = new MyPanel();
        
        this.add(panel);
        this.pack();
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void draw(int dx, int dy) {
        panel.draw(dx, dy);
    }
}
