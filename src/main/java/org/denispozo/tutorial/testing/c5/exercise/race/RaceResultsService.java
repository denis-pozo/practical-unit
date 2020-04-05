package org.denispozo.tutorial.testing.c5.exercise.race;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class RaceResultsService {

    Map<RaceCategory, Set<RacesClient>> subscribers = new HashMap<>();

    public RaceResultsService() {
        for(RaceCategory category : RaceCategory.values()) {
            subscribers.put(category, new HashSet<>());
        }
    }

    public void addSubscriber(RacesClient client) {
        for(RaceCategory category : subscribers.keySet()) {
            subscribers.get(category).add(client);
        }
    }

    public void removeSubscriber(RacesClient client) {
        boolean removed = false;
        for(RaceCategory category : RaceCategory.values()) {
            if(subscribers.get(category).remove(client)){
                removed = true;
            }
        }

        if (!removed) throw new IllegalArgumentException("Unknown Subscriber");
    }

    public void addSubscription(RacesClient client, RaceCategory category) {
        subscribers.get(category).add(client);
    }

    public void removeSubscription(RacesClient client, RaceCategory category) {
        subscribers.get(category).remove(client);
    }

    public void sendResults(RaceResults results) {
        Collection<RacesClient> clients = subscribers.get(results.getCategory());
        log.info(results.getMessage());
        for(RacesClient client : clients) {
            client.receive(results);
        }
    }



    /*
     * Requirements:
     * Allow clients to subscribe
     * Allow clients to unsubscribe
     * Everytime a new message comes, it should be sent to all subscribers
     */
}
