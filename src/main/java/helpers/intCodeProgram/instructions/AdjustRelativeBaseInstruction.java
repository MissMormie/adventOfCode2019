package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class AdjustRelativeBaseInstruction extends Instruction {
    public int adjustRelativeBase;

    public AdjustRelativeBaseInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<Integer> memoryState) {
        adjustRelativeBase = getValueOfParam(2, memoryState);
        return 2;
    }
}
