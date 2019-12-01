package helpers;

public class VelocityMovingObject implements Comparable<VelocityMovingObject> {

    private Coordinate coordinate;
    private int velocityX;
    private int velocityY;

    public VelocityMovingObject(int x, int y, int velocityX, int velocityY) {
        coordinate = new Coordinate(x, y);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void move(int ticks) {
        for(int tick = 0; tick < ticks; tick++) {
            move();
        }
    }

    public void move() {
        coordinate.x += velocityX;
        coordinate.y += velocityY;
    }

    public void moveBack() {
        coordinate.x -= velocityX;
        coordinate.y -= velocityY;

    }

    public Coordinate getCoordinate() {
        return coordinate.clone();
    }

    @Override
    public int compareTo(VelocityMovingObject o) {
        return this.coordinate.compareTo(o.getCoordinate());
    }
}
