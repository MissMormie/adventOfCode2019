package helpers.intCodeProgram;

import java.util.List;

public class MultiplyInstruction extends Instruction{
    @Override
    int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    void run(List<Integer> memoryState, int startingIndex) {
        int[] params = getParameters(memoryState, startingIndex);
        memoryState.set(params[3] , memoryState.get(params[1]) * memoryState.get(params[2]));
    }

}
