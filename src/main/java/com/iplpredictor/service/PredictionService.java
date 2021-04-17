package com.iplpredictor.service;

import com.iplpredictor.model.Match;
import com.iplpredictor.model.MatchPoll;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;

import java.util.List;
import java.util.Map;

public interface PredictionService {
    PredictionResult predictResult(String teamName);
    Schedule getSchedule(long startTime);
    Map<String,Object> getPredictionCounts();
    List<Match> getNextMatch();
    boolean poll(int matchId, int teamId);
    MatchPoll getPollData(int matchId);


}
