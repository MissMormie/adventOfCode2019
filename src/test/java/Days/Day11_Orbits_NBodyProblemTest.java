package Days;

import helpers.Coordinate3D;
import helpers.Moon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11_Orbits_NBodyProblemTest {

    @Test
    void createMoon() {
        String input = "<x=-1, y=0, z=2>";
        Moon moon = new Moon(new Coordinate3D(-1, 0, 2),
                new Coordinate3D(0, 0, 0));
        Moon createdMoon = Day11_Orbits_NBodyProblem.createMoon(input);

        assertEquals(true, createdMoon.equals(moon));
    }

    @Test
    void calculateGravity() {
        Moon ganymede = new Moon(new Coordinate3D(3, 3, 3), new Coordinate3D(0, 0, 0));
        Moon callisto = new Moon(new Coordinate3D(5, 5, 5), new Coordinate3D(0, 0, 0));

        // Step 1
        ganymede.calculateGravityInfluenceFrom(callisto);
        callisto.calculateGravityInfluenceFrom(ganymede);
        callisto.applyVelocity();
        ganymede.applyVelocity();

        // Step 2
        ganymede.calculateGravityInfluenceFrom(callisto);
        callisto.calculateGravityInfluenceFrom(ganymede);
        callisto.applyVelocity();
        ganymede.applyVelocity();

        // Step 3
        ganymede.calculateGravityInfluenceFrom(callisto);
        callisto.calculateGravityInfluenceFrom(ganymede);
        callisto.applyVelocity();
        ganymede.applyVelocity();
    }

    @Test
    void runA() {
        String input;
        int answer;
        int numTicks;

        input = "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>";
        answer = 179;
        numTicks = 10;
        assertEquals(answer, Day11_Orbits_NBodyProblem.runA(input, numTicks), "input = " + input + " answer: " + answer);


        input = "<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>";
        answer = 1940;
        numTicks = 100;
        assertEquals(answer, Day11_Orbits_NBodyProblem.runA(input, numTicks), "input = " + input + " answer: " + answer);
    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day11_Orbits_NBodyProblem.runB(input), "input = " + input + " answer: " + answer);
    }
}