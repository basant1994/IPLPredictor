package com.iplpredictor.model;

public class TeamStat {
    private final int id;
    int noOfMatchesPlayed;
    int noOfMatchesWon;
    int noOfMatchesLost;
    int noOfMatchesTied;
    int points;
    int netRunRate;

    TeamStat(int id) {
        this.id = id;
        noOfMatchesPlayed = 0;
        noOfMatchesWon = 0;
        noOfMatchesLost = 0;
        noOfMatchesTied = 0;
        points = 0;
        netRunRate = 0;
    }

    public int getId() {
        return id;
    }

    public int getNoOfMatchesPlayed() {
        return noOfMatchesPlayed;
    }

    public void updateNoOfMatchesPlayed() {
        this.noOfMatchesPlayed += 1;
    }

    public int getNoOfMatchesWon() {
        return noOfMatchesWon;
    }

    public void updateNoOfMatchesWon() {
        this.noOfMatchesWon += 1;
    }

    public int getNoOfMatchesLost() {
        return noOfMatchesLost;
    }

    public void updateNoOfMatchesLost() {
        this.noOfMatchesLost += 1;
    }

    public int getNoOfMatchesTied() {
        return noOfMatchesTied;
    }

    public void updateNoOfMatchesTied() {
        this.noOfMatchesTied += 1;
    }

    public int getPoints() {
        return points;
    }

    public void updatePoints() {
        this.points = 2 * this.noOfMatchesWon + this.noOfMatchesTied;
    }

    public int getNetRunRate() {
        return netRunRate;
    }

    public void setNetRunRate(int netRunRate) {
        this.netRunRate = netRunRate;
    }
}
