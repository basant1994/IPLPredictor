package com.iplpredictor.dao;

import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;

public interface PredictionDao {
    PredictionResult predictResult(String teamName);
    Schedule getSchedule(long startTime);
}
