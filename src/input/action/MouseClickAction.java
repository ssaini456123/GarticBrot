package input.action;

import input.InputAction;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseClickAction implements InputAction {
    private Robot r;

    public MouseClickAction(Robot robot) {
        this.r = robot;
    }

    @Override
    public void execute() {
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public String toString() {
        return "MouseClick(void)";
    }
}