package Task1;

public class MyRandom {
    private final int minValue;
    private final int maxValue;
    private int seed;
    
    public MyRandom(int min, int max) throws Exception {
        if (min < 0 || max <= min || max > 0x7ffff) {
            throw new Exception("Invalid input");
        }
        this.minValue = min;
        this.maxValue = max;
        this.seed = (int) System.currentTimeMillis() % 1000;
    }
    
    public MyRandom(int max) throws Exception {
        this(0, max);
    }
    
    public int next() {
        this.seed = this.seed * 1103515245 + 12345;
        return Math.abs((this.seed / 65536) % (this.maxValue + 1 - this.minValue)) + this.minValue;
    }
}
