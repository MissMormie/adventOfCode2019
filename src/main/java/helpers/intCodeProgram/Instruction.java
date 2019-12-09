package helpers.intCodeProgram;

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

    public ParameterMode[] parameterModes;

    public Instruction(int opcode, int startingIndex, int relativeBase) {
        this.opcode = opcode;
        this.startingIndex = startingIndex;
        createParameterModes();
    }

    private void createParameterModes() {
        parameterModes = new ParameterMode[4];
        parameterModes[1] = getParameterModes(opcode / 100 % 10) ;
        parameterModes[2] = getParameterModes(opcode / 1000 % 10) ;
        parameterModes[3] = getParameterModes(opcode / 10000 % 10) ;
        }

    private ParameterMode getParameterModes(int mode) {
        if(mode == 0) {
        return POSITION;
        }
        if(mode == 1) {
        return IMMEDIATE;
        }
        // mode == 2
        return RELATIVE;
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

    public int getValueOfParam(int paramPosition, List<Integer> memoryState) {
        return getValueOfParam(paramPosition, memoryState, false);
    }

    public Integer getValueOfParam(int paramPosition, List<Integer> integerList, boolean isSetter) {
        ParameterMode paramMode = parameterModes[paramPosition];
        if (paramMode == RELATIVE) {
            return getValueOfParamRelativeMode(integerList, startingIndex, paramPosition);
        }
        if(paramMode == POSITION && !isSetter) {
            return getValueOfParamPositionMode(integerList, startingIndex, paramPosition);
        }
        // paramMode == IMMEDIATE
        return getValueOfParamImmediateMode(integerList, startingIndex, paramPosition);
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
