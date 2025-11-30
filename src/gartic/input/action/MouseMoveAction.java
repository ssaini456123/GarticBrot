package gartic.input.action;

import gartic.input.InputAction;

import java.awt.*;

public class MouseMoveAction implements InputAction {
    private Robot r;
    private int x;
    private int y;

    public MouseMoveAction(int x, int y, Robot r) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        this.r.mouseMove(x,y);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MouseMove(x="+this.x+",y="+this.y+")";
    }
}
