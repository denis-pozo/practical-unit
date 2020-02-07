package org.denispozo.tutorial.testing.c3.exercise;

public class FahrenheitCelsiusConverter {

    public static int toFahrenheit(int celsius) {
        float fahrenheit = 32 + (celsius * 9f/5);
        return Math.round(fahrenheit);
    }

    public static int toCelsius(int fahrenheit) {
        float celsius = (fahrenheit - 32f) * 5f/9;

        return Math.round(celsius);
    }
}
