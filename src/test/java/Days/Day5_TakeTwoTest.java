package Days;

import helpers.StringHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static Days.Day5_TakeTwo.ParameterMode.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5_TakeTwoTest {

    @Test
    void runProgram_Multiplication() {
        // Multiplication
        String input = "1002,5,3,5,99,33";
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");

        Day5_TakeTwo.runProgram(memoryState);
        assertEquals( 99, memoryState.get(4));
    }

    @Test
    void runProgram_MovingPointer() {
        // Moving pointer
        //                        .   .       .
        String input = "1002,5,3,0,3,0,1,1,3,0,99";
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");

        Day5_TakeTwo.runProgram(memoryState);

        // Test succeeds if no exception is thrown. Either when 99 isn't reached or a wrong opcode is tried this throws an exception

    }

    @Test
    void runProgram_Addition() {
        // Multiplication
        String input = "1001,5,3,5,99,12";
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");

        Day5_TakeTwo.runProgram(memoryState);
        assertEquals(15, memoryState.get(5));
    }

    @Test
    void runProgram_Input() {
        // Multiplication
        String input = "3,3,99,12";
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");

        Day5_TakeTwo.runProgram(memoryState);
        assertEquals(1, memoryState.get(3));
    }

    @Test
    void getValueOfParam() {
        String input = "1002,4,3,4,33";
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");

        // Validate position mode. input 2 = 4. Get's position 4's value
        Integer valueOfParam = Day5_TakeTwo.getValueOfParam(memoryState, 0, 1, POSITION);
        assertEquals(valueOfParam, 33);

        // Validate position mode. input 2 = 4. Get's position 4's value
        valueOfParam = Day5_TakeTwo.getValueOfParam(memoryState, 0, 1, IMMEDIATE);
        assertEquals(valueOfParam, 4);
    }

}
