package helpers.intCodeProgram;

import helpers.intCodeProgram.instructions.Opcode_9_AdjustRelativeBaseInstruction;
import helpers.intCodeProgram.instructions.Opcode_99_FinalizeInstruction;
import helpers.intCodeProgram.instructions.Opcode_3_InputInstruction;
import helpers.intCodeProgram.instructions.Opcode_4_OutputInstruction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IntCodeProgram implements InputReceiver {
    List<BigInteger> memoryState;
    public BigInteger lastOutput;
    private boolean finished = false;
    private boolean waiting = false;
    private int relativeBase = 0;

    public InputReceiver inputReceiver;
//    public IntCodeProgram handOutputTo;

    public List<BigInteger> inputList = new ArrayList<>();

    public IntCodeProgram(List<BigInteger> memoryState) {
        this.memoryState = memoryState;
    }

    public void addInput(BigInteger input) {
        inputList.add(input);
    }

    @Override
    public void receiveInput(BigInteger input) {
        inputList.add(input);

    }

    public void addInput(Integer input) {
        inputList.add(new BigInteger(input.toString()));
    }

    public void setInputReceiver(InputReceiver inputReceiver) {
        this.inputReceiver = inputReceiver;
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
        if (instruction instanceof Opcode_99_FinalizeInstruction) {
            finished = true;
        }

        if (instruction instanceof Opcode_3_InputInstruction) {
            if (inputList.size() == 0) {
                waiting = true;
                throw new IllegalStateException();
            }
            ((Opcode_3_InputInstruction) instruction).input = inputList.remove(0);
        }

        int run = instruction.run(memoryState);
        if (instruction instanceof Opcode_4_OutputInstruction) {
            if (inputReceiver != null) {
                inputReceiver.receiveInput(lastOutput);
            }
            lastOutput = ((Opcode_4_OutputInstruction) instruction).getOutput();
        }

        if(instruction instanceof Opcode_9_AdjustRelativeBaseInstruction) {
            relativeBase += ((Opcode_9_AdjustRelativeBaseInstruction) instruction).adjustRelativeBase;
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
