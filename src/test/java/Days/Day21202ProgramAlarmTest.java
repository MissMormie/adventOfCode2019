package Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day21202ProgramAlarmTest {

    @Test
    void doIntCodeProgram() {
        String input;
        int answer;

        input = "1,0,0,0,99";
        answer = 2;
        assertEquals(answer, Day2_1202ProgramAlarm.runA(input, true), "input = " + input + " answer: " + answer);

        input = "2,3,0,3,99";
        answer = 2;
        assertEquals(answer, Day2_1202ProgramAlarm.runA(input, true), "input = " + input + " answer: " + answer);

        input = "2,4,4,5,99,0";
        answer = 2;
        assertEquals(answer, Day2_1202ProgramAlarm.runA(input, true), "input = " + input + " answer: " + answer);

        input = "1,1,1,4,99,5,6,0,99";
        answer = 30;
        assertEquals(answer, Day2_1202ProgramAlarm.runA(input, true), "input = " + input + " answer: " + answer);
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