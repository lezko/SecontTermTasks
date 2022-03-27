package Task1;

import java.util.concurrent.TimeUnit;

public class Painter {
    private static int step = 7;
    
    public static void drawPath(MyFrame frame) throws Exception {
        MyRandom random = new MyRandom(628);
        while (true) {
            double angle = random.next() / 100.0;
            int dx = (int) (step * Math.cos(angle));
            int dy = (int) (step * Math.sin(angle));
            
            makeMove(frame, dx, dy);
    
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
    
    private static void makeMove(MyFrame frame, int dx, int dy) {
        frame.draw(dx, dy);
    }
}
