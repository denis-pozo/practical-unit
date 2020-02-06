package org.denispozo.tutorial.testing.c3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MoneyTest {

    @Test
    public void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(10, "USD");

        assertEquals(10, money.getAmount());
        assertEquals("USD", money.getCurrency());
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
