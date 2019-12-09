package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class EqualsInstruction extends Instruction {
    public EqualsInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<Integer> memoryState) {
        boolean equals = getValueOfParam(1, memoryState) == (getValueOfParam(2, memoryState));
        memoryState.set(getValueOfParam(3, memoryState), equals ? 1 : 0);
//        System.out.println("Equals: Setting index: " +getValueOfParam(3, memoryState) + " to: " + (equals ? 1 : 0));

        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
