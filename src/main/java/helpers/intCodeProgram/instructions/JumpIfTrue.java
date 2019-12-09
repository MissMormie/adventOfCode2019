package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.util.List;

public class JumpIfTrue extends Instruction {
    public JumpIfTrue(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    public int run(List<Integer> memoryState) {
        if(getValueOfParam(1, memoryState) != 0) {
//            System.out.println("Jump if true: Changed pointer to: " + getValueOfParam(2, memoryState));

            return getValueOfParam(2, memoryState);
        } else {
//            System.out.println("Jump if true: Changed pointer to: " + startingIndex + getNumberOfParametersAndOpcode());
            return startingIndex + getNumberOfParametersAndOpcode();
        }

    }
}
