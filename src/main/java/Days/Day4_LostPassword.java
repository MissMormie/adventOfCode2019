package Days;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4_LostPassword {
    public static void main(String[] args) {
        System.out.println("answer A: " + runA(getInput()));
        System.out.println("answer B: " + runB(getInput()));
    }

    public static int runA(String input) {
        List<String> collect = IntStream.rangeClosed(145852, 616942).mapToObj(String::valueOf)
                .filter(i -> containsAtLeastTwoConsecutiveDuplicateNumbers(i) && allNumbersIncrease(i))
                .collect(Collectors.toList());
        return collect.size();
    }

    public static boolean containsAtLeastTwoConsecutiveDuplicateNumbers(String i) {
        return Arrays.stream(String.valueOf(i).split(""))
                .collect(Collectors.groupingBy(string -> string, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(occurrences -> occurrences > 1L);
    }

    public static boolean containsExactlyTwoConsecutiveDuplicateNumbers(String i) {
        return Arrays.stream(String.valueOf(i).split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .containsValue(2L);
    }

    // Split all chars in string, sort them. If it's the same as the original the original was increasing.
    public static boolean allNumbersIncrease(String i) {
        return Arrays.stream(i.split(""))
                .sorted()
                .collect(Collectors.joining()).equals(i);
    }

    public static int runB(String input) {
        List<String> collect = IntStream.rangeClosed(145852, 616942).mapToObj(String::valueOf)
                .filter(i -> containsExactlyTwoConsecutiveDuplicateNumbers(i) && allNumbersIncrease(i))
                .collect(Collectors.toList());
        return collect.size();
    }

    public static String getInput() {
        return "";
    }
}
