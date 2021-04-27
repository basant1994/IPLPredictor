package com.iplpredictor.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class PredictionResult implements Serializable {
    Match[] predictedMatchResults;
    PointsTable pointsTable;
    TeamStat[] predictedTeamStats;

    public PredictionResult(Match[] predictedMatchResults, TeamStat[] predictedTeamStats, PointsTable predictedPointsTable) {
        this.pointsTable = predictedPointsTable;
        this.predictedMatchResults = predictedMatchResults;
        this.predictedTeamStats = predictedTeamStats;
    }

    public PredictionResult() {

    }
}
