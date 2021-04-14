package com.iplpredictor.service;

import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;

public interface PredictionService {
    PredictionResult predictResult(String teamName);
    Schedule getSchedule(long startTime);

}
