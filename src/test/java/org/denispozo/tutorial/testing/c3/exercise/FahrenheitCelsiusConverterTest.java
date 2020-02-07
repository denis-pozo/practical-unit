package org.denispozo.tutorial.testing.c3.exercise;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FahrenheitCelsiusConverterTest {

    public static Object[] getCelsiusToFahrenheit() {
        return $(
            $(0, 32),
            $(100, 212)
        );
    }

    @Test
    @Parameters(method = "getCelsiusToFahrenheit")
    public void shouldConvertCelsiusToFahrenheit(
        int celsius, int fahrenheit) {

        assertEquals(fahrenheit, FahrenheitCelsiusConverter.toFahrenheit(celsius));
    }

    @Test
    public void specialCaseShouldConvertCelsiusToFahrenheit() {
        assertEquals(99, FahrenheitCelsiusConverter.toFahrenheit(37));
    }

    @Test
    @Parameters(method = "getCelsiusToFahrenheit")
    public void shouldConvertFahrenheitToCelsius(
        int celsius, int fahrenheit) {

        assertEquals(celsius, FahrenheitCelsiusConverter.toCelsius(fahrenheit));
    }

    @Test
    public void specialCaseShouldConvertFahrenheitToCelsius() {
        assertEquals(38, FahrenheitCelsiusConverter.toCelsius(100));
    }
}
