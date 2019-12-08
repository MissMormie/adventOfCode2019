package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class JumpIfFalse extends Instruction {
    public JumpIfFalse(int opcode, int startingIndex) {
        super(opcode, startingIndex);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    public int run(List<Integer> memoryState) {
        if (getValueOfParam(1, memoryState) == 0) {
//            System.out.println("Jump if false: Changed pointer to: " + getValueOfParam(2, memoryState));
            return getValueOfParam(2, memoryState);
        } else {
//            System.out.println("Jump if false: Changed pointer to: " + startingIndex + getNumberOfParametersAndOpcode());
            return startingIndex + getNumberOfParametersAndOpcode();
        }

    }
}