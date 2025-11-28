package input;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class InputManager implements Runnable {
    private Robot robot;
    private long inputDelay;
    private static final long DEFAULT_INPUT_DELAY = 1;

    private static final List<InputAction> actionBuffer = new ArrayList<>();

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
        actionBuffer.add(action);
    }

    public void removeInput(InputAction action) {
        actionBuffer.remove(action);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while (true) {
            if (actionBuffer.isEmpty()) {
                System.out.println("Action buffer is empty.");
            } else {
                for (InputAction action : actionBuffer) {
                    synchronized (actionBuffer) {
                        String actionClsName = action.toString();
                        System.out.println("Executing action: " + actionClsName);

                        action.execute();
                        removeInput(action);

                        System.out.println("Removed action: " + actionClsName + " from input buffer.");

                        try {
                            Thread.sleep(this.inputDelay);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        break;
                    }
                }
            }
        }
    }
}