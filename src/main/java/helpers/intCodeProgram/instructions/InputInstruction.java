package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class InputInstruction extends Instruction {
    public Integer input;

    public InputInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(1, memoryState), input); // input hardcoded for now
        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
