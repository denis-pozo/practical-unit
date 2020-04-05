package org.denispozo.tutorial.testing.c5.exercise.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class BookingServiceTest {

    private BookingService bookingService = new BookingService();
    private Room arielle = mock(Room.class, "arielle");
    private Room jasmine = mock(Room.class, "jasmine");
    private Room pocahontas = mock(Room.class, "pocahontas");
    private Room aurora = mock(Room.class, "aurora");
    private final int NUMBER_OF_ROOMS = 4;
    private final LocalDateTime bookingTime = LocalDateTime.of(2020, 03, 25, 15, 00);

    @Before
    public void initializeRooms() {
        bookingService.addRoom(arielle);
        when(arielle.getName()).thenReturn("Arielle");
        bookingService.addRoom(jasmine);
        when(jasmine.getName()).thenReturn("Jasmine");
        bookingService.addRoom(pocahontas);
        when(pocahontas.getName()).thenReturn("Pocahontas");
        bookingService.addRoom(aurora);
        when(aurora.getName()).thenReturn("Aurora");
    }

    @Test
    public void repeatedRooms_shouldBeAddedOnlyOnce() {
        BookingService bookings = new BookingService();
        bookings.addRoom(arielle);
        bookings.addRoom(arielle);

        assertEquals(1, bookings.getRooms().size());
    }

    @Test
    public void allRooms_shouldBeListed() {
        assertEquals(NUMBER_OF_ROOMS, bookingService.getRooms().size());
    }

    @Test
    public void allRooms_shouldBeAvailable_ifNoBookings() {
        assertEquals(4, bookingService.listAvailableRooms(bookingTime).size());
    }

    @Test
    public void unknownRoom_shouldNotBeBookable() {
        Room a1 = mock(Room.class, "a1");
        when(a1.getName()).thenReturn("A1");
        assertFalse(
            a1.getName() + " has been booked",
            bookingService.book("a1", bookingTime)
        );
    }

    @Test
    public void availableRoom_shouldBeBookable() {
        assertTrue(
            aurora.getName() + " is not available",
            bookingService.book("Aurora", bookingTime)
        );
    }

    @Test
    public void availableRoom_shouldBeBookableWithOthersUnavailable() {
        bookingService.book("Aurora", bookingTime);
        bookingService.book("Pocahontas", bookingTime);
        bookingService.book("Arielle", bookingTime);
        assertTrue(
            jasmine.getName() + " couldn't be booked",
            bookingService.book("Jasmine", bookingTime)
        );
    }

    @Test
    public void unavailableRoom_shouldNotBeBookable() {
        bookingService.book("Aurora", bookingTime);
        assertFalse(
            aurora.getName() + " is available",
            bookingService.book("Aurora", bookingTime)
        );
    }

    @Test
    public void availableRoomWithProjector_shouldBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        assertTrue("Room with projector wasn't booked",
                    bookingService.book(Equipment.PROJECTOR, bookingTime));
    }

    @Test
    public void unavailableRoomWithProjector_shouldNotBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        bookingService.book(Equipment.PROJECTOR, bookingTime);
        assertFalse("Room with projector was booked",
                     bookingService.book(Equipment.PROJECTOR, bookingTime));
    }

    @Test
    public void availableRoomWithMicrophone_shouldBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        assertTrue("Room with microphone wasn't booked",
            bookingService.book(Equipment.MICROPHONE, bookingTime));
    }

    @Test
    public void unavailableRoomWithMicrophone_shouldNotBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        bookingService.book(Equipment.MICROPHONE, bookingTime);
        bookingService.book(Equipment.MICROPHONE, bookingTime);
        assertFalse("Room with MICROPHONE was booked",
            bookingService.book(Equipment.MICROPHONE, bookingTime));
    }

    @Test
    public void availableRoomWithWhiteboard_shouldBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        assertTrue("Room with whiteboard wasn't booked",
            bookingService.book(Equipment.WHITEBOARD, bookingTime));
    }

    @Test
    public void unavailableRoomWithWhiteboard_shouldNotBeBookable() {
        when(aurora.getEquipment()).thenReturn(Equipment.PROJECTOR);
        when(jasmine.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(arielle.getEquipment()).thenReturn(Equipment.MICROPHONE);
        when(pocahontas.getEquipment()).thenReturn(Equipment.WHITEBOARD);
        bookingService.book(Equipment.WHITEBOARD, bookingTime);
        assertFalse("Room with whiteboard was booked",
            bookingService.book(Equipment.WHITEBOARD, bookingTime));
    }
}
