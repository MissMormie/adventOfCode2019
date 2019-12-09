package Days;

import helpers.intCodeProgram.IntCodeProgram;

import java.util.*;

import static Days.Day7_AmplificationCircuit.getFreshMemoryState;

public class Day7_EngineeredAmplification {
    public static int firstInput = 0;

    public static int runB(String input) {

        List<int[]> permutations = Day7_AmplificationCircuit.permute(new int[]{9, 8, 7, 6, 5});

        Map<Integer, int[]> outputForSequence = new HashMap<>();

        permutations.stream().forEach(sequence -> {
            outputForSequence.put(runProgramForSequence(sequence, input), sequence);
        });
        return outputForSequence.keySet().stream().mapToInt(value -> value).max().getAsInt();
    }

    public static int runProgramForSequence(int[] sequence, String input) {
        List<IntCodeProgram> amplifiers = getProgramsForSequence(sequence, input);

        boolean finished = false;
        while(!finished) {
            boolean allProgramsFinished = true;
            for(IntCodeProgram amplifier : amplifiers) {
                amplifier.runProgramUntilWaitingForInput();
                allProgramsFinished = allProgramsFinished && amplifier.isFinished(); // stops if all amplifiers are finished.
            }
            finished = allProgramsFinished;
        }
        return amplifiers.get(4).lastOutput;
    }

    public static List<IntCodeProgram> getProgramsForSequence(int[] sequence, String input) {
        // Create state of amplifyers for current sequence.
        IntCodeProgram amplifyerA = new IntCodeProgram(getFreshMemoryState(input));
        amplifyerA.addInput(sequence[0]);
        amplifyerA.addInput(firstInput);
        IntCodeProgram amplifyerB = new IntCodeProgram(getFreshMemoryState(input));
        amplifyerB.addInput(sequence[1]);
        IntCodeProgram amplifyerC = new IntCodeProgram(getFreshMemoryState(input));
        amplifyerC.addInput(sequence[2]);
        IntCodeProgram amplifyerD = new IntCodeProgram(getFreshMemoryState(input));
        amplifyerD.addInput(sequence[3]);
        IntCodeProgram amplifyerE = new IntCodeProgram(getFreshMemoryState(input));
        amplifyerE.addInput(sequence[4]);

        amplifyerA.setHandOutputTo(amplifyerB);
        amplifyerB.setHandOutputTo(amplifyerC);
        amplifyerC.setHandOutputTo(amplifyerD);
        amplifyerD.setHandOutputTo(amplifyerE);
        amplifyerE.setHandOutputTo(amplifyerA);

        ArrayList<IntCodeProgram> amplifiers = new ArrayList<>();
        amplifiers.add(amplifyerA);
        amplifiers.add(amplifyerB);
        amplifiers.add(amplifyerC);
        amplifiers.add(amplifyerD);
        amplifiers.add(amplifyerE);
        return amplifiers;
    }


    public static int runA(String input) {
        List<int[]> permutations = Day7_AmplificationCircuit.permute(new int[]{0, 1, 2, 3, 4});
        Map<Integer, int[]> outputForSequence = new HashMap<>();

        permutations.stream().forEach(sequence ->runAmplifiersWithSequence(sequence, outputForSequence, 0, input));
        return outputForSequence.keySet().stream().mapToInt(value -> value).max().getAsInt();
    }

    private static void runAmplifiersWithSequence(int[] sequence, Map<Integer, int[]> outputForSequence, int firstInputValue, String input) {
        int outputAmpA = runIntCodeProgram(getFreshMemoryState(input), sequence[0], firstInputValue);
        int outputAmpB = runIntCodeProgram(getFreshMemoryState(input), sequence[1], outputAmpA);
        int outputAmpC = runIntCodeProgram(getFreshMemoryState(input), sequence[2], outputAmpB);
        int outputAmpD = runIntCodeProgram(getFreshMemoryState(input), sequence[3], outputAmpC);
        int outputAmpE = runIntCodeProgram(getFreshMemoryState(input), sequence[4], outputAmpD);
        outputForSequence.put(outputAmpE, sequence);

    }


    public static int runIntCodeProgram(List<Integer> memoryState, int... inputs) {
        IntCodeProgram intCodeProgram = new IntCodeProgram(memoryState);
        Arrays.stream(inputs).forEach(input -> intCodeProgram.addInput(input));

        return intCodeProgram.runProgram();
    }



    public static void main(String[] args) {
        long startTime = System.nanoTime();
//        System.out.println("answer A: " + runA(getInput()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
//        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);

        startTime = System.nanoTime();
        System.out.println("answer B: " + runB(getInput()));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

    public static String getInput() {
        return "3,8,1001,8,10,8,105,1,0,0,21,34,55,68,93,106,187,268,349,430,99999,3,9,102,5,9,9,1001,9,2,9,4,9,99,3,9,1001,9,5,9,102,2,9,9,101,2,9,9,102,2,9,9,4,9,99,3,9,101,2,9,9,102,4,9,9,4,9,99,3,9,101,4,9,9,102,3,9,9,1001,9,2,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,101,2,9,9,1002,9,5,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,99";
    }
}
