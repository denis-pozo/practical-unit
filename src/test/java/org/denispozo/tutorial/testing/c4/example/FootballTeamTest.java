package org.denispozo.tutorial.testing.c4.example;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {

    private static final int ANY_NUMBER = 123;

    public Object[] nbOfWins() {
        return $(0, 1, 2, 3, 4);
    }

    public Object[] invalidNbOfWins() {
        return $(-4, -100, -2, -1);
    }

    @Test
    @Parameters(method = "nbOfWins")
    public void constructorShouldSetGamesWonParam(int nbOfWins) {
        FootballTeam team = new FootballTeam(nbOfWins);

        assertEquals(nbOfWins + " games passed to the constructor, but "
                     + team.getGamesWon() + " were returned",
                     nbOfWins, team.getGamesWon());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "invalidNbOfWins")
    public void constructorShouldThrowIAEforInvalidNbOfWins(int invalidNbWins) {
        FootballTeam team = new FootballTeam(invalidNbWins);
        System.out.println("This line should never get printed. Nb of wins: " + team.getGamesWon());
    }

    @Test
    public void souldBePossibleToCompareTeams() {
        FootballTeam team = new FootballTeam(ANY_NUMBER);

        assertTrue("FootballTeam should implement Comparable", team instanceof Comparable);
    }

    @Test
    public void teamWithMoreWinsShouldBeGreater() {
        int threeWins = 3;
        int twoWins = 0;

        FootballTeam first = new FootballTeam(threeWins);
        FootballTeam second = new FootballTeam(twoWins);

        assertTrue("team with " + first.getGamesWon() + " wins should be ranked before "
                    + "the team with " + second.getGamesWon() + " wins",
                   first.compareTo(second) > 0);
    }

    @Test
    public void teamWithLessWinsShouldBeSmaller() {
        int threeWins = 3;
        int twoWins = 0;

        FootballTeam first = new FootballTeam(threeWins);
        FootballTeam second = new FootballTeam(twoWins);

        assertTrue("team with " + first.getGamesWon() + " wins should be ranked before "
                   + "the team with " + second.getGamesWon() + " wins",
                   second.compareTo(first) < 0);
    }

    @Test
    public void teamWithSameNumberOfWinsShouldBeEqual() {
        int wins = 6;
        FootballTeam teamA = new FootballTeam(wins);
        FootballTeam teamB = new FootballTeam(wins);

        assertTrue( "teams with same number of wins " + wins + " wins]"
                    + " should be equal", teamA.compareTo(teamB) == 0);
    }
}
