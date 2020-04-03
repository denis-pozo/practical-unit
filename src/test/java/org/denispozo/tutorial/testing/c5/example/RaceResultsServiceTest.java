package org.denispozo.tutorial.testing.c5.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class RaceResultsServiceTest {

    private RaceResults raceResults = mock(RaceResults.class);
    private RacesClient clientA = mock(RacesClient.class, "clientA");
    private RacesClient clientB = mock(RacesClient.class, "clientB");

    private RaceResultsService raceResultsService = new RaceResultsService();

    @Test
    public void unknownSubscribedClients_shouldNotReceiveRaceResults() {
        raceResultsService.sendResults(raceResults);

        verify(clientA, never()).receive(raceResults);
        verify(clientB, never()).receive(raceResults);
    }

    @Test
    public void subscribedClient_shouldReceiveRaceResults() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.sendResults(raceResults);

        verify(clientA).receive(raceResults);
    }

    @Test
    public void allSubscribers_shouldReceiveRaceResults() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientB);
        raceResultsService.sendResults(raceResults);

        verify(clientA).receive(raceResults);
        verify(clientB).receive(raceResults);
    }

    @Test
    public void clientSubscribedMultipleTimes_shouldReceiveRaceResultsOnce() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientA);
        raceResultsService.sendResults(raceResults);

        verify(clientA, times(1)).receive(raceResults);
    }

    @Test
    public void unsubscribedClient_shouldNotReceiveRaceResults() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.removeSubscriber(clientA);
        raceResultsService.removeSubscriber(clientA);
        raceResultsService.sendResults(raceResults);
        verify(clientA, never()).receive(raceResults);
    }
}
