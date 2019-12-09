package helpers.intCodeProgram;

import Days.Day5_TakeTwo;
import helpers.intCodeProgram.instructions.JumpIfFalse;
import helpers.intCodeProgram.instructions.JumpIfTrue;
import helpers.intCodeProgram.instructions.OutputInstruction;

import java.util.List;

import static helpers.intCodeProgram.Instruction.ParameterMode.*;

public abstract class Instruction {

    public enum ParameterMode {
        POSITION, IMMEDIATE, RELATIVE
    }

    public int opcode;

    public int startingIndex;

    public int relativeBase;

    public Instruction(int opcode, int startingIndex, int relativeBase) {
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
        ParameterMode parameterMode = getParameterMode(paramPosition);

        if(!(10 * Math.pow(10, paramPosition) > opcode)) {

            char[] b = String.valueOf(opcode).toCharArray();
            int a = b[b.length - 2 - paramPosition] - 48;
            parameterMode = a == 1 ? IMMEDIATE : POSITION;
        }

        int[] params = getParameters(memoryState, startingIndex);

        return parameterMode == IMMEDIATE ? params[paramPosition]: memoryState.get(params[paramPosition]);
    }

    private ParameterMode getParameterMode(int paramPosition) {
        // Get mode in which param is read
        ParameterMode parameterMode = POSITION;

        // This does something that seemed smart where it checks if it's the last parameter to see if it's a setter.
        // That did no longer work for more complex opcodes, so i'm excluding those to prevent having to think.
        if(getNumberOfParametersAndOpcode() == paramPosition + 1
                && !(this instanceof OutputInstruction) && !(this instanceof JumpIfTrue) && !(this instanceof JumpIfFalse)  ) {
            parameterMode = IMMEDIATE;
        }
        return parameterMode;
    }

    public Integer getValueOfParam(List<Integer> integerList, int startingIndex, int i, ParameterMode paramMode) {
        if(paramMode == POSITION) {
            return getValueOfParamPositionMode(integerList, startingIndex, i);
        }
        if(paramMode == IMMEDIATE) {
            return getValueOfParamImmediateMode(integerList, startingIndex, i);
        }
        if (paramMode == RELATIVE) {
            return getValueOfParamRelativeMode(integerList, startingIndex, i);
        }
        throw new IllegalStateException("Unknown parameter mode" + paramMode);
    }

    private Integer getValueOfParamImmediateMode(List<Integer> integerList, int startingIndex, int i) {
        return integerList.get(startingIndex + i);
    }

    private Integer getValueOfParamPositionMode (List<Integer> integerList, int startingIndex, int i) {
        return integerList.get(integerList.get(startingIndex + i));
    }

    private Integer getValueOfParamRelativeMode (List<Integer> integerList, int startingIndex, int i) {
        return integerList.get(integerList.get(startingIndex + i + relativeBase));
    }

}
