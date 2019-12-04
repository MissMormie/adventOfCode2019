package Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4_LostPasswordTest {

    @Test
    void runA() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day4_LostPassword.runA(input), "input = " + input + " answer: " + answer);

    }


    @Test
    void runB() {
        String input;
        boolean answer;

        input = "111333";
        answer = false;
        assertEquals(answer, Day4_LostPassword.containsValidRunBNumber(input), "input = " + input + " answer: " + answer);

        input = "112233";
        answer = true;
        assertEquals(answer, Day4_LostPassword.containsValidRunBNumber(input), "input = " + input + " answer: " + answer);

        input = "123444";
        answer = false;
        assertEquals(answer, Day4_LostPassword.containsValidRunBNumber(input), "input = " + input + " answer: " + answer);

        input = "111122";
        answer = true;
        assertEquals(answer, Day4_LostPassword.containsValidRunBNumber(input), "input = " + input + " answer: " + answer);
    }
}