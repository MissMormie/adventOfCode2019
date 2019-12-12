package helpers;

import java.util.Objects;

public class Moon {
    public Coordinate3D position;
    public Coordinate3D velocity;
    public String name;

    public Moon(Coordinate3D position, Coordinate3D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void calculateGravityInfluenceFrom(Moon moon) {
        Coordinate3D otherPosition = moon.position;
        velocity.x += getVelocityChange(position.x, otherPosition.x);
        velocity.y += getVelocityChange(position.y, otherPosition.y);
        velocity.z += getVelocityChange(position.z, otherPosition.z);
    }

    public static int getVelocityChange(int x, int y) {
        if(x != y) {
            return x > y ? -1 : 1;
        }
        return 0;
    }

    public void applyVelocity() {
        position.add(velocity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return position.equals(moon.position) &&
                velocity.equals(moon.velocity);
    }

    @Override
    public String toString() {
        return "Moon{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    public int getEnergy() {
        // A moon's potential energy is the sum of the absolute values of its x, y, and z position coordinates.
        int potentialEnergy = Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);

        // A moon's kinetic energy is the sum of the absolute values of its velocity coordinates.
        int kineticEnergy = Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);

        // The total energy for a single moon is its potential energy multiplied by its kinetic energy.
        return potentialEnergy * kineticEnergy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }

    public static class MoonPair {
        public Moon firstMoon;
        public Moon secondMoon;

        public MoonPair(Moon firstMoon, Moon secondMoon) {
            this.firstMoon = firstMoon;
            this.secondMoon = secondMoon;
        }
    }
}
