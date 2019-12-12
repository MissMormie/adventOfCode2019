package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_9_AdjustRelativeBaseInstruction extends Instruction {
    public int adjustRelativeBase;

    public Opcode_9_AdjustRelativeBaseInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        adjustRelativeBase = getValueOfParam(1, memoryState).intValue();
        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
