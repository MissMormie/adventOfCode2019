package helpers.intCodeProgram;

import java.util.List;

public class FinalizeInstruction extends Instruction {
    @Override
    int getNumberOfParametersAndOpcode() {
        return 1;
    }

    @Override
    void run(List<Integer> memoryState, int startingIndex) {

    }
}
