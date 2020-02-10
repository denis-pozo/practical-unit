package org.denispozo.tutorial.testing.c4.example;

public class FootballTeam implements Comparable<FootballTeam> {

    private int gamesWon = 0;

    public FootballTeam(int gamesWon) {
        if(gamesWon < 0) {
            throw new IllegalArgumentException();
        }

        this.gamesWon = gamesWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public int compareTo(FootballTeam otherTeam) {
        return gamesWon - otherTeam.getGamesWon();
    }
}
