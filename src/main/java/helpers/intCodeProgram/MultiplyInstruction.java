package helpers.intCodeProgram;

import java.util.List;

public class MultiplyInstruction extends Instruction{
    MultiplyInstruction(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    void run(List<Integer> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState) ,
                getValueOfParam(1, memoryState) * getValueOfParam(2, memoryState));
    }

}
