package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_2_MultiplyInstruction extends Instruction {
    public Opcode_2_MultiplyInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState, true).intValue() ,
                getValueOfParam(1, memoryState).multiply(getValueOfParam(2, memoryState)));
        return startingIndex + getNumberOfParametersAndOpcode();

    }

}
