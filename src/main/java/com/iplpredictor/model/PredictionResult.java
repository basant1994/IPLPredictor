package com.iplpredictor.model;

import lombok.Data;

import java.util.List;

@Data
public class PredictionResult {
    Match[] predictedMatchResults;
    PointsTable pointsTable;
    TeamStat[] predictedTeamStats;

    public PredictionResult(Match[] predictedMatchResults, TeamStat[] predictedTeamStats, PointsTable predictedPointsTable) {
        this.pointsTable = predictedPointsTable;
        this.predictedMatchResults = predictedMatchResults;
        this.predictedTeamStats = predictedTeamStats;
    }
}
