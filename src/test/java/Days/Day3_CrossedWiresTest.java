package Days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3_CrossedWiresTest {

    @Test
    void runA() {
        String input;
        int answer;

        input = "R10,U10\n" +
                "U1,R20";
        answer = 11;
        assertEquals(answer, Day3_CrossedWires_UsingLines.runA(input), "input = " + input + " answer: " + answer);

        input = "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R8";
        answer = 159;
        assertEquals(answer, Day3_CrossedWires_UsingLines.runA(input), "input = " + input + " answer: " + answer);

        input = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        answer = 135;
        assertEquals(answer, Day3_CrossedWires.runA(input), "input = " + input + " answer: " + answer);
    }


    @Test
    void runB() {
        String input;
        int answer;

        input = "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83";
        answer = 610;
        assertEquals(answer, Day3_CrossedWires.runB(input), "input = " + input + " answer: " + answer);

        input = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        answer = 410;
        assertEquals(answer, Day3_CrossedWires.runB(input), "input = " + input + " answer: " + answer);
    }
}