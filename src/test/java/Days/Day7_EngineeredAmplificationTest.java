package Days;

import helpers.intCodeProgram.IntCodeProgram;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day7_EngineeredAmplificationTest {
    @Test
    void runA() {
        String input;
        int answer;
        input = "";


        answer = 0;
        assertEquals(answer, Day7_EngineeredAmplification.runA(input), "input = " + input + " answer: " + answer);

    }


    @Test
    void runProgramForSequence() {
        String input;
        int answer;

        input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";

        answer = 139629729;
        assertEquals(answer, Day7_EngineeredAmplification.runProgramForSequence(new int[]{9, 8, 7, 6, 5}, input), "input = " + input + " answer: " + answer);
    }

    @Test
    void runB() {
        String input;
        int answer;

        input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";

        answer = 139629729;
        assertEquals(answer, Day7_EngineeredAmplification.runB(input), "input = " + input + " answer: " + answer);
    }
}