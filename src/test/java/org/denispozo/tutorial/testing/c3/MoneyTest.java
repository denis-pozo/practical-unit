package org.denispozo.tutorial.testing.c3;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    public static final int VALID_AMOUNT = 10;
    public static final String VALID_CURRENCY = "USD";

    private static final Object[] getMoney() {
        return $(
            $(10, "USD"),
            $(20, "EUR")
        );
    }

    private static final Object[] getInvalidAmount() {
        return $(
            $(-1),
            $(-100)
        );
    }

    private static final Object[] getInvalidCurrency() {
        return $(
            $(null),
            $("")
        );
    }

    @Test
    public void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(VALID_AMOUNT, VALID_CURRENCY);

        assertEquals(10, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    @Parameters(method = "getMoney")
    public void constructorShouldSetAmountAndCurrencyParametrized(
        int amount, String currency) {
        Money money = new Money(amount, currency);

        assertEquals(amount, money.getAmount());
        assertEquals(currency, money.getCurrency());
    }

    @Test
    public void equalsShouldReturnTrueWithEqualObjects() {
        Money moneyA = new Money(VALID_AMOUNT, VALID_CURRENCY);
        Money moneyB = new Money(VALID_AMOUNT, VALID_CURRENCY);

        assertTrue(moneyA.equals(moneyB));
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidAmount")
    public void constructorShouldThrowIAEForNegativeAmount(int amount) {

        Money money = new Money(amount, VALID_CURRENCY);
        System.out.println("No Exception Thrown - Money: " + money);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidCurrency")
    public void constructorShouldThrowIAEForInvalidCurrency(String currency) {

        Money money = new Money(VALID_AMOUNT, currency);
        System.out.println("No Exception Thrown - Money: " + money);
    }
}
