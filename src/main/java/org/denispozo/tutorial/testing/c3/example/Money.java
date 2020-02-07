package org.denispozo.tutorial.testing.c3.example;

public class Money {
    private final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (currency == null || currency.equals("")) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean equals(Object anObject) {
        if(!(anObject instanceof Money)) {
            return false;
        }

        Money money = (Money) anObject;
        return money.getCurrency().equals(getCurrency())
            && getAmount() == money.getAmount();
    }
}
