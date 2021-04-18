package com.iplpredictor.service;

import com.iplpredictor.model.Match;
import com.iplpredictor.model.MatchPoll;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;

import java.util.List;
import java.util.Map;

public interface PredictionService {
    PredictionResult predictResult(int teamId);
    Schedule getSchedule(long startTime);
    Map<Integer,Object> getPredictionCounts();
    List<Match> getNextMatch();
    boolean poll(int matchId, int teamId);
    MatchPoll getPollData(int matchId);
    List<Match> getAllMatches();

}
