package helpers;

import Days.Day0;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoilerPlate {

    @Test
    void runA() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day0.runA(input), "input = " + input + " answer: " + answer);

    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day0.runB(input), "input = " + input + " answer: " + answer);
    }
}
