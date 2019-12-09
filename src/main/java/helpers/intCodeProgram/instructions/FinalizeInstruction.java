package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class FinalizeInstruction extends Instruction {
    public FinalizeInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 1;
    }

    @Override
    public int run(List<Integer> memoryState) {
        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
