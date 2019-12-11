package helpers.intCodeProgram;

import java.math.BigInteger;
import java.util.List;

import static helpers.intCodeProgram.Instruction.ParameterMode.*;

public abstract class Instruction {

    public enum ParameterMode {
        POSITION, IMMEDIATE, RELATIVE
    }

    public int opcode;

    public int startingIndex;

    public Integer relativeBase;

    public ParameterMode[] parameterModes;

    public Instruction(int opcode, int startingIndex, int relativeBase) {
        this.opcode = opcode;
        this.startingIndex = startingIndex;
        this.relativeBase = relativeBase;
        createParameterModes();
    }

    private void createParameterModes() {
        parameterModes = new ParameterMode[4];
        parameterModes[1] = getParameterModes(opcode / 100 % 10);
        parameterModes[2] = getParameterModes(opcode / 1000 % 10);
        parameterModes[3] = getParameterModes(opcode / 10000 % 10);
    }

    private ParameterMode getParameterModes(int mode) {
        if (mode == 0) {
            return POSITION;
        }
        if (mode == 1) {
            return IMMEDIATE;
        }
        // mode == 2
        return RELATIVE;
    }

    public abstract int getNumberOfParametersAndOpcode();

    /**
     * @return new pointer value
     */
    public abstract int run(List<BigInteger> memoryState);

    public BigInteger getValueOfParam(int paramPosition, List<BigInteger> memoryState) {
        return getValueOfParam(paramPosition, memoryState, false);
    }

    public BigInteger getValueOfParam(int paramPosition, List<BigInteger> integerList, boolean isSetter) {
        ParameterMode paramMode = parameterModes[paramPosition];
        if (paramMode == RELATIVE) {
            return getValueOfParamRelativeMode(integerList, startingIndex, paramPosition);
        }
        if (paramMode == POSITION && !isSetter) {
            return getValueOfParamPositionMode(integerList, startingIndex, paramPosition);
        }
        if(paramMode == IMMEDIATE || paramMode == POSITION && isSetter) {
            return getValueOfParamImmediateMode(integerList, startingIndex, paramPosition);
        }
        return getValueOfParamRelativeSetterMode(integerList, startingIndex, paramPosition);
    }

    private BigInteger getValueOfParamImmediateMode(List<BigInteger> integerList, int startingIndex, int i) {
        return integerList.get(startingIndex + i);
    }

    private BigInteger getValueOfParamPositionMode(List<BigInteger> integerList, int startingIndex, int i) {
        return integerList.get(integerList.get(startingIndex + i).intValue());
    }

    private BigInteger getValueOfParamRelativeMode(List<BigInteger> integerList, int startingIndex, int i) {
        return integerList.get(integerList.get(startingIndex + i).intValue() + relativeBase);
    }

    private BigInteger getValueOfParamRelativeSetterMode(List<BigInteger> integerList, int startingIndex, int i) {
        return integerList.get(startingIndex + i).add(new BigInteger(relativeBase.toString()));
    }
}
