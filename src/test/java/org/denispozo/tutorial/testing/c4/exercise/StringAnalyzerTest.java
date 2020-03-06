package org.denispozo.tutorial.testing.c4.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringAnalyzerTest {


    @Test
    public void should_returnListWith1Group_if_stringHasOneGroupOf3Digits() {
        String input = "cdefg 345 12bb23";
        assertEquals(input + " has '345' and getNumberList(input) should return a "
                     + "list with one element '345'",
                     "345", StringAnalyzer.getNumberList(input).get(0));
        assertEquals(1, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnListWith1Group_if_stringHasOneGroupOf4Digits() {
        String input = "cdefg 2345 12bb23";
        assertEquals(input + " has '2345' and getNumberList(input) should return a list "
                     + "with one element '2345'",
                     "2345", StringAnalyzer.getNumberList(input).get(0));
        assertEquals(1, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnEmpty_if_stringWithNoDigits() {
        String input = "asldkgawiejf";
        assertEquals(input + " has no digits, returned list must be empty",
                     0, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnEmpty_if_stringHasOneDigit() {
        String input = "asldk1awiejf";
        assertEquals(input + " has 1 digit, returned list must be empty",
                     0, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnEmpty_if_stringHasTwoDigits() {
        String input = "abc 12";
        assertEquals(input + " has 2 digits, returned list must be empty",
                     0, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnEmpty_if_stringHasThreeDigitsSeparatedByWhitespace() {
        String input = "asldk12 3awiejf";
        assertEquals(input + " has 3 digits but not correlative, returned list must be empty",
                     0, StringAnalyzer.getNumberList(input).size());
    }

    @Test
    public void should_returnListOfSize2_if_stringHas2GroupsOfThreeDigits() {
        String input = "cdefg 345 12bb33 678tt";
        assertEquals(input + " has 2 groups of 3 digit, size of returned list must be 2",
                     2, StringAnalyzer.getNumberList(input).size());
    }

}
