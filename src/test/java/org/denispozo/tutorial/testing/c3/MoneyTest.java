package org.denispozo.tutorial.testing.c3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    private static final Object[] getMoney() {
        return new Object[] {
            new Object[] {10, "USD"},
            new Object[] {20, "EUR"}
        };
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

}
