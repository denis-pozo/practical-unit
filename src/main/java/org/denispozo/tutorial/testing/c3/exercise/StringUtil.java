package org.denispozo.tutorial.testing.c3.exercise;

public class StringUtil {

    public static String reverse (String s) {
        StringBuilder reversedString = new StringBuilder(s.length());

        for(int i = s.length() - 1 ; i >= 0 ; i--) {
            reversedString.append(s.substring(i,i+1));
        }

        return reversedString.toString();
    }
}
