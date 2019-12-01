package helpers;

public class Coordinate4D extends Coordinate3D {
    int t; // time

    public Coordinate4D(int x, int y, int z, int t) {
        super(x, y, z);
        this.t = t;
    }

    public int getManhattanDistance(Coordinate4D coords) {
        int distance = Math.abs(coords.y - y)
                     + Math.abs(coords.x - x)
                     + Math.abs(coords.z - z)
                     + Math.abs(coords.t - t);
        return distance;
    }

    public static String makeCoordString(int x, int y, int z, int t) {
        return "" + x + "," + y + "," + z + "," + t;
    }

    @Override
    public String getCoords() {
        return makeCoordString(x, y , z, t);
    }


}
