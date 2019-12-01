package helpers;

public class Coordinate3D extends Coordinate {
    int z;

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
}
