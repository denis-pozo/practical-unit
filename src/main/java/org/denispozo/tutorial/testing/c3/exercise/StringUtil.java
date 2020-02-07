package org.denispozo.tutorial.testing.c3.exercise;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static String reverse (String s) {
        List<String> tempArray;

        try {
            tempArray = new ArrayList<>(s.length());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("String cannot be null");
        }

        for(int i = 0 ; i < s.length() ; i++) {
            tempArray.add(s.substring(i,i+1));
        }
        StringBuilder reversedString = new StringBuilder(s.length());
        for(int i = tempArray.size() - 1 ; i >= 0 ; i--) {
            reversedString.append(tempArray.get(i));
        }

        return reversedString.toString();
    }
}
