package helpers.intCodeProgram;

import java.util.List;

public abstract class Instruction {

    public enum ParameterMode {
        POSITION, IMMEDIATE;
    }

    int opcode;

    int startingIndex;

    Instruction(int opcode, int startingIndex) {
        this.opcode = opcode;
        this.startingIndex = startingIndex;
    }

    abstract int getNumberOfParametersAndOpcode();

    abstract void run(List<Integer> memoryState);

    int[] getParameters(List<Integer> memoryState, int startingIndex) {
        int[] parameters = new int[getNumberOfParametersAndOpcode()];
        for(int i = 0; i < getNumberOfParametersAndOpcode(); i++) {
            parameters[i] = memoryState.get(startingIndex + i);
        }
        return parameters;
    }

    int getValueOfParam(int paramPosition, List<Integer> memoryState) {
        // Get mode in which param is read
        ParameterMode parameterMode = ParameterMode.POSITION;
        if(getNumberOfParametersAndOpcode() == paramPosition + 1) {
            parameterMode = ParameterMode.IMMEDIATE;
        }

        if(!(10 * Math.pow(10, paramPosition) > opcode)) {

            char[] b = String.valueOf(opcode).toCharArray();
            int a = b[b.length - 2 - paramPosition] - 48;
            parameterMode = a == 1 ? ParameterMode.IMMEDIATE : ParameterMode.POSITION;
        }

        int[] params = getParameters(memoryState, startingIndex);

        return parameterMode == ParameterMode.IMMEDIATE ? params[paramPosition]: memoryState.get(params[paramPosition]);
    }


}
