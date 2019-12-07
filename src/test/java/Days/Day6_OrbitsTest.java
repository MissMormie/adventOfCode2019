package Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6_OrbitsTest {

    @Test
    void runA() {
        String input;
        int answer;

        input = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L";
        answer = 42;
        assertEquals(answer, Day6_Orbits.runA(input), "input = " + input + " answer: " + answer);
    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "K)YOU\n" +
                "I)SAN";

        answer = 4;
        assertEquals(answer, Day6_Orbits.runB(input), "input = " + input + " answer: " + answer);
    }
}