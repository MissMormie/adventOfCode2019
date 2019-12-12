package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_8_EqualsInstruction extends Instruction {
    public Opcode_8_EqualsInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        boolean equals = getValueOfParam(1, memoryState).equals((getValueOfParam(2, memoryState)));
        memoryState.set(getValueOfParam(3, memoryState, true).intValue(), new BigInteger(equals ? "1" : "0"));
//        System.out.println("Equals: Setting index: " +getValueOfParam(3, memoryState) + " to: " + (equals ? 1 : 0));

        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
