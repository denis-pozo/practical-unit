package org.denispozo.tutorial.testing.c3.exercise;

import static org.junit.Assert.assertEquals;
import static junitparams.JUnitParamsRunner.$;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringUtilTest {

    private static final Object[] getValidString() {
        return $(
            $("Car", "raC"),
            $("Pencil", "licneP"),
            $("destroy", "yortsed"),
            $("", ""),
            $("STRING", "GNIRTS")
        );
    }

    @Test
    @Parameters(method = "getValidString")
    public void reverseShouldReturnReversedStringsForValidInputs(
        String actual, String expectedReversed) {
        String reverseString = StringUtil.reverse(actual);

        assertEquals(expectedReversed, reverseString);
    }
}
