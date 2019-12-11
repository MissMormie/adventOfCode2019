package helpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    public int x;
    public int y;
    public List adjacentCoords;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point getPoint() {
        return new Point(x, y);
    }

    public int getManhattanDistance(Coordinate coords) {
        return getManhattanDistance(coords.x, coords.y);
    }

    public int getManhattanDistance(int x, int y) {
        return Math.abs(y - this.y) + Math.abs(x - this.x);
    }

    public String getCoords() {
        return makeCoordString(x, y);
    }

    // TODO if i need this function, be sure to include the new vars
    public Coordinate clone() {
        return new Coordinate(x, y);
    }

    public void saveAdjacent(Map<String,Coordinate> coordinateMap) {
        adjacentCoords = new ArrayList();

        for(int x = -1; x < 2; x++ ) {
            for(int y = -1; y < 2; y++ ) { // loop adjecent acres
                String coords = Util.makeCoordString(this.x - x, this.y -y);
                if(!coords.equals(getCoords())) {
                    Coordinate coord = coordinateMap.get(coords);
                    if (coord != null) {
                        adjacentCoords.add(coord);
                    }
                }
            }
        }
    }

    public static String makeCoordString(int x, int y) {
        return "" + x + "," + y;
    }

    public boolean sameSpace(Coordinate coord) {
        if(coord.y == this.y && coord.x == this.x) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate coord = (Coordinate) obj;
        return coord.x == x && coord.y == y;
    }

    public int compareTo(Coordinate o) {
        if(o.y == this.y) {
            return this.x - o.x;
        }
        return this.y - o.y;
    }
}
