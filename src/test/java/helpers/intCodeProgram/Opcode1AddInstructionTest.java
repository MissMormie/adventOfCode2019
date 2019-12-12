package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.Opcode_2_MultiplyInstruction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Opcode1AddInstructionTest {

    @Test
    void run() {
        Instruction instruction = new Opcode_2_MultiplyInstruction(1002,0, 0);
        List<Integer> memoryState = IntStream.of(1001, 4, 3, 4, 33).boxed().collect(Collectors.toList());

        List<Integer> answer = IntStream.of(1001, 4, 3, 4, 33).boxed().collect(Collectors.toList());
        assertEquals(answer, memoryState);
    }
}