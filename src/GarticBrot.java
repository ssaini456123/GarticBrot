import input.InputManager;

import java.awt.*;

public class GarticBrot {
    public static void main(String[] args) throws AWTException {
        Point initialPt = new Point(652,306);
        Point terminalPt = new Point(1583,325);
        DimensionVertex dimensionVertex = new DimensionVertex(initialPt, terminalPt);

        Canvas canvas = new Canvas(dimensionVertex, 1000);

        //test
        for (int i = 0; i < 100; i++) {
            canvas.drawDot(i,0);
        }
    }
}