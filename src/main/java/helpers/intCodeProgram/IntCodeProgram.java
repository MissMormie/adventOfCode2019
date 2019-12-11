package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.AdjustRelativeBaseInstruction;
import helpers.intCodeProgram.instructions.FinalizeInstruction;
import helpers.intCodeProgram.instructions.InputInstruction;
import helpers.intCodeProgram.instructions.OutputInstruction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IntCodeProgram {
    List<BigInteger> memoryState;
    public BigInteger lastOutput;
    private boolean finished = false;
    private boolean waiting = false;
    private int relativeBase = 0;

    public IntCodeProgram handOutputTo;

    public List<BigInteger> inputList = new ArrayList<>();

    public IntCodeProgram(List<BigInteger> memoryState) {
        this.memoryState = memoryState;
    }

    public void addInput(BigInteger input) {
        inputList.add(input);
    }

    public void addInput(Integer input) {
        inputList.add(new BigInteger(input.toString()));
    }

    public void setHandOutputTo(IntCodeProgram handOutputTo) {
        this.handOutputTo = handOutputTo;
    }

    public void setNoun(BigInteger noun) {
        if (noun != null) {
            memoryState.set(1, noun);
        }
    }

    public void setVerb(BigInteger verb) {
        if (verb != null) {
            memoryState.set(2, verb);
        }
    }

    public BigInteger runProgram() {
        finished = false;
        // Sure I build actual support for larger memorySizes. But I can also just add some 0's and assume it works ;)
        while(memoryState.size() < 10000) {
            memoryState.add(new BigInteger("0"));
        }

        for (int pointer = 0; pointer < memoryState.size() && !finished; ) {
            pointer = processInstruction(memoryState, pointer);
        }
        return lastOutput;
    }

    public List<BigInteger> getMemoryState() {
        return memoryState;
    }

    public int processInstruction(List<BigInteger> memoryState, int startingIndex) throws IllegalStateException {
        waiting = false;
        BigInteger opCode = memoryState.get(startingIndex);
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
                handOutputTo.addInput(lastOutput);
            }
            lastOutput = ((OutputInstruction) instruction).getOutput();
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

    public BigInteger getLastOutput() {
        return lastOutput;
    }
}
