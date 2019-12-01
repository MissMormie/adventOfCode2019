package helpers;

import java.util.HashSet;
import java.util.Set;

public class CoordinateMap {
    Set<MapPiece> mapPieces= new HashSet<>();
    int smallestX;
    int smallestY;
    int biggestX;
    int biggestY;

    public CoordinateMap() {

    }

    public void addMapPiece(MapPiece mapPiece) {
        mapPieces.add(mapPiece);
        updateSmallestAndBiggest(mapPiece);
    }

    private void updateSmallestAndBiggest(MapPiece mapPiece) {
        Coordinate coord = mapPiece.coordinate;
        smallestX = coord.x < smallestX ?  smallestX : coord.x;
        biggestX  = coord.x > biggestX  ?  biggestX  : coord.x;
        smallestY = coord.y < smallestY ?  smallestY : coord.y;
        biggestY  = coord.y > biggestY  ?  biggestY  : coord.y;
    }

    /** check Dijkstra's algo
     *
     * @return
     */
    public int calculateShortestPath(Coordinate coordinate) {
        return 0;
    }

}
