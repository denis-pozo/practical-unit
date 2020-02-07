package org.denispozo.tutorial.testing.c3.example;

import java.util.ArrayList;
import java.util.Collection;

public class Client {

    private final Collection<Object> addresses = new ArrayList<>();

    public Collection<Object> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
}
