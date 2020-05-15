package org.denispozo.tutorial.testing.c6.example;

public class Phone {
    private String number;

    public Phone() {
    }

    public void setNumber(String number) {
        if(null == number || number.isEmpty()) {
            throw new IllegalArgumentException("'Number' cannot be null or empty");
        }

        if (number.startsWith("+")) {
            throw new IllegalArgumentException("Plus sign prefix not allowed, number: ["
                                                + number + "]");
        }

        this.number = number;
    }
}
