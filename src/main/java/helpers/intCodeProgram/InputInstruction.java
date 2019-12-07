package helpers.intCodeProgram;

import java.util.List;

public class InputInstruction extends Instruction {
    InputInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    void run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(1, memoryState), 1); // input hardcoded for now
    }
}
