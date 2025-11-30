package gartic.util;

public class DimensionVertex {
    Point initialPoint;
    Point terminalPoint;

    public DimensionVertex(Point initialPoint, Point terminalPoint) {
        this.initialPoint = initialPoint;
        this.terminalPoint = terminalPoint;
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(Point initialPoint) {
        this.initialPoint = initialPoint;
    }

    public Point getTerminalPoint() {
        return terminalPoint;
    }

    public void setTerminalPoint(Point terminalPoint) {
        this.terminalPoint = terminalPoint;
    }
}