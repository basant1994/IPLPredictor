package com.iplpredictor.service;


import com.iplpredictor.dao.PredictionDao;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService{

    @Autowired
    private PredictionDao predictionDao;

    @Override
    public PredictionResult predictResult(String teamName) {
        return null;
    }

    @Override
    public Schedule getSchedule(long startTime) {
        return predictionDao.getSchedule(startTime);
    }
}
