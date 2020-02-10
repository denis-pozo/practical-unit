package org.denispozo.tutorial.testing.c4.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FootballTeamTest {

    private static final int THREE_GAMES_WON = 3;

    @Test
    public void constructorShouldSetGamesWon() {
        FootballTeam team = new FootballTeam(THREE_GAMES_WON);

        assertEquals(THREE_GAMES_WON + " games passed to the constructor, but "
                     + team.getGamesWon() + " were returned",
                     THREE_GAMES_WON, team.getGamesWon());
    }
}
