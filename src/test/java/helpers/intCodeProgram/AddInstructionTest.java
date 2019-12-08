package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.MultiplyInstruction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AddInstructionTest {

    @Test
    void run() {
        Instruction instruction = new MultiplyInstruction(1002,0);
        List<Integer> memoryState = IntStream.of(1001, 4, 3, 4, 33).boxed().collect(Collectors.toList());

        List<Integer> answer = IntStream.of(1001, 4, 3, 4, 33).boxed().collect(Collectors.toList());
        assertEquals(answer, memoryState);
    }
}