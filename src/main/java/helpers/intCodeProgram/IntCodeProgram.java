package helpers.intCodeProgram;

import java.util.List;

public class IntCodeProgram {
    List<Integer> memoryState;

    public IntCodeProgram(List<Integer> memoryState) {
        this.memoryState = memoryState;
    }

    public void setNoun(Integer noun) {
        if(noun != null) {
            memoryState.set(1, noun);
        }
    }

    public void setVerb(Integer verb) {
        if(verb != null) {
            memoryState.set(2, verb);
        }
    }

    private boolean processing = false;

    public void runProgram() {
        processing = true;

        for (int pointer = 0; pointer < memoryState.size() && processing; ) {
            pointer += processInstruction(memoryState, pointer);
        }
    }

    public List<Integer> getMemoryState() {
        return memoryState;
    }

    public int processInstruction(List<Integer> memoryState, int startingIndex) {
        int opCode = memoryState.get(startingIndex);
        Instruction instruction = InstructionFactory.getInstruction(opCode);
        if(instruction instanceof FinalizeInstruction) {
            processing = false;
        }
        instruction.run(memoryState, startingIndex);
        return instruction.getNumberOfParametersAndOpcode();
    }

}
