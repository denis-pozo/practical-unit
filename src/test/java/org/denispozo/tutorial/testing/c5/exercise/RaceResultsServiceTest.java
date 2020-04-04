package org.denispozo.tutorial.testing.c5.exercise;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class RaceResultsServiceTest {

    private RaceResults raceResults = mock(RaceResults.class);
    private RaceResults horsesResults = mock(RaceResults.class, "horsesResults");
    private RaceResults f1Results = mock(RaceResults.class, "f1Results");
    private RaceResults boatsResults = mock(RaceResults.class, "boatsResults");
    private RaceResults athsResults = mock(RaceResults.class, "athsResults");
    private RacesClient clientA = mock(RacesClient.class, "clientA");
    private RacesClient clientB = mock(RacesClient.class, "clientB");

    private RaceResultsService raceResultsService = new RaceResultsService();

    @Before
    public void initializeResults() {
        when(horsesResults.getCategory()).thenReturn(RaceCategory.HORSES);
        when(horsesResults.getMessage()).thenReturn("Horse Race Results");
        when(f1Results.getCategory()).thenReturn(RaceCategory.F1);
        when(f1Results.getMessage()).thenReturn("F1 Race Results");
        when(boatsResults.getCategory()).thenReturn(RaceCategory.BOATS);
        when(boatsResults.getMessage()).thenReturn("Boat Race Results");
        when(athsResults.getCategory()).thenReturn(RaceCategory.ATHLETICS);
        when(athsResults.getMessage()).thenReturn("Athletics Race Results");
        when(raceResults.getMessage()).thenReturn("Race Results");
    }

    @Test(expected = IllegalArgumentException.class)
    public void service_shouldThrowException_ifUnknownClientsUnsubscribe() {
        raceResultsService.removeSubscriber(clientA);
    }

    @Test
    public void unknownSubscribedClients_shouldNotReceiveRaceResults() {
        for (RaceCategory category : RaceCategory.values()) {
            when(raceResults.getCategory()).thenReturn(category);
            raceResultsService.sendResults(raceResults);
            verify(clientA, never()).receive(raceResults);
            verify(clientB, never()).receive(raceResults);
        }
    }

    @Test
    public void allSubscribers_shouldReceiveRaceResults() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientB);

        for (RaceCategory category : RaceCategory.values()) {
            when(raceResults.getCategory()).thenReturn(category);
            raceResultsService.sendResults(raceResults);
        }

        verify(clientA, times(RaceCategory.values().length)).receive(raceResults);
        verify(clientB, times(RaceCategory.values().length)).receive(raceResults);
    }

    @Test
    public void clientSubscribedMultipleTimes_shouldReceiveRaceResultsOnce() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientA);

        raceResultsService.sendResults(horsesResults);

        verify(clientA, times(1)).receive(horsesResults);
    }

    @Test
    public void unsubscribedClient_shouldNotReceiveRaceResults() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.removeSubscriber(clientA);

        for (RaceCategory category : RaceCategory.values()) {
            when(raceResults.getCategory()).thenReturn(category);
            raceResultsService.sendResults(raceResults);
        }

        verify(clientA, never()).receive(raceResults);
    }

    @Test
    public void clientOnlySubscribedToHorseRaces_shouldOnlyReceiveHorseRacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.HORSES);

        for (RaceCategory category : RaceCategory.values()) {
            if(category != RaceCategory.HORSES) {
                when(raceResults.getCategory()).thenReturn(category);
                raceResultsService.sendResults(raceResults);
                verify(clientA, never()).receive(raceResults);
            }
        }
        raceResultsService.sendResults(horsesResults);

        verify(clientA).receive(horsesResults);
    }

    @Test
    public void clientOnlySubscribedToF1Races_shouldOnlyReceiveF1RacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.F1);

        for (RaceCategory category : RaceCategory.values()) {
            if(category != RaceCategory.F1) {
                when(raceResults.getCategory()).thenReturn(category);
                raceResultsService.sendResults(raceResults);
                verify(clientA, never()).receive(raceResults);
            }
        }
        raceResultsService.sendResults(f1Results);

        verify(clientA).receive(f1Results);
    }

    @Test
    public void clientOnlySubscribedToBoatRaces_shouldOnlyReceiveBoatRacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.BOATS);

        for (RaceCategory category : RaceCategory.values()) {
            if(category != RaceCategory.BOATS) {
                when(raceResults.getCategory()).thenReturn(category);
                raceResultsService.sendResults(raceResults);
                verify(clientA, never()).receive(raceResults);
            }
        }
        raceResultsService.sendResults(boatsResults);

        verify(clientA).receive(boatsResults);
    }

    @Test
    public void clientOnlySubscribedToAthRaces_shouldOnlyReceiveAthRacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.ATHLETICS);

        for (RaceCategory category : RaceCategory.values()) {
            if(category != RaceCategory.ATHLETICS) {
                when(raceResults.getCategory()).thenReturn(category);
                raceResultsService.sendResults(raceResults);
                verify(clientA, never()).receive(raceResults);
            }
        }
        raceResultsService.sendResults(athsResults);

        verify(clientA).receive(athsResults);
    }

    @Test
    public void clientNotSubscribedToHorseRaces_shouldNotReceiveHorseRacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.F1);

        raceResultsService.sendResults(horsesResults);

        verify(clientA, never()).receive(horsesResults);
    }

    @Test
    public void clientUnsubscribedFromHorseRaces_shouldNotReceiveHorseRacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.HORSES);
        raceResultsService.removeSubscription(clientA, RaceCategory.HORSES);

        raceResultsService.sendResults(horsesResults);

        verify(clientA, never()).receive(horsesResults);
    }

    @Test
    public void clientSubscribedToHorseAndF1Races_shouldReceiveHorseAndF1RacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.HORSES);
        raceResultsService.addSubscription(clientA, RaceCategory.F1);

        raceResultsService.sendResults(horsesResults);
        raceResultsService.sendResults(f1Results);

        verify(clientA).receive(horsesResults);
        verify(clientA).receive(f1Results);
    }

    @Test
    public void clientUnsubscribedFromHorsesButStillInF1_shouldOnlyReceiveF1RacesResults() {
        raceResultsService.addSubscription(clientA, RaceCategory.F1);
        raceResultsService.addSubscription(clientA, RaceCategory.HORSES);
        raceResultsService.removeSubscription(clientA, RaceCategory.HORSES);

        raceResultsService.sendResults(horsesResults);
        raceResultsService.sendResults(f1Results);

        verify(clientA, never()).receive(horsesResults);
        verify(clientA).receive(f1Results);
    }
}
