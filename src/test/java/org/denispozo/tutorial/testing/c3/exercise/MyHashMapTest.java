package org.denispozo.tutorial.testing.c3.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyHashMapTest {

    private Map<String, Integer> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    @Test
    public void getShouldRetrieveAnExistingObject() {
        String number = "One";
        Integer symbol = 1;
        map.put(number, symbol);

        assertEquals(symbol, map.get(number));
    }

    @Test
    public void addingSecondObjectSameKeyShouldReplaceExistingOne() {
        String number = "One";
        Integer wrongSymbol = 4;
        Integer rightSymbol = 1;

        map.put(number, wrongSymbol);
        assertEquals(1, map.size());
        assertEquals(wrongSymbol, map.get(number));

        map.put(number, rightSymbol);
        assertEquals(1, map.size());
        assertEquals(rightSymbol, map.get(number));
    }

    @Test
    public void clearShouldRemoveAllContent() {
        String number = "One";
        Integer symbol = 1;
        map.put(number, symbol);
        assertEquals(1, map.size());

        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void nullShouldBeAPossibleKey() {
        Integer expected = 0;

        map.put(null, 0);
        Integer symbol = map.get(null);

        assertEquals(1, map.size());
        assertEquals(expected, symbol);
    }
}
