package helpers.intCodeProgram;

import java.util.List;

public class ReplaceInstruction extends Instruction {

    ReplaceInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    void run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(2, memoryState),
                getValueOfParam(1, memoryState));
    }
}
