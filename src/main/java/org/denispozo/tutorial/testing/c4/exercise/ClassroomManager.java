package org.denispozo.tutorial.testing.c4.exercise;

import java.util.HashSet;
import java.util.Set;

public class ClassroomManager {

    final private Set<Integer> bookedHours;

    public ClassroomManager() {
        bookedHours = new HashSet<>();
    }

    public Set<Integer> getBookedHours() {
        return bookedHours;
    }

    public boolean book(int hour) {
        if( hour > 23 || hour < 0) {
            throw new IllegalArgumentException(
                "Hour cannot be '" + hour + "', valid range [0-23]");
        }

        return bookedHours.add(hour);
    }
}
