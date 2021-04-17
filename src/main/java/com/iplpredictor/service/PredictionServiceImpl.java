package com.iplpredictor.service;


import com.iplpredictor.dao.PredictionDao;
import com.iplpredictor.model.Match;
import com.iplpredictor.model.MatchPoll;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
public class PredictionServiceImpl implements PredictionService{

    @Autowired
    private PredictionDao predictionDao;

    @Override
    public PredictionResult predictResult(String teamId) {
        this.predictionDao.updatePredictionCount(teamId);
        return new PredictionResult();
    }

    @Override
    public Schedule getSchedule(long startTime) {
        return predictionDao.getSchedule(startTime);
    }

    @Override
    public Map<String, Object> getPredictionCounts() {
        List<Map<String, Object>> predictionCounts = predictionDao.getPredictionCounts();
        Map<String, Object> teamPredictionCount = predictionCounts.stream()
                .collect(Collectors.toMap(e -> e.get("id").toString(), e -> e.get("count")));
        return teamPredictionCount;
    }

    public List<Match> getNextMatch()
    {
        long millis = System.currentTimeMillis();
        Date startOfIPL = new Date(1617980442000L);

        Date utcTimeStamp =  new Date(millis);
        //Date indianTimeStamp = DateUtils.addMinutes(utcTimeStamp, 330);
        long diffInMillies = Math.abs(utcTimeStamp.getTime() - startOfIPL.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
        log.info("Diff: " + diff);

        return predictionDao.getNextMatch(diff);
    }

    @Override
    public boolean poll(int matchId, int teamId) {
        return this.predictionDao.updatePoll(matchId, teamId);
    }

    @Override
    public MatchPoll getPollData(int matchId) {
        return this.predictionDao.getPollData(matchId);
    }


}
