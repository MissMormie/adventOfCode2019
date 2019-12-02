package helpers.intCodeProgram;

public class InstructionFactory {
    public static Instruction getInstruction(int opCode) {
        switch (opCode) {
            case 1: return new AddInstruction();
            case 2: return new MultiplyInstruction();
//            case 3: return new ReplaceInstruction();
            case 99: return new FinalizeInstruction();
        }
        throw new IllegalStateException("Unsupported opcode");
    }
}
