package org.denispozo.tutorial.testing.c5.exercise.booking;

import java.time.LocalDateTime;

public interface Room {
    String getName();
    void setCapacity();
    int getCapacity();
    void addEquipment();
    Equipment getEquipment();
}
