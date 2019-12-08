package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.JumpIfFalse;
import helpers.intCodeProgram.instructions.JumpIfTrue;
import helpers.intCodeProgram.instructions.OutputInstruction;

import java.util.List;

public abstract class Instruction {

    public enum ParameterMode {
        POSITION, IMMEDIATE;
    }

    public int opcode;

    public int startingIndex;

    public Instruction(int opcode, int startingIndex) {
        this.opcode = opcode;
        this.startingIndex = startingIndex;
    }

    public abstract int getNumberOfParametersAndOpcode();

    /**
     * @return new pointer value
     */
    public abstract int run(List<Integer> memoryState);

    public int[] getParameters(List<Integer> memoryState, int startingIndex) {
        int[] parameters = new int[getNumberOfParametersAndOpcode()];
        for(int i = 0; i < getNumberOfParametersAndOpcode(); i++) {
            parameters[i] = memoryState.get(startingIndex + i);
        }
        return parameters;
    }

    public int getValueOfParam(int paramPosition, List<Integer> memoryState) {
        // Get mode in which param is read
        ParameterMode parameterMode = ParameterMode.POSITION;

        // This does something that seemed smart where it checks if it's the last parameter to see if it's a setter.
        // That did no longer work for more complex opcodes, so i'm excluding those to prevent having to think.
        if(getNumberOfParametersAndOpcode() == paramPosition + 1
                && !(this instanceof OutputInstruction) && !(this instanceof JumpIfTrue) && !(this instanceof JumpIfFalse)  ) {
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
