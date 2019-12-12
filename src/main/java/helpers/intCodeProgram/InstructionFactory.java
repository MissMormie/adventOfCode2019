package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.*;

import java.math.BigInteger;

public class InstructionFactory {
    public static Instruction getInstruction(BigInteger startCode, int startingIndex, int relativeBase) {
        int instruction = startCode.intValue();
        int opCode = instruction % 100; // get last two digits

        switch (opCode) {
            case 1: return new Opcode_1_AddInstruction(instruction, startingIndex, relativeBase);
            case 2: return new Opcode_2_MultiplyInstruction(instruction, startingIndex, relativeBase);
            case 3: return new Opcode_3_InputInstruction(instruction, startingIndex, relativeBase);
            case 4: return new Opcode_4_OutputInstruction(instruction, startingIndex, relativeBase);
            case 5: return new Opcode_5_JumpIfTrue(instruction, startingIndex, relativeBase);
            case 6: return new Opcode_6_JumpIfFalse(instruction, startingIndex, relativeBase);
            case 7: return new Opcode_7_LessThanInstruction(instruction, startingIndex, relativeBase);
            case 8: return new Opcode_8_EqualsInstruction(instruction, startingIndex, relativeBase);
            case 9: return new Opcode_9_AdjustRelativeBaseInstruction(instruction, startingIndex, relativeBase);
            case 99: return new Opcode_99_FinalizeInstruction(instruction, startingIndex, relativeBase);
        }
        throw new IllegalStateException("Unsupported opcode");
    }
}
