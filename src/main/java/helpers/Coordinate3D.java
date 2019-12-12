package helpers;

import java.util.Objects;

public class Coordinate3D extends Coordinate {
    public int z;

    public Coordinate3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }


    public int getManhattanDistance(Coordinate3D coords) {
        int distance = Math.abs(coords.y - y) + Math.abs(coords.x - x) + Math.abs(coords.z - z);
        return distance;
    }

    @Override
    public String getCoords() {
        return makeCoordString(x, y , z);
    }

    public static String makeCoordString(int x, int y, int z) {
        return "" + x + "," + y + "," + z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coordinate3D that = (Coordinate3D) o;
        return z == that.z && x == that.x && y == that.y;
    }

    public void add(Coordinate3D coordinate3D) {
        x += coordinate3D.x;
        y += coordinate3D.y;
        z += coordinate3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }

    @Override
    public String toString() {
        return " x= " + x +
                "\ty= " + y +
                "\t z= " + z;
    }
}
