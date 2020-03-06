package org.denispozo.tutorial.testing.c4.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {

    public static List<String> getNumberList(String input) {
        Pattern threeDigits = Pattern.compile("(\\d{3,})");
        Matcher hasThreeDigits = threeDigits.matcher(input);

        List<String> result = new ArrayList<>();
        int count = 0;
        while(hasThreeDigits.find()) {
            result.add(hasThreeDigits.group(count++));
        }
        return result;
    }
}
