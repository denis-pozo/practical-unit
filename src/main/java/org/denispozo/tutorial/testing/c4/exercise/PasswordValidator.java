package org.denispozo.tutorial.testing.c4.exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static final int MAX_LENGTH = 20;
    public static final int MIN_LENGTH = 8;

    public static boolean isValid(String password) {
        if(password.length() > MAX_LENGTH
            || password.length() < MIN_LENGTH) return false;

        Pattern lowerCase = Pattern.compile("[a-z]");
        Matcher hasLowerCase = lowerCase.matcher(password);
        if(!hasLowerCase.find()) return false;

        Pattern upperCase = Pattern.compile("[A-Z]");
        Matcher hasUpperCase = upperCase.matcher(password);
        if(!hasUpperCase.find()) return false;

        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(password);
        if(!hasDigit.find()) return false;

        Pattern special = Pattern.compile(
            "[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        if(!hasSpecial.find()) return false;

        return true;
    }
}
