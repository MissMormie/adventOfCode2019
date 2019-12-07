package helpers.intCodeProgram;

public class InstructionFactory {
    public static Instruction getInstruction(int startCode, int startingIndex) {
        int opCode = startCode % 100; // get last two digits

        switch (opCode) {
            case 1: return new AddInstruction(startCode, startingIndex);
            case 2: return new MultiplyInstruction(startCode, startingIndex);
            case 3: return new InputInstruction(startCode, startingIndex);
            case 4: return new OutputInstruction(startCode, startingIndex);
            case 99: return new FinalizeInstruction(startCode, startingIndex);
        }
        throw new IllegalStateException("Unsupported opcode");
    }
}
