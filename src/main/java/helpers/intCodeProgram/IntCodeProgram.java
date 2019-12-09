package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.AdjustRelativeBaseInstruction;
import helpers.intCodeProgram.instructions.FinalizeInstruction;
import helpers.intCodeProgram.instructions.InputInstruction;
import helpers.intCodeProgram.instructions.OutputInstruction;

import java.util.ArrayList;
import java.util.List;

public class IntCodeProgram {
    List<Integer> memoryState;
    public Integer lastOutput;
    private boolean finished = false;
    private boolean waiting = false;
    private int relativeBase = 0;

    public IntCodeProgram handOutputTo;

    public List<Integer> inputList = new ArrayList<>();

    public IntCodeProgram(List<Integer> memoryState) {
        this.memoryState = memoryState;
    }

    public void addInput(int input) {
        inputList.add(input);
    }

    public void setHandOutputTo(IntCodeProgram handOutputTo) {
        this.handOutputTo = handOutputTo;
    }

    public void setNoun(Integer noun) {
        if (noun != null) {
            memoryState.set(1, noun);
        }
    }

    public void setVerb(Integer verb) {
        if (verb != null) {
            memoryState.set(2, verb);
        }
    }

    public int runProgram() {
        finished = false;

        for (int pointer = 0; pointer < memoryState.size() && !finished; ) {
            pointer = processInstruction(memoryState, pointer);
        }
        return lastOutput;
    }

    public List<Integer> getMemoryState() {
        return memoryState;
    }

    public int processInstruction(List<Integer> memoryState, int startingIndex) throws IllegalStateException {
        waiting = false;
        int opCode = memoryState.get(startingIndex);
        Instruction instruction = InstructionFactory.getInstruction(opCode, startingIndex, relativeBase);

        // Check if program is finished
        if (instruction instanceof FinalizeInstruction) {
            finished = true;
        }

        if (instruction instanceof InputInstruction) {
            if (inputList.size() == 0) {
                waiting = true;
                throw new IllegalStateException();
            }
            ((InputInstruction) instruction).input = inputList.remove(0);
        }

        int run = instruction.run(memoryState);
        if (instruction instanceof OutputInstruction) {
            if (handOutputTo != null) {
                lastOutput = ((OutputInstruction) instruction).getOutput();
            }
            handOutputTo.addInput(lastOutput);
        }

        if(instruction instanceof AdjustRelativeBaseInstruction) {
            relativeBase += ((AdjustRelativeBaseInstruction) instruction).adjustRelativeBase;
        }
        return run;
    }

    int pointer = 0;

    public void runProgramUntilWaitingForInput() {

        for (; pointer < memoryState.size() && !finished; ) {
            try {
                pointer = processInstruction(memoryState, pointer);
            } catch (IllegalStateException e) {
                // No input available, waiting.
                return;
            }
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public Integer getLastOutput() {
        return lastOutput;
    }
}
