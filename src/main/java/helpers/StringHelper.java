package helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {
    public static String sortCharsInStringAlphabetically(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static List<Integer> getListOfNumbersSeperatedBy(String input, String regex) {
        String[] split = input.split(input);
        return Arrays.stream(input.split(regex)).map(Integer::parseInt).collect(Collectors.toList());
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
        numbers.stream().forEach(num -> linkedList.addObject(num));
        return linkedList;
    }

}
