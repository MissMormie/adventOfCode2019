package helpers;

import java.util.Optional;

public class StraightLine {
    Coordinate startCoordinate;
    Coordinate endCoordinate;

    public StraightLine(Coordinate startCoordinate, Coordinate endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }

    /**
     * This function only works for lines that are horizontal or vertical, and only intersect and not overlap.
     */
    public Optional<Coordinate> crossesLineAt(StraightLine line) {
        if((line.isHorizontal() && isHorizontal())
                || (line.isVertical() && isVertical())) {
            return Optional.empty();
        }
        int intersectX = isHorizontal() ? this.startCoordinate.x : line.startCoordinate.x;
        int intersectY = isVertical() ? this.startCoordinate.y : line.startCoordinate.y;

        if(pointOnLine(intersectX, intersectY) && line.pointOnLine(intersectX, intersectY)) {
            return Optional.of(new Coordinate(intersectX, intersectY));
        }
        return Optional.empty();
    }

    public boolean pointOnLine(int x, int y) {
        return (x <= getHighestX() && x >= getLowestX())
                && (y <= getHighestY() && y >= getLowestY());
    }

    private int getHighestX() {
        return  startCoordinate.x > endCoordinate.x ? startCoordinate.x : endCoordinate.x;
    }

    private int getLowestX() {
        return  startCoordinate.x > endCoordinate.x ? endCoordinate.x : startCoordinate.x;
    }

    private int getHighestY() {
        return  startCoordinate.y > endCoordinate.y ? startCoordinate.y : endCoordinate.y;
    }

    private int getLowestY() {
        return  startCoordinate.y > endCoordinate.y ? endCoordinate.y : startCoordinate.y;
    }

    public boolean isHorizontal() {
        return startCoordinate.x == endCoordinate.x;
    }

    public boolean isVertical() {
        return startCoordinate.y == endCoordinate.y;
    }
}
