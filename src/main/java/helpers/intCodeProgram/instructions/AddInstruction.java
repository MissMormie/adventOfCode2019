package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class AddInstruction extends Instruction {

    public AddInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState),
                getValueOfParam(1, memoryState) + getValueOfParam(2, memoryState));
        return startingIndex + getNumberOfParametersAndOpcode();
    }

}
