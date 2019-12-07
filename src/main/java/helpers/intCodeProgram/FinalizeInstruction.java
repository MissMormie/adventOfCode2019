package helpers.intCodeProgram;

import java.util.List;

public class FinalizeInstruction extends Instruction {
    FinalizeInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    int getNumberOfParametersAndOpcode() {
        return 1;
    }

    @Override
    void run(List<Integer> memoryState) {

    }
}
