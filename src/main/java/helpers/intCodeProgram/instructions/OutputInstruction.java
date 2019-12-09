package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class OutputInstruction extends Instruction {
    private Integer output;

    public OutputInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<Integer> memoryState) {
        output = getValueOfParam(1, memoryState);
//        System.out.print(getValueOfParam(1, memoryState) + " ");
        return startingIndex + getNumberOfParametersAndOpcode();
    }

    public int getOutput() {
        return output;
    }
}