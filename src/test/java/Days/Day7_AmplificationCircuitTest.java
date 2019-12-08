package Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7_AmplificationCircuitTest {
    @Test
    void runA() {
        String input;
        int answer;

        input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        answer = 43210;
        assertEquals(answer, Day7_AmplificationCircuit.runA(input), "input = " + input + " answer: " + answer);

    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day7_AmplificationCircuit.runB(input), "input = " + input + " answer: " + answer);
    }
}