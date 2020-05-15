package org.denispozo.tutorial.testing.c6.example;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PhoneTest {

    Phone phone = new Phone();

    @Test
    public void shouldThrowIAEForEmptyNumber() {
        catchException(phone).setNumber(null);

        assertTrue(caughtException() instanceof IllegalArgumentException);
        assertEquals("'Number' cannot be null or empty",
                     caughtException().getMessage());

        // Old fashion:
        // try {
        //     phone.setNumber(null);
        //     fail("Should have thrown IllegalArgumentException");
        // } catch(IllegalArgumentException iae) {
        //     assertEquals("'Number' cannot be null or empty", iae.getMessage());
        // }
    }

    @Test
    public void shouldThrowIAEForPlusPrefixedNumber() {
        catchException(phone).setNumber("+123");

        assertTrue(caughtException() instanceof IllegalArgumentException);
        assertEquals("Plus sign prefix not allowed, number: [+123]",
                     caughtException().getMessage());
        // Old-fashion
        // try {
        //     phone.setNumber("+123");
        //     fail("Should have thrown IllegalArgumentException");
        // } catch (IllegalArgumentException iae) {
        //     assertEquals("Plus sign prefix not allowed, number: [+123]", iae.getMessage());
        // }
    }
}
