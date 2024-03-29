package helpers.intCodeProgram.instructions;

import helpers.intCodeProgram.Instruction;

import java.math.BigInteger;
import java.util.List;

public class Opcode_4_OutputInstruction extends Instruction {
    private BigInteger output;

    public Opcode_4_OutputInstruction(int opcode, int startingIndex, int relativeBase) {
        super(opcode, startingIndex, relativeBase);
    }

    @Override
    public int getNumberOfParametersAndOpcode() {
        return 2;
    }

    @Override
    public int run(List<BigInteger> memoryState) {
        output = getValueOfParam(1, memoryState);
        System.out.println("output for opcode: " + opcode + " is: " + output.toString());
//        System.out.print(getValueOfParam(1, memoryState) + " ");
        return startingIndex + getNumberOfParametersAndOpcode();
    }

    public BigInteger getOutput() {
        return output;
    }
}
