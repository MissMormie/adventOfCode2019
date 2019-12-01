package helpers;

public class DirectionalMovingObject {
    // Should not extend coords but have it as a variable.

    public enum Direction {
        LEFT, UP, RIGHT, DOWN;
    }

    public Coordinate coordinate;
    public Direction direction;
    Direction[] directions = Direction.values();


    public DirectionalMovingObject(int x, int y, Direction dir) {
        coordinate = new Coordinate(x, y);
        direction = dir;
    }

    public void turnRight() {
        int newDirection = (direction.ordinal() + 1) % directions.length;
        direction = directions[newDirection];
    }

    public void turnLeft() {
        int newDirection = direction.ordinal() == 0 ? directions.length - 1 : (direction.ordinal() - 1);
        direction = directions[newDirection];
    }

    public void turnAround() {
        int newDirection = (direction.ordinal() + 2) % directions.length;
        direction = directions[newDirection];
    }

    public void move() {
        move(1);
    }

    public void move(int steps) {
        if(direction == Direction.UP) {
            coordinate.y--;
        } else if(direction == Direction.DOWN) {
            coordinate.y++;
        } else if(direction == Direction.LEFT) {
            coordinate.x--;
        } else if(direction == Direction.RIGHT) {
            coordinate.x++;
        }
    }

}
