package Days;

import java.util.*;
import java.util.stream.Collectors;

import static Days.Day5_TakeTwo.ParameterMode.IMMEDIATE;
import static Days.Day5_TakeTwo.ParameterMode.POSITION;

public class Day7_AmplificationCircuit {

    public static List<int[]> permutations = new ArrayList<>();
    public static int runA(String input) {
        permute(new int[]{0,1,2,3,4});
        Map<Integer, int[]> outputForSequence = new HashMap<>();

        permutations.stream().forEach(sequence -> runAmplifiersWithSequence(sequence, input, outputForSequence));
        return outputForSequence.keySet().stream().mapToInt(value -> value).max().getAsInt();

    }

    private static void runAmplifiersWithSequence(int[] sequence, String input, Map<Integer, int[]> outputForSequence) {
        Integer outputAmpA = runProgram(getFreshMemoryState(input), sequence[0], 0);
        Integer outputAmpB = runProgram(getFreshMemoryState(input), sequence[1], outputAmpA);
        Integer outputAmpC = runProgram(getFreshMemoryState(input), sequence[2], outputAmpB);
        Integer outputAmpD = runProgram(getFreshMemoryState(input), sequence[3], outputAmpC);
        Integer outputAmpE = runProgram(getFreshMemoryState(input), sequence[4], outputAmpD);
        outputForSequence.put(outputAmpE, sequence);
    }

    private static int runAmplifiersWithSequenceForB(int[] sequence, String input, Map<Integer, int[]> outputForSequence, int firstInputValue) {
        Integer outputAmpA = runProgram(getFreshMemoryState(input), sequence[0], firstInputValue);
        Integer outputAmpB = runProgram(getFreshMemoryState(input), sequence[1], outputAmpA);
        Integer outputAmpC = runProgram(getFreshMemoryState(input), sequence[2], outputAmpB);
        Integer outputAmpD = runProgram(getFreshMemoryState(input), sequence[3], outputAmpC);
        Integer outputAmpE = runProgram(getFreshMemoryState(input), sequence[4], outputAmpD);
        outputForSequence.put(outputAmpE, sequence);
        return outputAmpE;
    }


    public static int runB(String input) {
        permute(new int[]{5, 6, 7, 8, 9});
        Map<Integer, int[]> outputForSequence = new HashMap<>();
        int firstInputValue = 0;
        for(int[] sequence : permutations) {
            firstInputValue = runAmplifiersWithSequenceForB(sequence, input, outputForSequence, firstInputValue);
        }

        permutations.stream().forEach(sequence -> runAmplifiersWithSequence(sequence, input, outputForSequence));
        return outputForSequence.keySet().stream().mapToInt(value -> value).max().getAsInt();

    }

    public static List<Integer> getFreshMemoryState(String input) {
        return Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }


    public static List<int[]> permute(int[] arr){
        permuteHelper(arr, 0);
        return permutations;
    }

    private static void permuteHelper(int[] arr, int index){
        if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
            //System.out.println(Arrays.toString(arr));
            int[] permutation = new int[5];
            for(int i = 0; i < arr.length; i++){
                permutation[i] = arr[i];
            }
            permutations.add(permutation);
            return;
        }

