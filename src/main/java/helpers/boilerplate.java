package helpers;

public class boilerplate {

    public static void main(String[] args) {
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

    public static int runA(String input) {


        // DON'T FORGET TO ADD THE ANSWER
        return 1;
    }


    public static int runB(String input) {


        // DON'T FORGET TO ADD THE ANSWER
        return 1;
    }

    public static String getInput() {
        return "";
    }
}
