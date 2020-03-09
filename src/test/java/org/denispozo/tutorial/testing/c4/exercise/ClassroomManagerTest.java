package org.denispozo.tutorial.testing.c4.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ClassroomManagerTest {

    ClassroomManager classroom = new ClassroomManager();

    @Test
    public void should_returnEmptyBookedHours_when_creatingBookingSystem() {
        assertEquals( "After creation, there should be NO booking hours",
                      0, classroom.getBookedHours().size());
    }

    @Test
    public void should_not_bookABookedHour() {
        classroom.book(4);
        assertFalse("Classroom was already booked at " + classroom.getBookedHours(),
                     classroom.book(4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_not_allowOutOfRangeHours() {
        classroom.book(24);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_not_allowNegativeHours() {
        classroom.book(-1);
    }

    @Test
    public void should_not_bookAnyHour_whenBookingsAreFull() {
        for(int i = 0 ; i < 24 ; i++) {
            classroom.book(i);
        }

        for(int i = 0 ; i < 24 ; i++) {
            assertFalse("Schedule full, should not be able to book at " + i,
                         classroom.book(i));
        }
    }

    @Test
    public void should_returnListOfSizeOne_ifThereIsOneBooking() {
        int bookedHour = 13;
        classroom.book(bookedHour);
        assertEquals("Size of bookings after one booking should be equals one",
                     1, classroom.getBookedHours().size());
        assertTrue("Booked hour " + bookedHour + " should be included within the booked hours list",
                    classroom.getBookedHours().contains(bookedHour));
    }

    // Specs:
    // 1. Return a list of booked hours
    // 2. Not allow a particular hour to be double-booked
    // 3. Deal in a sensible manner with illegal values (provided as input parameters)
    // Constrains - The system:
    // a. has only one resource that can be booked (e.g. a classroom, a lawn mower, a restaurant
    // table or anything else that makes sense to you)
    // b. has no notion of days, it assumes reservations for today.
    // c. should only permit booking of regular whole clock-hours.
    // d. is not required to remember any additional information concerning the reservation (who
    // booked it, when etc.)
}
