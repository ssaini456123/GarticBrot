import input.InputManager;
import input.action.MouseClickAction;
import input.action.MouseMoveAction;

import java.awt.*;

public class Canvas {
    DimensionVertex dimensionVertex;
    private Robot r;
    private InputManager inputManager;

    /**
     * <b>Note:</b>
     * <p>
     * This program does not have the functionality to grab the Gartic Phone canvas dimensions
     * programmatically. Meaning each <b>Canvas</b> relies on the user to input the dimensions that
     * correspond to the actual drawable area of Gartic Phone.
     * </p>
     *
     * @param dimV The corners that define the initial and terminal point of the canvas
     */
    public Canvas(DimensionVertex dimV) throws AWTException {
        this.r = new Robot();
        this.dimensionVertex = dimV;
        this.inputManager = new InputManager(this.r);
    }

    /**
     * @param dimV
     * @param inputManager The input manager
     * @see #Canvas(DimensionVertex, InputManager)
     */
    public Canvas(DimensionVertex dimV, InputManager inputManager) throws AWTException {
        this.dimensionVertex = dimV;
        this.inputManager = inputManager;
    }

    /**
     * <b>Note:</b>
     * <p>
     * This program does not have the functionality to grab the Gartic Phone canvas dimensions
     * programmatically. Meaning each <b>Canvas</b> relies on the user to input the dimensions that
     * correspond to the actual drawable area of Gartic Phone.
     * </p>
     *
     * @param dimV
     * @param inputDelay The delay in which the input buffer will have between each
     *                   input action.
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

    public void drawDot(int x, int y) {
        InputManager inpM = this.inputManager;

        Point initialPt = this.dimensionVertex.initialPoint;
        Point termPt = this.dimensionVertex.terminalPoint;

        int[] cornerA = {initialPt.getX(), initialPt.getY()};
        int[] cornerB = {termPt.getX(), termPt.getY()};

        int clampedX = Math.max(cornerA[0], Math.min(x + cornerA[0], cornerB[0]));
        int clampedY = Math.max(cornerA[1], Math.min(y + cornerA[1], cornerB[1]));

        inpM.addInput(new MouseMoveAction(clampedX, clampedY, this.r));
        inpM.addInput(new MouseClickAction(this.r));
    }
}