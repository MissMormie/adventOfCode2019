package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_5_JumpIfTrue extends Instruction {
    public Opcode_5_JumpIfTrue(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 3;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        if(getValueOfParam(1, memoryState).intValue() != 0) {
//            System.out.println("Jump if true: Changed pointer to: " + getValueOfParam(2, memoryState));

            return getValueOfParam(2, memoryState).intValue();
        } else {
//            System.out.println("Jump if true: Changed pointer to: " + startingIndex + getNumberOfParametersAndOpcode());
            return startingIndex + getNumberOfParametersAndOpcode();
        }

    }
}
