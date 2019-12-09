package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class LessThanInstruction extends Instruction {
    public LessThanInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 0;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        boolean lessThan = getValueOfParam(1, memoryState).compareTo((getValueOfParam(2, memoryState)))  < 0;
        memoryState.set(getValueOfParam(3, memoryState, true).intValue(), new BigInteger(lessThan ? "1" : "0"));
//        System.out.println("Less Than: Setting index: " + getValueOfParam(3, memoryState) + " to: " + (lessThan ? 1 : 0));

        return startingIndex + getNumberOfParametersAndOpcode();
    }
}
