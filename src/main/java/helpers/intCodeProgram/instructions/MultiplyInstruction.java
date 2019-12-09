package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class MultiplyInstruction extends Instruction {
    public MultiplyInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState, true) ,
                getValueOfParam(1, memoryState) * getValueOfParam(2, memoryState));
        return startingIndex + getNumberOfParametersAndOpcode();

    }

}
