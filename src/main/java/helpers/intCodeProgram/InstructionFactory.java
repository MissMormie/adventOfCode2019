package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.*;

public class InstructionFactory {
    public static Instruction getInstruction(int startCode, int startingIndex, int relativeBase) {
        int opCode = startCode % 100; // get last two digits

        switch (opCode) {
            case 1: return new AddInstruction(startCode, startingIndex, relativeBase);
            case 2: return new MultiplyInstruction(startCode, startingIndex, relativeBase);
            case 3: return new InputInstruction(startCode, startingIndex, relativeBase);
            case 4: return new OutputInstruction(startCode, startingIndex, relativeBase);
            case 5: return new JumpIfTrue(startCode, startingIndex, relativeBase);
            case 6: return new JumpIfFalse(startCode, startingIndex, relativeBase);
            case 7: return new LessThanInstruction(startCode, startingIndex, relativeBase);
            case 8: return new EqualsInstruction(startCode, startingIndex, relativeBase);
            case 9: return new AdjustRelativeBaseInstruction(startCode, startingIndex, relativeBase);
            case 99: return new FinalizeInstruction(startCode, startingIndex, relativeBase);
        }
        throw new IllegalStateException("Unsupported opcode");
    }
}
