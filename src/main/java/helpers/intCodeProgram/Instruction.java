package helpers.intCodeProgram;

import java.util.List;

public abstract class Instruction {
    int parameter;

    abstract int getNumberOfParametersAndOpcode();

    abstract void run(List<Integer> memoryState, int startingIndex);

    int[] getParameters(List<Integer> memoryState, int startingIndex) {
        int[] parameters = new int[getNumberOfParametersAndOpcode()];
        for(int i = 0; i < getNumberOfParametersAndOpcode(); i++) {
            parameters[i] = memoryState.get(startingIndex + i);
        }
        return parameters;
    }
}
