package helpers.intCodeProgram;

import java.util.List;

public class ReplaceInstruction extends Instruction {
    @Override
    int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    void run(List<Integer> memoryState, int startingIndex) {
        int[] params = getParameters(memoryState, startingIndex);
        memoryState.set(params[3] , memoryState.get(params[1]));
    }
}
