package helpers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {
    public static String sortCharsInStringAlphabetically(String inputString) {
        // convert input string to char array
        char[] tempArray = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static List<Integer> getListOfNumbersSeperatedBy(String input, String regex) {
        return Arrays.stream(input.split(regex)).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<BigInteger> getListOfBigIntegerSeperatedBy(String input, String regex) {
        input = input.replace(" ", "");
        return Arrays.stream(input.split(regex)).map(stringNum -> {
            // Big ints dont like negatives too well.
            if(stringNum.indexOf('-') > -1) {
                return new BigInteger(stringNum.substring(1)).negate();
            } else {
                return new BigInteger(stringNum);
            }
        }
        ).collect(Collectors.toList());
    }

    public static String numberToStringWithXPositions(int i) {
        StringBuilder s = new StringBuilder(String.valueOf(i));
        while (s.length() < 6) {
            s.append("0");
        }
        return s.toString();
    }
    public static List<Integer> getNumbersFromStringOnePerLine(String input) {
        String[] split = input.split("\n");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Integer> getNumbersFromStringTabSeperated(String input) {
        String[] split = input.split("\t");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static CircularLinkedList<Integer> getCircularLinkedListNumbersFromStringTabSeperated(String input) {
        List<Integer> numbers = getNumbersFromStringTabSeperated(input);
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        numbers.forEach(linkedList::addObject);
        return linkedList;
    }
}
