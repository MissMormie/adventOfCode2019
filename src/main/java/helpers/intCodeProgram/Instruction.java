package helpers.intCodeProgram;

import java.math.BigInteger;
import java.util.List;

import static helpers.intCodeProgram.Instruction.ParameterMode.*;

public abstract class Instruction {

    public enum ParameterMode {
        POSITION_0, IMMEDIATE_1, RELATIVE_2
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
            return POSITION_0;
        }
        if (mode == 1) {
            return IMMEDIATE_1;
        }
        // mode == 2
        return RELATIVE_2;
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
        if(isSetter) {
            if(paramMode == POSITION_0 || paramMode == IMMEDIATE_1) {
                return getValueOfParamImmediateMode(integerList, startingIndex, paramPosition);
            } else if(paramMode == RELATIVE_2) {
                return getValueOfParamRelativeSetterMode(integerList, startingIndex, paramPosition);
            }
            throw new IllegalStateException("Setter in immediate mode is not allowed at pointer " + startingIndex);
        }
        if (paramMode == POSITION_0) {
            return getValueOfParamPositionMode(integerList, startingIndex, paramPosition);
        }

        if(paramMode == IMMEDIATE_1) {
            return getValueOfParamImmediateMode(integerList, startingIndex, paramPosition);
        }

        // paramMode == RELATIVE_2)
        return getValueOfParamRelativeMode(integerList, startingIndex, paramPosition);
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