        for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            permuteHelper(arr, index+1);

            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
    }

    public static Integer runProgram(List<Integer> integerList, int inputValue1, int inputValue2) {
        boolean usedInput1 = false;
        Integer output = -1;
        for(int pointer = 0; pointer <  integerList.size();) {
            Integer instruction = integerList.get(pointer);

            // Get opcode and modes for instruction 1 and 2
            int opcode = instruction % 100;
            Day5_TakeTwo.ParameterMode modeInstruction1 = instruction / 100 % 10  == 1 ? IMMEDIATE : POSITION ;
            Day5_TakeTwo.ParameterMode modeInstruction2 = instruction / 1000 % 10  == 1 ? IMMEDIATE : POSITION ;

            if(opcode == 1) { // add instruction
                integerList.set(integerList.get(pointer + 3) , getValueOfParam(integerList, pointer, 1, modeInstruction1) + getValueOfParam(integerList, pointer, 2, modeInstruction2));
                pointer += 4;

            } else if(opcode == 2) { // multiply instruction
                integerList.set(integerList.get(pointer + 3), getValueOfParam(integerList, pointer, 1, modeInstruction1) * getValueOfParam(integerList, pointer, 2, modeInstruction2));
                pointer += 4;

            } else if(opcode == 3) { // input instruction
                if(!usedInput1) {
                    integerList.set(integerList.get(pointer + 1), inputValue1);
                    usedInput1 = true;
                } else {
                    integerList.set(integerList.get(pointer + 1), inputValue2);
                }
                pointer += 2;

            } else if (opcode == 4) { // output instruction
                output = getValueOfParam(integerList, pointer, 1, modeInstruction1);
                // Output all test codes, last one is diagnostic code.
//                System.out.println("value at address: " + (pointer + 1) + " is: " + getValueOfParam(integerList, pointer, 1, modeInstruction1));
//                        integerList.get(pointer + 1));
                pointer += 2;

            } else if(opcode == 5 ) { // jump-if-true
                if(getValueOfParam(integerList, pointer, 1, modeInstruction1) != 0) {
                    pointer = getValueOfParam(integerList, pointer, 2, modeInstruction2);
                } else {
                    pointer += 3;
                }
                System.out.println("Jump if true: Changed pointer to: " + pointer);

            } else if(opcode == 6) { // jump if false
                if (getValueOfParam(integerList, pointer, 1, modeInstruction1) == 0) {
                    pointer = getValueOfParam(integerList, pointer, 2, modeInstruction2);
                } else {
                    pointer += 3;
                }
                System.out.println("Jump if false: Changed pointer to: " + pointer);

            } else if(opcode == 7) {
                boolean lessThan = getValueOfParam(integerList, pointer, 1, modeInstruction1) < getValueOfParam(integerList, pointer, 2, modeInstruction2);
                integerList.set(integerList.get(pointer + 3), lessThan ? 1 : 0);
                pointer += 4;
                System.out.println("Less Than: Setting index: " + integerList.get(pointer + 3) + " to: " + (lessThan ? 1 : 0));

            } else if (opcode == 8) {
                boolean equals = getValueOfParam(integerList, pointer, 1, modeInstruction1).equals(getValueOfParam(integerList, pointer, 2, modeInstruction2));
                integerList.set(integerList.get(pointer + 3), equals ? 1 : 0);
                pointer += 4;
                System.out.println("Equals: Setting index: " + integerList.get(pointer + 3) + " to: " + (equals ? 1 : 0));

            } else if (opcode == 99) {
                return output;
            } else {
                throw new IllegalStateException("Unsupported opcode");
            }
        }
        throw new IllegalStateException("Finished running without finding opcode 99");
    }

    enum ParameterMode {
        IMMEDIATE, POSITION
    }

    public static Integer getValueOfParam(List<Integer> integerList, int startingIndex, int i, Day5_TakeTwo.ParameterMode paramMode) {
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


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("answer A: " + runA(getInput()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);


        startTime = System.nanoTime();
//        System.out.println("answer B: " + runB(getInput()));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

    public static String getInput() {
        return "3,8,1001,8,10,8,105,1,0,0,21,34,55,68,93,106,187,268,349,430,99999,3,9,102,5,9,9,1001,9,2,9,4,9,99,3,9,1001,9,5,9,102,2,9,9,101,2,9,9,102,2,9,9,4,9,99,3,9,101,2,9,9,102,4,9,9,4,9,99,3,9,101,4,9,9,102,3,9,9,1001,9,2,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,1002,9,5,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,99";
    }
}
