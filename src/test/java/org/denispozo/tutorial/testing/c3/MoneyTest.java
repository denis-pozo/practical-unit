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

    private static final Object[] getMoney() {
        return $(
            $(10, "USD"),
            $(20, "EUR")
        );
    }

    @Test
    public void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(10, "USD");

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
        int amount = 10;
        String currency = "USD";

        Money moneyA = new Money(amount, currency);
        Money moneyB = new Money(amount, currency);

        assertTrue(moneyA.equals(moneyB));
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeAmountShouldThrowIllegalArgumentException() {
        int amount = -4;
        String currency = "USD";

        Money money = new Money(amount, currency);
        System.out.println("No Exception Thrown - Money: " + money);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCurrencyShouldThrowIllegalArgumentException() {
        int amount = 4;

        Money money = new Money(amount, null);
        System.out.println("No Exception Thrown - Money: " + money);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyCurrencyShouldThrowIllegalArgumentException() {
        int amount = 4;
        String empty = "";

        Money money = new Money(amount, empty);
        System.out.println("No Exception Thrown - Money: " + money);
    }
}
