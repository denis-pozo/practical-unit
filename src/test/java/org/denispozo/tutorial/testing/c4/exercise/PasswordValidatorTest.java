package org.denispozo.tutorial.testing.c4.exercise;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PasswordValidatorTest {

    public Object[] getValidPassword() {
        return $("9qJFzPc%",
                 ");R%D8Cy#d5",
                 "CESH@dQB3@",
                 "dw4P9J!TAM3Ep",
                 "J@W@z4kSsAVy$#",
                 "6za$MQMMWU&aNV*9FnJT");
    }

    @Test
    @Parameters(method = "getValidPassword")
    public void should_beValid_if_passwordCompliantWithRules(String password) {
        assertTrue(PasswordValidator.isValid(password));
    }

    @Test
    public void should_notBeValid_if_tooLongPassword() {
        String tooLong = "66za$MQMMWU&aNV*9FnJT";
        assertFalse(PasswordValidator.isValid(tooLong));

    }

    @Test
    public void should_notBeValid_if_tooShortPassword() {
        String tooShort = "66za$MQ";
        assertFalse(PasswordValidator.isValid(tooShort));
    }

    @Test
    @Parameters(method = "getValidPassword")
    public void should_notBeValid_if_passwordWithNoUpperCase(
        String password) {
        String invalid = password.toLowerCase();
        assertFalse(PasswordValidator.isValid(invalid));
    }

    @Test
    @Parameters(method = "getValidPassword")
    public void should_notBeValid_if_passwordWithNoLowerCase(
        String password) {
        String invalid = password.toUpperCase();
        assertFalse(PasswordValidator.isValid(invalid));
    }

    @Test
    public void should_notBeValid_if_passwordWithNoNumber() {
        String invalid = "NNnn>!NNNN";
        assertFalse(PasswordValidator.isValid(invalid));
    }

    @Test
    public void should_notBeValid_if_passwordWithNoSpecialChar() {
        String invalid = "NNnn32NNNN";
        assertFalse(PasswordValidator.isValid(invalid));
    }
}
