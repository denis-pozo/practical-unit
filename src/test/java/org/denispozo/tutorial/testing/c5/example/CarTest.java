package org.denispozo.tutorial.testing.c5.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.denispozo.tutorial.testing.c5.example.Car;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class CarTest {

    private Car testCar = mock(Car.class);

    @Test
    public void mockedCar_shouldBeACar() {
        assertTrue(testCar instanceof Car);
    }

    @Test
    public void mockedCar_shouldReturnFalseByDefault() {
        assertFalse("Test stub should return false by default", testCar.needsFuel());
        when(testCar.needsFuel()).thenReturn(true);
        assertTrue("Test stub should return true after setting it up",
                    testCar.needsFuel());
    }

    @Test
    public void mockedCar_shouldReturnZeroByDefault() {
        assertEquals("Test stub should return 0.0 by default",
                     0.0, testCar.getEngineTemperature(), 0.0);
    }

    @Test(expected = RuntimeException.class)
    public void mockedCar_shouldThrowAnException() {
        when(testCar.needsFuel()).thenThrow(new RuntimeException());
        testCar.needsFuel();
    }

    @Test
    public void mockedCar_shouldReallyCallCarMethods() {
        testCar.driveTo("Sweet home Alabama");
        testCar.needsFuel();
        testCar.getEngineTemperature();

        verify(testCar).driveTo("Sweet home Alabama");
        verify(testCar).needsFuel();
        verify(testCar).getEngineTemperature();
    }

}
