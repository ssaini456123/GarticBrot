package gartic.input;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class InputManager implements Runnable {
    private Robot robot;
    private long inputDelay;
    private final Queue<InputAction> actionQueue = new ArrayDeque<>();

    private static final long DEFAULT_INPUT_DELAY = 1;

    /**
     * @throws AWTException default exception thrown by <b>Robot</b>
     */
    public InputManager() throws AWTException {
        this.robot = new Robot();
        this.inputDelay = DEFAULT_INPUT_DELAY; // 1 ms
    }

    /**
     * @throws AWTException default exception thrown by <b>Robot</b>
     */
    public InputManager(long inputDelay) throws AWTException {
        this.robot = new Robot();
        this.inputDelay = inputDelay; // 1 ms
    }

    /**
     * @param robotInstance an instance of <b>Robot</b>
     */
    public InputManager(Robot robotInstance) {
        this.robot = robotInstance;
        this.inputDelay = DEFAULT_INPUT_DELAY;
    }

    /**
     * @param robotInstance an instance of <b>Robot</b>
     */
    public InputManager(Robot robotInstance, long inputDelay) {
        this.robot = robotInstance;
        this.inputDelay = inputDelay;
    }

    public void addInput(InputAction action) {
        actionQueue.add(action);
    }

    public void removeInput(InputAction action) {
        actionQueue.remove(action);
    }

    public long getInputDelay() {
        return inputDelay;
    }

    public void setInputDelay(long inputDelay) {
        this.inputDelay = inputDelay;
    }

    public Queue<InputAction> getActionQueue() {
        return actionQueue;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while (true) {
            if (!actionQueue.isEmpty()) {

                for (InputAction action : actionQueue) {

                    String actionClsName = action.toString();
                    System.out.println("Executing action: " + actionClsName);

                    action.execute();
                    removeInput(action);

                    System.out.println("Removed action: " + actionClsName + " from gartic.input buffer.");

                    try {
                        Thread.sleep(this.getInputDelay());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        }
    }
}