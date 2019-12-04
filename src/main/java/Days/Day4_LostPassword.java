package Days;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4_LostPassword {
    public static void main(String[] args) {
        System.out.println("answer A: " + runA(getInput()));
        System.out.println("answer B: " + runB(getInput()));
    }

    public static int runA(String input) {
        // 145852-616942
        List<String> collect = IntStream.rangeClosed(145852, 616942).mapToObj(i -> numberToStringWithXPositions(i))
                .filter(i -> containsTwoConsecutiveDuplicateNumbers(i) && allNumbersIncrease(i))
                .collect(Collectors.toList());

        return collect.size();

    }

    public static String numberToStringWithXPositions(int i) {
        String s = String.valueOf(i);
        while(s.length() < 6) {
            s += "0";
        }
        return s;
    }

    public static boolean containsTwoConsecutiveDuplicateNumbers(String i) {
        char[] chars = i.toCharArray();
                return chars[0] == chars[1]
                || chars[1] == chars[2]
                || chars[2] == chars[3]
                || chars[3] == chars[4]
                || chars[4] == chars[5];
    }

    public static boolean containsTwoNotThreeConsecutiveDuplicateNumbers(String i) {
        char[] chars = i.toCharArray();
        return (chars[0] == chars[1] && chars[1] != chars[2])
                || (chars[1] == chars[2] && chars[2] != chars[3] && chars[0] != chars[1])
                || (chars[2] == chars[3] && chars[3] != chars[4] && chars[1] != chars[2])
                || (chars[3] == chars[4] && chars[4] != chars[5] && chars[2] != chars[3])
                || (chars[4] == chars[5] && chars[3] != chars[4]);
    }

    public static boolean allTheSameChar(char c1, char c2, char c3) {
        return c1 == c2 && c2 == c3;
    }



    public static boolean allNumbersIncrease(String i) {
        char[] chars = i.toCharArray();
        return chars[0] <= chars[1] &&
                chars[1] <= chars[2] &&
                chars[2] <= chars[3] &&
                chars[3] <= chars[4] &&
                chars[4] <= chars[5];
    }


    public static boolean containsValidRunBNumber(String i) {
        return allNumbersIncrease(i)
                && containsTwoNotThreeConsecutiveDuplicateNumbers(i);

    }

    public static int runB(String input) {
        List<String> collect = IntStream.rangeClosed(145852, 616942).mapToObj(i -> numberToStringWithXPositions(i))
                .filter(i -> containsValidRunBNumber(i))
                .collect(Collectors.toList());

        return collect.size();
    }

    public static String getInput() {
        return "";
    }
}
