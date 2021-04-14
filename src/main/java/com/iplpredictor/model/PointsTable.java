package com.iplpredictor.model;


public class PointsTable {
    TeamStat[] teamStat;

    PointsTable() {
        teamStat = new TeamStat[8];
    }

    PointsTable(TeamStat[] teamStats) {
        this.teamStat = teamStats;
    }
}

