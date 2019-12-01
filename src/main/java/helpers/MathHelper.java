package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathHelper {
    public static boolean isEvenNumber(int num) {
        return num % 2 == 0;
    }

    public static List<Integer> findAllNumbersInString(String input) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        List<Integer> list = new ArrayList<>();
        while(m.find()) {
            list.add(Integer.parseInt(m.group()));
        }
        return list;
    }
}
