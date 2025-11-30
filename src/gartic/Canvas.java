package gartic;

import gartic.input.InputManager;
import gartic.input.action.MouseClickAction;
import gartic.input.action.MouseMoveAction;
import gartic.util.DimensionVertex;
import gartic.util.Point;

import java.awt.*;

public class Canvas {
    DimensionVertex dimensionVertex;
    private Robot r;
    private InputManager inputManager;

    /**
     * @param dimV The canvas screen vertices
     */
    public Canvas(DimensionVertex dimV) throws AWTException {
        this.r = new Robot();
        this.dimensionVertex = dimV;
        this.inputManager = new InputManager(this.r);
    }

    /**
     * @param dimV The canvas screen vertices
     * @param inputDelay The delay in which the gartic.input buffer will have between each
     *                   gartic.input action.
     */
    public Canvas(DimensionVertex dimV, long inputDelay) throws AWTException {
        this.r = new Robot();
        this.dimensionVertex = dimV;
        this.inputManager = new InputManager(this.r, inputDelay);

        Thread inputMgrThread = new Thread(inputManager);
        inputMgrThread.start();
    }

    public DimensionVertex getDimensionVertex() {
        return dimensionVertex;
    }

    public void setDimensionVertex(DimensionVertex dimensionVertex) {
        this.dimensionVertex = dimensionVertex;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    private Robot getR(){
        return this.r;
    }

    private void setR(Robot r) {
        this.r = r;
    }

    /**
     * Scales the drawing region to the provided canvas
     * dimensions
     * @param x X
     * @param y Y
     * @return The clamped coordinates, first element is the clamped x value,
     * second element is the clamped y value.
     */
    public int[] clampCoordinates(int x, int y) {
        Point initialPt = this.dimensionVertex.getInitialPoint();
        Point termPt = this.dimensionVertex.getTerminalPoint();

        int initialX = initialPt.getX();
        int initialY = initialPt.getY();

        int termX = termPt.getX();
        int termY = termPt.getY();

        int canvasX = initialX + x;
        int canvasY = initialY + y;

        int clampedX = Math.clamp(canvasX, initialX, termX);
        int clampedY = Math.clamp(canvasY, initialY, termY);

        return new int[]{clampedX, clampedY};
    }

    /**
     * Draws a dot on to the canvas.
     * @param x The x position
     * @param y The y position
     */
    public void drawDot(int x, int y) {
        InputManager inpM = this.getInputManager();
        int[] clamped = this.clampCoordinates(x, y);
        inpM.addInput(new MouseMoveAction(clamped[0], clamped[1], this.r));
        inpM.addInput(new MouseClickAction(this.r));
    }

    /**
     * Draws a (horizontal) line on to the canvas
     * @param x The x position
     * @param y The y position
     * @param length The length of the line in pixels
     */
    public void drawHorizontalLine(int x, int y, int length) {
        int[] clampedCoordinates = this.clampCoordinates(x,y);
        int clampedX = clampedCoordinates[0];
        int clampedY = clampedCoordinates[1];

        for (int i = 0; i < length; i++) {
            int xNew = clampedX + i;
            this.getInputManager().addInput(new MouseClickAction(this.r));
            this.getInputManager().addInput(new MouseMoveAction(xNew, clampedY, this.r));
        }
    }

    /**
     * Draws a (vertical) line on to the canvas.
     * @param x The x position
     * @param y The y position
     * @param length Length of the line in pixels
     */
    public void drawVerticalLine(int x, int y, int length) {
        int[] clampedCoordinates = this.clampCoordinates(x,y);
        int clampedX = clampedCoordinates[0];
        int clampedY = clampedCoordinates[1];

        for (int i = 0; i < length; i++) {
            int yNew = clampedY + i;
            this.getInputManager().addInput(new MouseClickAction(this.r));
            this.getInputManager().addInput(new MouseMoveAction(clampedX, yNew, this.r));
        }
    }
}