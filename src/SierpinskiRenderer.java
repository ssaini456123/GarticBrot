public class SierpinskiRenderer {
    private final int width;
    private final int height;
    private final int depth;

    public SierpinskiRenderer(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    private void drawTriangle(Canvas canvas, double x, double y, double size, int currentDepth) {
        if (currentDepth == 0) {
            canvas.drawDot((int)x, (int)y);
        } else {
            double halfSize = size / 2;
            double height = size * Math.sqrt(3) / 2;

            drawTriangle(canvas, x, y, halfSize, currentDepth - 1); // top
            drawTriangle(canvas, x - halfSize / 2, y + height / 2, halfSize, currentDepth - 1); // bottom left
            drawTriangle(canvas, x + halfSize / 2, y + height / 2, halfSize, currentDepth - 1); // bottom right
        }
    }

    public void render(Canvas canvas) {
        double size = Math.min(width, height);
        double startX = width / 2.0;
        double startY = 0;
        drawTriangle(canvas, startX, startY, size, depth);
    }
}