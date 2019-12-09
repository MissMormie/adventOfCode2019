package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.*;

import java.math.BigInteger;

public class InstructionFactory {
    public static Instruction getInstruction(BigInteger startCode, int startingIndex, int relativeBase) {
        int instruction = startCode.intValue();
        int opCode = instruction % 100; // get last two digits

        switch (opCode) {
            case 1: return new AddInstruction(instruction, startingIndex, relativeBase);
            case 2: return new MultiplyInstruction(instruction, startingIndex, relativeBase);
            case 3: return new InputInstruction(instruction, startingIndex, relativeBase);
            case 4: return new OutputInstruction(instruction, startingIndex, relativeBase);
            case 5: return new JumpIfTrue(instruction, startingIndex, relativeBase);
            case 6: return new JumpIfFalse(instruction, startingIndex, relativeBase);
            case 7: return new LessThanInstruction(instruction, startingIndex, relativeBase);
            case 8: return new EqualsInstruction(instruction, startingIndex, relativeBase);
            case 9: return new AdjustRelativeBaseInstruction(instruction, startingIndex, relativeBase);
            case 99: return new FinalizeInstruction(instruction, startingIndex, relativeBase);
        }
        throw new IllegalStateException("Unsupported opcode");
    }
}
