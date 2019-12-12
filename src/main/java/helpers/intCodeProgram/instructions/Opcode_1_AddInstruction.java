package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_1_AddInstruction extends Instruction {

    public Opcode_1_AddInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 4;
    }

    /**
     * Opcode 1 adds together numbers read from two positions and stores the result in a third position.
     * The three integers immediately after the opcode tell you these three positions
     */
    @Override
    public int run(List<BigInteger> memoryState) {
        memoryState.set(getValueOfParam(3, memoryState, true).intValue(),
                getValueOfParam(1, memoryState).add(getValueOfParam(2, memoryState)));
        return startingIndex + getNumberOfParametersAndOpcode();
    }

}
