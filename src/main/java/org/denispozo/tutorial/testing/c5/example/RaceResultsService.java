package org.denispozo.tutorial.testing.c5.example;

import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {

    Collection<RacesClient> subscriberList = new HashSet<>();

    public void addSubscriber(RacesClient client) {
        subscriberList.add(client);
    }

    public void sendResults(RaceResults results) {
        for(RacesClient client : subscriberList) {
            client.receive(results);
        }
    }

    public void removeSubscriber(RacesClient client) {
        subscriberList.remove(client);
    }


    /*
     * Requirements:
     * Allow clients to subscribe
     * Allow clients to unsubscribe
     * Everytime a new message comes, it should be sent to all subscribers
     */
}
