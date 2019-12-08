package Days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day8_SpaceImageTest {
    @Test
    void runA() {
        String input;
        int answer;

        input = "";
        answer = 0;
        assertEquals(answer, Day8_SpaceImage.runA(input), "input = " + input + " answer: " + answer);

    }

    @Test
    void runB() {
        String input;

        input = "0222112222120000";
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(1);
        answer.add(1);
        answer.add(0);
        assertEquals(answer, Day8_SpaceImage.runB(input, 2, 2), "input = " + input + " answer: " + answer);
    }
}