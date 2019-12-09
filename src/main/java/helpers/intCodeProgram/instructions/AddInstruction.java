package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class AddInstruction extends Instruction {

    public AddInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState, true).intValue(),
                getValueOfParam(1, memoryState).add(getValueOfParam(2, memoryState)));
        return startingIndex + getNumberOfParametersAndOpcode();
    }

}
