package Days;

import helpers.StringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2_1202ProgramAlarm {

    public static void main(String[] args) {
        System.out.println("answer A: " + runA(getInput(), 12, 2));
//        System.out.println("answer B: " + runB(getInput()));
    }

    public static int runA(String input, Integer noun, Integer verb) {
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");
        if(noun != null) {
            memoryState.set(1, noun);
        }
        if ( verb != null) {
            memoryState.set(2, verb);
        }
        runProgram(memoryState);

        return memoryState.get(0);
    }

    private static void runProgram(List<Integer> inputList) {
        boolean done = false;
        for(int pointer = 0; pointer<  inputList.size() && !done; pointer += 4) {
            done = doIntCodeProgram(inputList, pointer );
        }
    }

    public static boolean doIntCodeProgram(List<Integer> integerList, int startingIndex) {
        if(integerList.get(startingIndex) == 1) {
            System.out.println("Writing " + integerList.get(integerList.get(startingIndex + 1)) + " + " + integerList.get(integerList.get(startingIndex + 2)) + " to: " + integerList.get(startingIndex + 3));
            integerList.set(integerList.get(startingIndex + 3) , integerList.get(integerList.get(startingIndex + 1)) + integerList.get(integerList.get(startingIndex + 2)));
            return false;
        } else if(integerList.get(startingIndex) == 2) {
            System.out.println("Writing " + integerList.get(integerList.get(startingIndex + 1)) + " + " + integerList.get(integerList.get(startingIndex + 2)) + " to: " + integerList.get(startingIndex + 3));
            integerList.set(integerList.get(startingIndex + 3) , integerList.get(integerList.get(startingIndex + 1)) * integerList.get(integerList.get(startingIndex + 2)));
            return false;
        } else if( integerList.get(startingIndex)== 99) {
            return true;
        }
        throw new IllegalStateException("Unsupported opcode");
    }

    public static int runB(String input) {
        // Yeah recursion hell
        int i = 0;
        int j = 0;
        outerloop:
        for(;i < 100; i++) {
            for(;j < 100; j++) {
                List<Integer> inputList = StringHelper.getListOfNumbersSeperatedBy(input, ",");
                inputList.set(1, i);
                inputList.set(2, j);
                try {
                    runProgram(inputList);
                    if(inputList.get(0) == 19690720) {
                        break outerloop;
                    }
                }
                catch(IllegalStateException e) {
                    // illegal memory state, moving on.
                }
            }
            j = 0;
        }

        return i * 100 + j;
    }

    public static String getInputA() {
        return "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,2,23,10,27,1,27,5,31,1,31,6,35,1,6,35,39,2,39,13,43,1,9,43,47,2,9,47,51,1,51,6,55,2,55,10,59,1,59,5,63,2,10,63,67,2,9,67,71,1,71,5,75,2,10,75,79,1,79,6,83,2,10,83,87,1,5,87,91,2,9,91,95,1,95,5,99,1,99,2,103,1,103,13,0,99,2,14,0,0";
    }


    public static String getInput() {
        return "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,2,23,10,27,1,27,5,31,1,31,6,35,1,6,35,39,2,39,13,43,1,9,43,47,2,9,47,51,1,51,6,55,2,55,10,59,1,59,5,63,2,10,63,67,2,9,67,71,1,71,5,75,2,10,75,79,1,79,6,83,2,10,83,87,1,5,87,91,2,9,91,95,1,95,5,99,1,99,2,103,1,103,13,0,99,2,14,0,0";
    }
}
