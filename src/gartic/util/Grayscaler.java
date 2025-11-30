package gartic.util;

public class Grayscaler {
    private int r;
    private int g;
    private int b;

    public Grayscaler(int r, int g, int b) {
        this.r = r;
        this.b = b;
        this.g = g;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    /**
     * Grayscale an image pixel
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return The gray-scaled RGB value.
     */
    public static int toGrayscale(int r, int g, int b) {
        return (int) (0.299 * r + 0.587 * g + 0.114 * b);
    }
}
