package gartic;

import gartic.renderer.ImageRenderer;
import gartic.renderer.MandelbrotRenderer;
import gartic.renderer.SierpinskiRenderer;
import gartic.util.DimensionVertex;
import gartic.util.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GarticBrot {
    public static void main(String[] args) throws AWTException {
        Point[] p = new Point[2];
        p[0] = new Point(654,307);
        p[1] = new Point(1581,825);

        DimensionVertex dimensionVertex = new DimensionVertex(p[0], p[1]);
        Canvas canvas = new Canvas(dimensionVertex);

        MandelbrotRenderer renderer = new MandelbrotRenderer(600,200);
        renderer.render(canvas);
        
        Thread inputThread = new Thread(canvas.getInputManager());
        inputThread.start();
    }
}