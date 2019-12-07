package Days;

import helpers.StringHelper;

import java.util.List;

import static Days.Day5_TakeTwo.ParameterMode.*;

public class Day5_TakeTwo {

    public static int runA(String input) {
        List<Integer> memoryState = StringHelper.getListOfNumbersSeperatedBy(input, ",");
        runProgram(memoryState);

        return memoryState.get(0);
    }

    public static void runProgram(List<Integer> integerList) {
        for(int pointer = 0; pointer <  integerList.size();) {
            Integer instruction = integerList.get(pointer);

            // Get opcode and modes for instruction 1 and 2
            int opcode = instruction % 100;
            ParameterMode modeInstruction1 = instruction / 100 % 10  == 1 ? IMMEDIATE : POSITION ;
            ParameterMode modeInstruction2 = instruction / 1000 % 10  == 1 ? IMMEDIATE : POSITION ;

            if(opcode == 1) { // add instruction
                integerList.set(integerList.get(pointer + 3) , getValueOfParam(integerList, pointer, 1, modeInstruction1) + getValueOfParam(integerList, pointer, 2, modeInstruction2));
                pointer += 4;

            } else if(opcode == 2) { // multiply instruction
                integerList.set(integerList.get(pointer + 3), getValueOfParam(integerList, pointer, 1, modeInstruction1) * getValueOfParam(integerList, pointer, 2, modeInstruction2));
                pointer += 4;

            } else if(opcode == 3) { // input instruction
                integerList.set(integerList.get(pointer + 1), 1);
                pointer += 2;

            } else if (opcode == 4) { // output instruction
                System.out.println("value at address: " + (pointer + 1) + " is: " + integerList.get(pointer + 1));
                pointer += 2;

            } else if( opcode == 99) {
                return;
            } else {
                throw new IllegalStateException("Unsupported opcode");
            }
        }
        throw new IllegalStateException("Finished running without finding opcode 99");
    }

    enum ParameterMode {
        IMMEDIATE, POSITION
    }

    public static Integer getValueOfParam(List<Integer> integerList, int startingIndex, int i, ParameterMode paramMode) {
        if(paramMode == POSITION) {
            return getValueOfParamPositionMode(integerList,startingIndex, i);
        }
        return getValueOfParamImmediateMode(integerList, startingIndex, i);
    }

    private static Integer getValueOfParamImmediateMode(List<Integer> integerList, int startingIndex, int i) {
        return integerList.get(startingIndex + i);
    }

    private static Integer getValueOfParamPositionMode (List<Integer> integerList, int startingIndex, int i) {
        return integerList.get(integerList.get(startingIndex + i));
    }

    public static int runB(String input) {
        int answer = 1;

        // DON'T FORGET TO ADD THE ANSWER
        return answer;
    }

    public static String getInput() {
        return "3,225,1,225,6,6,1100,1,238,225,104,0,1101,11,91,225,1002,121,77,224,101,-6314,224,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,1102,74,62,225,1102,82,7,224,1001,224,-574,224,4,224,102,8,223,223,1001,224,3,224,1,224,223,223,1101,28,67,225,1102,42,15,225,2,196,96,224,101,-4446,224,224,4,224,102,8,223,223,101,6,224,224,1,223,224,223,1101,86,57,225,1,148,69,224,1001,224,-77,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1101,82,83,225,101,87,14,224,1001,224,-178,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,38,35,225,102,31,65,224,1001,224,-868,224,4,224,1002,223,8,223,1001,224,5,224,1,223,224,223,1101,57,27,224,1001,224,-84,224,4,224,102,8,223,223,1001,224,7,224,1,223,224,223,1101,61,78,225,1001,40,27,224,101,-89,224,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,226,224,1002,223,2,223,1006,224,329,101,1,223,223,8,226,677,224,102,2,223,223,1005,224,344,101,1,223,223,1107,226,677,224,102,2,223,223,1006,224,359,101,1,223,223,1007,226,226,224,102,2,223,223,1006,224,374,101,1,223,223,7,677,677,224,102,2,223,223,1005,224,389,1001,223,1,223,108,677,677,224,1002,223,2,223,1005,224,404,101,1,223,223,1008,226,226,224,102,2,223,223,1005,224,419,1001,223,1,223,1107,677,226,224,102,2,223,223,1005,224,434,1001,223,1,223,1108,677,677,224,102,2,223,223,1006,224,449,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,464,101,1,223,223,1008,677,677,224,102,2,223,223,1005,224,479,101,1,223,223,1007,226,677,224,1002,223,2,223,1006,224,494,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,509,101,1,223,223,1007,677,677,224,1002,223,2,223,1006,224,524,101,1,223,223,107,226,226,224,102,2,223,223,1006,224,539,101,1,223,223,107,226,677,224,102,2,223,223,1005,224,554,1001,223,1,223,7,677,226,224,102,2,223,223,1006,224,569,1001,223,1,223,107,677,677,224,1002,223,2,223,1005,224,584,101,1,223,223,1107,677,677,224,102,2,223,223,1005,224,599,101,1,223,223,1108,226,677,224,102,2,223,223,1006,224,614,101,1,223,223,8,226,226,224,102,2,223,223,1006,224,629,101,1,223,223,108,226,677,224,102,2,223,223,1005,224,644,1001,223,1,223,108,226,226,224,102,2,223,223,1005,224,659,101,1,223,223,1108,677,226,224,102,2,223,223,1006,224,674,1001,223,1,223,4,223,99,226";
    }

    public static void main(String[] args) {
        // Just blabla to get the time A and B run, not interesting at all.
        long startTime = System.nanoTime();
        System.out.print("answer A: " + runA(getInput()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);

        startTime = System.nanoTime();
        System.out.print("answer B: " + runB(getInput()));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

}
