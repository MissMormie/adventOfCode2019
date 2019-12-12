package Days;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class Day9_SensorBoostTest {
    @Test
    void runA() {
        String input;
        BigInteger answer;

        input = "109,-1,4,1,99";
        answer = new BigInteger("1").negate();
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, -1, 104, 1, 99";
        answer = new BigInteger("1");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, -1, 204, 1, 99";
        answer = new BigInteger("109");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, 1, 9, 2, 204, -6, 99";
        answer = new BigInteger("204");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, 1, 109, 9, 204, -6, 99";
        answer = new BigInteger("204");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, 1, 209, -1, 204, -106, 99";
        answer = new BigInteger("204");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, 1, 3, 3, 204, 2, 99";
        answer = new BigInteger("1");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);

        input = "109, 1, 203, 2, 204, 2, 99";
        answer = new BigInteger("1");
        assertEquals(answer, Day9_SensorBoost.runA(input), "input = " + input + " answer: " + answer);
    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day9_SensorBoost.runB(input), "input = " + input + " answer: " + answer);
    }
}