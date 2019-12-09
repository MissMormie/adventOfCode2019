package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class InputInstruction extends Instruction {
    public BigInteger input;

    public InputInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        memoryState.set(getValueOfParam(1, memoryState, true).intValue(), input); // input hardcoded for now
        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
