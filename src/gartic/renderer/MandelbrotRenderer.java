package gartic.renderer;

import gartic.Canvas;

public class MandelbrotRenderer implements Renderer {
    private final int width;
    private final int height;

    public MandelbrotRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean isInside(double cx, double cy) {
        double zx = 0;
        double zy = 0;
        int iter = 0;

        int maxIterations = 200;
        while (zx*zx + zy*zy <= 4 && iter < maxIterations) {
            double temp = zx*zx - zy*zy + cx;
            zy = 2*zx*zy + cy;
            zx = temp;
            iter++;
        }
        return iter == maxIterations;
    }

    private void drawHorizontalSpan(Canvas canvas, int xStart, int y) {
        canvas.drawDot(xStart, y);
    }

    public void render(Canvas canvas) {
        for (int y = 0; y < height; y++) {
            double yMax = 1.5;
            double yMin = -1.5;
            double cy = yMin + (y / (double)height) * (yMax - yMin);
            int x = 0;

            while (x < width) {
                double xMin = -2.0;
                double xMax = 1.0;
                double cx = xMin + (x / (double)width) * (xMax - xMin);

                if (isInside(cx, cy)) {
                    int spanStart = x;

                    while (x < width) {
                        cx = xMin + (x / (double)width) * (xMax - xMin);
                        if (!isInside(cx, cy)) break;
                        x++;
                    }

                    drawHorizontalSpan(canvas, spanStart, y);

                } else {
                    x++;
                }
            }
        }
    }
}