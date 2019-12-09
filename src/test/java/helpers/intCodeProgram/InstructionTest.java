package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.MultiplyInstruction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void getParameters() {
        Instruction instruction = new MultiplyInstruction(1002,0, 0);
        List<Integer> memoryState = IntStream.of(1002, 4, 3, 4, 33).boxed().collect(Collectors.toList());

        assertEquals(33, instruction.getValueOfParam(1, memoryState));
        assertEquals(3, instruction.getValueOfParam(2, memoryState));
        assertEquals(33, instruction.getValueOfParam(3, memoryState) );
    }

    @Test
    void getValueOfParam() {
    }
}