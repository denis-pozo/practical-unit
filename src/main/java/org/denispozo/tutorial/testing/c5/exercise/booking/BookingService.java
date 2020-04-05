package org.denispozo.tutorial.testing.c5.exercise.booking;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Slf4j
public class BookingService {

    final private Collection<Room> rooms = new HashSet<>();
    final private Map<LocalDateTime, ArrayList<Room>> bookedRooms = new HashMap<>();

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        if(rooms.add(room)) {
            log.info("Room " + room + " successfully added to the list of rooms");
        } else {
            log.error("Room " + room + " couldn't be added to the list of rooms");
        }
    }

    public Collection<Room> listAvailableRooms(LocalDateTime bookingTime) {
        Collection<Room> available = new ArrayList<>();

        if(!bookedRooms.containsKey(bookingTime)) {
            return rooms;
        }

        Collection<Room> booked = bookedRooms.get(bookingTime);
        for(Room room : rooms) {
            if(!booked.contains(room)) {
                available.add(room);
            }
        }

        return available;
    }


    public boolean book(String name, LocalDateTime bookingTime) {
        Room room = getByName(name);
        if(room == null) {
            log.error("Booking failed: Room " + name + " doesn't exist in our system");
            return false;
        }

        return book(room, bookingTime);
    }

    public boolean book(Equipment equipment, LocalDateTime bookingTime) {
        for(Room room : rooms) {
            if(room.getEquipment().equals(equipment)){
                if(book(room, bookingTime)){
                    return true;
                }
            }
        }
        return false;
    }

    private Room getByName(String roomName) {
        for(Room room : rooms) {
            if(room.getName().equals(roomName)) {
                return room;
            }
        }

        return null;
    }

    private boolean book(Room room, LocalDateTime bookingTime) {
        if(!bookedRooms.containsKey(bookingTime)) {
            bookedRooms.put(bookingTime, new ArrayList<>(Arrays.asList(room)));
            log.info("Room " + room.getName() + " successfully booked on " + bookingTime.toString());
            return true;
        }

        if(bookedRooms.get(bookingTime).contains(room)) {
            log.error("Booking failed: Room " + room.getName() + " is not available");
            return false;
        } else {
            log.info("Room " + room.getName() + " successfully booked on " + bookingTime
                .toString());
            bookedRooms.get(bookingTime).add(room);
            return true;
        }
    }
}
