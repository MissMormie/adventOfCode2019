package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class JumpIfFalse extends Instruction {
    public JumpIfFalse(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        if (getValueOfParam(1, memoryState).intValue() == 0) {
//            System.out.println("Jump if false: Changed pointer to: " + getValueOfParam(2, memoryState));
            return getValueOfParam(2, memoryState).intValue();
        } else {
//            System.out.println("Jump if false: Changed pointer to: " + startingIndex + getNumberOfParametersAndOpcode());
            return startingIndex + getNumberOfParametersAndOpcode();
        }

    }
}