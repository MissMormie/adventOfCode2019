package helpers.intCodeProgram;

import java.util.List;

public class OutputInstruction extends Instruction {

    OutputInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    void run(List<Integer> memoryState) {
        getValueOfParam(1, memoryState);
        System.out.print(getValueOfParam(1, memoryState) + " ");
    }
}
