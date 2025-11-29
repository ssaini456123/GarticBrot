import util.DimensionVertex;
import util.Point;

import java.awt.*;

public class GarticBrot {
    public static void main(String[] args) throws AWTException {
        Point[] p = new Point[2];

        p[0] = new Point(654,307);
        p[1] = new Point(1581,825);

        DimensionVertex dimensionVertex = new DimensionVertex(p[0], p[1]);

        Canvas canvas = new Canvas(dimensionVertex);

        Thread inpuThread = new Thread(canvas.getInputManager());
        inpuThread.start();

        int width = 466;
        int height = 500;
        int depth = 10;
        SierpinskiRenderer renderer = new SierpinskiRenderer(width, height,depth);
        renderer.render(canvas);
    }
}