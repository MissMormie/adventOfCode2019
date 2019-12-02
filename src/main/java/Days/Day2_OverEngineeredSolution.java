package Days;

import helpers.StringHelper;
import helpers.intCodeProgram.IntCodeProgram;

import java.util.List;

public class Day2_OverEngineeredSolution {
    public static void main(String[] args) {
        System.out.println("answer A: " + runA(Day2_1202ProgramAlarm.getInput(), 12, 2)); // 3931283
        System.out.println("answer B: " + runB(Day2_1202ProgramAlarm.getInput())); // 6979
    }

    public static int runA(String input, Integer verb, Integer noun) {
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");
        IntCodeProgram intCodeProgram = runIntCodeProgram(memoryState, null, null);

        return intCodeProgram.getMemoryState().get(0);
    }

    public static IntCodeProgram runIntCodeProgram(List<Integer> memoryState, Integer noun, Integer verb) {
        IntCodeProgram intCodeProgram = new IntCodeProgram(memoryState);
        intCodeProgram.setNoun(noun);
        intCodeProgram.setVerb(verb);

        intCodeProgram.runProgram();
        return intCodeProgram;
    }

    public static int runB(String input) {
        int verb = 0;
        int noun = 0;

        // Yeah recursions! It's like an unending messed up string of christmas lights that just won't end.
        outerLoop:
        for(;verb < 100; verb++) {
            for(;noun < 100; noun++) {
                List<Integer> inputList = StringHelper.getListOfNumbersSeperatedBy(input, ",");
                inputList.set(1, verb);
                inputList.set(2, noun);
                try {
                    runIntCodeProgram(inputList, verb, noun);
                    if(inputList.get(0) == 19690720) {
                        break outerLoop;
                    }
                }
                catch(IllegalStateException e) {
                    // illegal memoryState, moving on.
                }
            }
            noun = 0;
        }

        return verb * 100 + noun;
    }
}
